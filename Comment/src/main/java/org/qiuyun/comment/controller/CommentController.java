package org.qiuyun.comment.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.qiuyun.comment.dto.CommentReqDTO;
import org.qiuyun.comment.service.CommentService;
import org.qiuyun.common.web.Result;
import org.qiuyun.common.web.Results;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/5/26 16:54
 */
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/api/comment-service/create")
    public Result<Void> createComment(@RequestBody CommentReqDTO requestParam){
        commentService.createCommentDO(requestParam);
        return Results.success();
    }
}
