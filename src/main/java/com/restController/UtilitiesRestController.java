package com.restController;

import com.models.common.AjaxResponse;
import com.util.OsUtil;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname: UtilitiesRestController
 * @Date: 2022/12/9 下午 11:40
 * @Author: kalam_au
 * @Description:
 */

@RestController
@RequestMapping("/rest/utilities")
@Api(tags = "utilities")
@EnableConfigurationProperties
public class UtilitiesRestController {
    private static final Logger log = LogManager.getLogger(UtilitiesRestController.class.getName());


    @RequestMapping(method = RequestMethod.GET, value = "/getOSInformation")
    public AjaxResponse getOSInformation() {
        OsUtil.getOsInfo();
        String osName = OsUtil.getOsName();
        return AjaxResponse.success(osName);
    }

}

