package causharing.causharing.service;

import causharing.causharing.model.entity.EditedData;
import causharing.causharing.model.entity.Post;
import causharing.causharing.model.entity.Sharpening;
import causharing.causharing.model.entity.User;
import causharing.causharing.model.repository.EditedDataRepository;
import causharing.causharing.model.repository.PostRepository;
import causharing.causharing.model.repository.SharpeningRepository;
import causharing.causharing.model.repository.UserRepository;
import causharing.causharing.model.request.EditedDataRequest;
import causharing.causharing.model.response.SharpeningResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SharpeningService {

    @Autowired
    private SharpeningRepository sharpeningRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private EditedDataRepository editedDataRepository;

    public String create(String email, EditedDataRequest editedDataRequest) {
        User user = userRepository.findByEmail(email);
        Post post = postRepository.findByPostId(editedDataRequest.getPostId());
        Sharpening check = sharpeningRepository.findByPostId(post);
        // 최초 생성
        if (check == null) {
            Sharpening sharpening = Sharpening.builder()
                    .postId(post)
                    .writer(user.getNickname())
                    .build();

            sharpeningRepository.save(sharpening);

            List<EditedData> editedDataList = editedDataRequest.getEditedDataList().stream().map(editedData -> createList(editedData, sharpening))
                    .collect(Collectors.toList());

            sharpening.setEditedData(editedDataList);

            sharpeningRepository.save(sharpening);

            return "sharpening content successfully";
        }
        // 첨삭내용 이미 존재하는 경우
        else {
            List<EditedData> editedDataList = editedDataRequest.getEditedDataList().stream().map(editedData -> createList(editedData, check))
                    .collect(Collectors.toList());

            // 리스트에 추가하고 싶은 경우 set을 이용해도 추가됨!
            check.setEditedData(editedDataList);

            sharpeningRepository.save(check);

            return "sharpening content successfully";
        }

    }

    private EditedData createList(causharing.causharing.model.EditedData editedData, Sharpening sharpening) {
        EditedData editedData1 = EditedData.builder()
                .line(editedData.getLine())
                .content(editedData.getContent())
                .sharpeningId(sharpening)
                .build();
        editedDataRepository.save(editedData1);
        return editedData1;
    }

    public SharpeningResponse read(Long postId) {
        Post post = postRepository.findByPostId(postId);
        Sharpening sharpening = sharpeningRepository.findByPostId(post);
        if (sharpening == null) {
            return null;
        }
        List<causharing.causharing.model.EditedData> editedDataList = sharpening.getEditedData().stream().map(editedData -> entityToResponse(editedData))
                .collect(Collectors.toList());
        editedDataList.sort(new UserComparator());
        return SharpeningResponse.builder()
                .postId(post.getPostId())
                .editedDataList(editedDataList)
                .build();
    }

    class UserComparator implements Comparator<causharing.causharing.model.EditedData>{

        @Override
        public int compare(causharing.causharing.model.EditedData o1, causharing.causharing.model.EditedData o2) {
            if(o1.getLine()>o2.getLine()) return 1;
            if(o1.getLine()<o2.getLine()) return -1;
            return 0;
        }
    }

    private causharing.causharing.model.EditedData entityToResponse(EditedData editedData) {
        return causharing.causharing.model.EditedData.builder()
                .EditedDataId(editedData.getEditedDataId())
                .line(editedData.getLine())
                .content(editedData.getContent())
                .writer(editedData.getSharpeningId().getWriter())
                .build();
    }
}
