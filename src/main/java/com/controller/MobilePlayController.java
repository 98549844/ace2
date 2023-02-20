package com.controller;

import com.constant.AceEnvironment;
import com.controller.common.CommonController;
import com.service.MobilePlayService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;

/**
 * @Classname: DplayerController
 * @Date: 2022/11/12 下午 10:11
 * @Author: kalam_au
 * @Description:
 */

@Controller
@RequestMapping("/ace")
public class MobilePlayController extends CommonController {
    private static final Logger log = LogManager.getLogger(MobilePlayController.class.getName());

    private final MobilePlayService mobilePlayService;
    private final String videoPath;


    @Autowired
    public MobilePlayController(MobilePlayService mobilePlayService) {
        this.mobilePlayService = mobilePlayService;
        this.videoPath = AceEnvironment.getVideoPath();
    }


    @RequestMapping(value = "/mobile/play.html/{ext}/{playId}", method = RequestMethod.GET)
    public ModelAndView mobilePlay(@PathVariable(value = "playId") String playId, @PathVariable(value = "ext") String ext) {
        log.info("access mobile/play.html/{}/{}", ext, playId);
        String device = getDevice();
        log.info("device type: {}", device);
        ModelAndView modelAndView = page("ace/tool-pages/mobile-play");
        modelAndView.addObject("playId", playId);
        modelAndView.addObject("ext", ext);

        return modelAndView;
    }



    // ClientAbort Exception and IO Exception
    @GetMapping(value = "/stream/play.html/{ext}/{playId}")
    public ResponseEntity<StreamingResponseBody> streamPlay(
            @PathVariable("playId") String playId, @PathVariable("ext") String ext,
            @RequestHeader(value = "Range", required = false) String rangeHeader) throws IOException {
        log.info("access stream/play.html {} ; {}", ext, playId);
        String mediaName = videoPath + playId + "." + ext;
        return mobilePlayService.getResponseStream (mediaName, rangeHeader);

    }


}

