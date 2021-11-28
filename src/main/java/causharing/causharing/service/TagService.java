package causharing.causharing.service;

import causharing.causharing.model.entity.MatchingRoom;
import causharing.causharing.model.entity.Tag;
import causharing.causharing.model.repository.MatchingRoomRepository;
import causharing.causharing.model.repository.TagRepository;
import causharing.causharing.model.repository.UserRepository;
import causharing.causharing.model.request.TagRequest;
import causharing.causharing.model.response.TagListResponse;
import causharing.causharing.model.response.TagResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private MatchingRoomRepository matchingRoomRepository;

    @Autowired
    private UserRepository userRepository;

    public TagResponse create(String email, TagRequest tagRequest) {

        // 태그를 만드는 api
        Tag tag = Tag.builder()
                .tagName(tagRequest.getTagName())
                .writer(userRepository.findByEmail(email).getNickname())
                .startDate(tagRequest.getStartDate())
                .endDate(tagRequest.getEndDate())
                .matchingRoomId(matchingRoomRepository.findByMatchingRoomId(tagRequest.getMatchingRoomId()))
                .rgb(tagRequest.getRgb())
                .build();

        tagRepository.save(tag);

        // TagResponse 리턴
        return TagResponse.builder()
                .tagName(tag.getTagName())
                .writer(userRepository.findByEmail(email).getNickname())
                .startDate(tag.getStartDate())
                .endDate(tag.getEndDate())
                .matchingRoomId(tag.getMatchingRoomId().getMatchingRoomId())
                .rgb(tag.getRgb())
                .build();

    }

    public TagListResponse read(Long matchingRoomId, String month) {
        List<Tag> tags = tagRepository.findByTags(matchingRoomId, month);
        List<TagResponse> tagResponseList = tags.stream().map(tag -> response(tag))
                .collect(Collectors.toList());

        return TagListResponse.builder()
                .tagResponseList(tagResponseList)
                .build();
    }

    private TagResponse response(Tag tag) {
        return TagResponse.builder()
                .tagName(tag.getTagName())
                .writer(tag.getWriter())
                .startDate(tag.getStartDate())
                .endDate(tag.getEndDate())
                .rgb(tag.getRgb())
                .matchingRoomId(tag.getMatchingRoomId().getMatchingRoomId())
                .build();
    }
}
