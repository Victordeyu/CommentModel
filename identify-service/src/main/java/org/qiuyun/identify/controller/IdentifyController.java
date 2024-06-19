package org.qiuyun.identify.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.qiuyun.common.web.Result;
import org.qiuyun.common.web.Results;
import org.qiuyun.identify.dao.dto.req.*;
import org.qiuyun.identify.dao.dto.resp.CommentBitRespDTO;
import org.qiuyun.identify.dao.dto.resp.UserBitRespDTO;
import org.qiuyun.identify.dao.dto.resp.VideoBitRespDTO;
import org.qiuyun.identify.service.IdentifyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/6/17 21:06
 */
@RestController
@RequiredArgsConstructor
public class IdentifyController {
    private final IdentifyService identifyService;

    @PostMapping("/api/identify-service/updateVideoDimIdentify")
    public Result<Void> updateVideoDimIdentify(@RequestBody@Valid VideoBitSetReqDTO requestParam){
        identifyService.updateVideoDimIdentify(requestParam);
        return Results.success();
    }

    @PostMapping("/api/identify-service/videoDimIdentify")
    public Result<VideoBitRespDTO> videoDimIdentify(@RequestBody@Valid VideoBitQueryReqDTO requestParam){
        return Results.success(identifyService.videoDimIdentify(requestParam));
    }

    @PostMapping("/api/identify-service/userDimIdentify")
    public Result<UserBitRespDTO> userDimIdentify(@RequestBody@Valid UserBitReqDTO requestParam){
        return Results.success(identifyService.userDimIdentify(requestParam));
    }

    @PostMapping("/api/identify-service/addUserBlackList")
    public Result<Void> addUserBlackList(@RequestBody@Valid UserBlackListReqDTO requestParam){
        identifyService.addUserBlackList(requestParam);
        return Results.success();
    }

    @PostMapping("/api/identify-service/removeUserBlackList")
    public Result<Void> removeUserBlackList(@RequestBody@Valid UserBlackListReqDTO requestParam){
        identifyService.removeUserBlackList(requestParam);
        return Results.success();
    }

    @PostMapping("/api/identify-service/updateCommentDimIdentify")
    public Result<Void> updateCommentDimIdentify(@RequestBody@Valid CommentBitSetReqDTO requestParam){
        identifyService.updateCommentDimIdentify(requestParam);
        return Results.success();
    }

    @GetMapping("/api/identify-service/commentDimIdentify")
    public Result<CommentBitRespDTO> commentDimIdentify(@RequestParam@Valid Long requestParam){
        return Results.success(identifyService.commentDimIdentify(requestParam));
    }

    @PostMapping("/api/identify-service/commentDimBatchIdentify")
    public Result<List<CommentBitRespDTO>>commentDimBatchIdentify(@RequestBody CommentBitBatchReqDTO requestParam){
        return Results.success(identifyService.commentDimBatchIdentify(requestParam));

    }
}
