package causharing.causharing.controller;

import causharing.causharing.model.Header;
import causharing.causharing.model.request.TagRequest;
import causharing.causharing.service.TagService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@Api(tags = "태그 기능")
@RestController
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping("/tag")
    public Header create(@RequestBody TagRequest tagRequest) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String email = ((User) auth.getPrincipal()).getUsername();

            return Header.OK(tagService.create(email, tagRequest), "create successfully");
        }
        catch (Exception e) {
            return Header.ERROR("Need to login for creating a tag");
        }
    }

    @GetMapping("/tag")
    public Header read(@RequestParam Long MatchingRoomId,
                       @RequestParam String Month) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String email = ((User) auth.getPrincipal()).getUsername();

            return Header.OK(tagService.read(MatchingRoomId, Month), "read successfully");
        }
        catch (Exception e) {
            return Header.ERROR("Need to login for viewing tags");
        }
    }

}
