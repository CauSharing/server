package causharing.causharing.controller;

import causharing.causharing.model.Header;
import causharing.causharing.service.TranslateService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "번역기")
public class TranslateController {

    @Autowired
    private TranslateService translateService;

    @GetMapping("/translate")
    public Header read(@RequestParam String src,
                       @RequestParam String dst,
                       @RequestParam String content) {
        return Header.OK(translateService.getTransSentence(src, dst, content));
    }

}
