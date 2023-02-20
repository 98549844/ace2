package com.restController;

import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/index")
@Api(tags = "index")
public class IndexRestController {
    private final static Logger log = LogManager.getLogger(IndexRestController.class.getName());

    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public String getIndex() {
        String get = "Welcome Index ! GET";
        return get;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userName}")
    public String getIndex(@PathVariable String userName) {
        log.info(userName);

        String get = "Welcome Index !" + userName + " GET";
        return get;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String postIndex(@RequestBody String IndexVO) {
        String post = "Welcome Index ! POST";
        System.out.println(post);
        IndexVO = post;
        return IndexVO;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String putIndex() {
        String put = "Welcome Index ! PUT";
        return put;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteIndex() {


        String delete = "Hello World ! DELETE";
        return delete;
    }
}
