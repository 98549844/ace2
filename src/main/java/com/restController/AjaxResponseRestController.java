package com.restController;

import com.exception.ResponseException;
import com.exception.ResponseExceptionType;
import com.models.common.AjaxResponse;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.controller.common.CommonController;
import org.springframework.web.bind.annotation.*;
import com.util.NullUtil;


/**
 * @Classname: AjaxResponseRestController
 * @Date: 13/12/2021 1:17 AM
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rest/ajaxResponse")
@Api(tags = "ajaxResponse")
public class AjaxResponseRestController extends CommonController {
    private static final Logger log = LogManager.getLogger(AjaxResponseRestController.class.getName());

    @RequestMapping(method = RequestMethod.GET, value = "/getSuccess")
    public AjaxResponse getAjaxResponseSuccess() {
        return AjaxResponse.success("SUCCESS");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getError/{code}")
    public AjaxResponse getAjaxResponseError(@PathVariable Integer code) {
        if (NullUtil.isNull(code) || code == 999) {
            return AjaxResponse.error(new ResponseException(ResponseExceptionType.OTHER_ERROR, "AjaxResponse OTHER_ERROR"));
        } else if (code == 400) {
            return AjaxResponse.error(new ResponseException(ResponseExceptionType.USER_INPUT_ERROR, "AjaxResponse USER_INPUT_ERROR"));
        } else if (code == 404) {
            return AjaxResponse.error(new ResponseException(ResponseExceptionType.PAGE_NOT_FOUND_ERROR, "AjaxResponse PAGE_NOT_FOUND_ERROR"));
        } else if (code == 500) {
            return AjaxResponse.error(new ResponseException(ResponseExceptionType.SYSTEM_ERROR, "AjaxResponse SYSTEM_ERROR"));
        }
        return AjaxResponse.error(new ResponseException(ResponseExceptionType.OTHER_ERROR, "AjaxResponse OTHER_ERROR"));

    }
}

