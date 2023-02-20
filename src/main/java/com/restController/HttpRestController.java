package com.restController;

import com.controller.common.CommonController;
import com.models.common.AjaxResponse;
import com.util.HttpUtil;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Classname: HttpRestController
 * @Date: 2022/8/26 下午 02:51
 * @Author: kalam_au
 * @Description:
 */

@RestController
@RequestMapping("/rest/httpInfo")
@Api(tags = "http")
@EnableConfigurationProperties
public class HttpRestController extends CommonController {
    private static final Logger log = LogManager.getLogger(HttpRestController.class.getName());

    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public AjaxResponse httpInfo() {
        return AjaxResponse.success(HttpUtil.requestInfo(getRequest()));
    }


}

