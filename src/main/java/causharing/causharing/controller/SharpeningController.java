package causharing.causharing.controller;

import causharing.causharing.model.Header;
import causharing.causharing.model.request.EditedDataRequest;
import causharing.causharing.service.SharpeningService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "첨삭 내용 등록, 읽기")
public class SharpeningController {

    @Autowired
    private SharpeningService sharpeningService;

    @PostMapping("/sharpening")
    public Header create(@RequestBody EditedDataRequest editedDataRequest) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String email = ((User) auth.getPrincipal()).getUsername();

            return Header.OK(sharpeningService.create(email, editedDataRequest), "sharpening successfully");
        }
        catch (Exception e) {
            return Header.ERROR("Need to login for sharpening a post");
        }

    }

    @GetMapping("/sharpening/{postId}")
    public Header read(@PathVariable Long postId) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String email = ((User) auth.getPrincipal()).getUsername();
            if (sharpeningService.read(postId) == null)
                return Header.OK("Sharpening content does not exist");
            return Header.OK(sharpeningService.read(postId), "Successfully read sharpening content");
        }
        catch (Exception e) {
            return Header.ERROR("Need to login for reading the sharpening content");
        }

    }


}
