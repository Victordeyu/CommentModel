package org.qiuyun.identify.controller;

import lombok.RequiredArgsConstructor;
import org.qiuyun.common.web.Results;
import org.qiuyun.common.web.Result;
import org.qiuyun.identify.dao.dto.req.VideoBitQueryReqDTO;
import org.qiuyun.identify.dao.dto.req.VideoBitSetReqDTO;
import org.qiuyun.identify.dao.dto.resp.VideoBitRespDTO;
import org.qiuyun.identify.service.IdentifyService;
import org.springframework.web.bind.annotation.*;


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
    public Result<Void> updateVideoDimIdentify(@RequestBody VideoBitSetReqDTO requestParam){
        identifyService.updateVideoDimIdentify(requestParam);
        return Results.success();
    }

    @PostMapping("/api/identify-service/videoDimIdentify")
    public Result<VideoBitRespDTO> videoDimIdentify(@RequestBody VideoBitQueryReqDTO requestParam){
        return Results.success(identifyService.videoDimIdentify(requestParam));
    }


}
