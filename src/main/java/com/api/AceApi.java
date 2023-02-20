package com.api;

import com.models.common.AjaxResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Classname: AceApi
 * @Date: 9/5/2021 9:52 上午
 * @Author: garlam
 * @Description:
 */


@FeignClient(name = "aceJob", url = "http://localhost:8088/")
public interface AceApi {


    //call target controller url
    @GetMapping("/rest/users/getUsers")
    AjaxResponse getAllUsers();

}
