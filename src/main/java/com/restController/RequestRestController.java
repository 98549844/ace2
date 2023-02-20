package com.restController;

import com.models.common.AjaxResponse;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @Classname: RequestRestController
 * @Date: 2022/10/2 上午 04:34
 * @Author: kalam_au
 * @Description:
 */

@RestController
@RequestMapping("/rest/request")
@Api(tags = "request")
public class RequestRestController {
    private static final Logger log = LogManager.getLogger(RequestRestController.class.getName());

    @RequestMapping(method = RequestMethod.POST, value = "/post")
    public AjaxResponse getRequest(MultipartHttpServletRequest request) {
        return AjaxResponse.success(request);
    }

}

