package org.qiuyun.comment.controller;


import lombok.RequiredArgsConstructor;
import org.qiuyun.comment.dao.entity.CommentDO;
import org.qiuyun.comment.dto.CommentDeleteReqDTO;
import org.qiuyun.comment.dto.CommentReqDTO;
import org.qiuyun.comment.service.CommentService;
import org.qiuyun.common.web.Result;
import org.qiuyun.common.web.Results;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/5/26 16:54
 */
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/api/comment-service/createComment")
    public Result<Void> createComment(@RequestBody CommentReqDTO requestParam){
        commentService.createComment(requestParam);
        return Results.success();
    }

    @PostMapping("/api/comment-service/createReply")
    public Result<Void> createReply(@RequestBody CommentReqDTO requestParam){
        commentService.createReply(requestParam);
        return Results.success();
    }

    @GetMapping("/api/comment-service/queryById")
    public Result<CommentDO> queryById(@RequestParam("comment_id")Long id){
        return Results.success(commentService.findById(id));
    }

    @GetMapping("/api/comment-service/queryByPid")
    public Result<List<CommentDO>> queryByPid(@RequestParam("comment_pid")Long pid){
        return Results.success(commentService.findByPid(pid));
    }

    @GetMapping("/api/comment-service/queryByVid")
    public Result<List<CommentDO>> queryByVid(@RequestParam("comment_vid")Long vid){
        return Results.success(commentService.findByVideoid(vid));
    }

    @PostMapping("/api/comment-service/deleteComment")
    public Result<Void> deleteComment(@RequestBody CommentDeleteReqDTO requestParam){
        commentService.deleteComment(requestParam.getId());
        return Results.success();
    }

}
