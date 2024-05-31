package org.qiuyun.comment.controller;

import lombok.RequiredArgsConstructor;
import org.qiuyun.comment.dto.CustomEmojiSetReqDTO;
import org.qiuyun.comment.service.CustomeEmojiService;
import org.qiuyun.common.web.Result;
import org.qiuyun.common.web.Results;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/5/31 14:33
 */
@RestController
@RequiredArgsConstructor
public class CustomeEmojiController {
    private final CustomeEmojiService customeEmojiService;

    @PostMapping("/api/comment-service/createCustomEmojiSet")
    public Result<Void> createCustomEmoji(@RequestBody CustomEmojiSetReqDTO requestParam){
        customeEmojiService.createEmojiSet(requestParam);
        return Results.success();
    }
}
