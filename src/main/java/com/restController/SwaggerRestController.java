package com.restController;

import com.models.view.SwaggerVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/swagger")
@Api(tags = "swagger")
public class SwaggerRestController {
    private final static Logger log = LogManager.getLogger(SwaggerRestController.class.getName());


    @RequestMapping(method = RequestMethod.GET)
    public String getSwagger() {
        String get = "Hello World ! GET";
        return get;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userName}")
    public String getSwagger(@PathVariable String userName) {
        String get = "Hello World !" + userName + " GET";
        return get;
    }

    @ApiOperation(value = "swagger post body", notes = "{\n" + "    \"swaggerContent\": \"---CONTENT---\",\n" + "    \"swaggerDescription\": \"---DESCRIPTION---\",\n" + "    \"swaggerId\": 0,\n" + "    \"swaggerTitle\": \"---TITLE---\"\n" + "}")
    @RequestMapping(method = RequestMethod.POST)
    public SwaggerVo postSwagger(@RequestBody SwaggerVo swaggerVo) {
        String post = "Hello World ! POST";
        System.out.println(post);
        swaggerVo.setSwaggerDescription(post);
        return swaggerVo;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String putSwagger() {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + ": ");
        String put = "Hello World ! PUT";
        return put;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteSwagger() {
        String delete = "Hello World ! DELETE";
        return delete;
    }
}
