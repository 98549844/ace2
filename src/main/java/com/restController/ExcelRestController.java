package com.restController;

import com.models.common.AjaxResponse;
import com.models.entity.Columns;
import com.models.entity.Users;
import com.service.DataBaseService;
import com.service.UsersService;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.util.EasyExcelUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname: ExcelRestController
 * @Date: 6/12/2021 3:18 上午
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rest/easyExcel")
@Api(tags = "easyExcel")
public class ExcelRestController {
    private static final Logger log = LogManager.getLogger(ExcelRestController.class.getName());

    final static String path = "/Users/garlam/IdeaProjects/ace/src/main/resources/files/output/";
    final static String fileName = path + "excel.xls";

    private DataBaseService dataBaseService;
    private UsersService usersService;


    @Autowired
    public ExcelRestController(DataBaseService dataBaseService, UsersService usersService) {
        this.dataBaseService = dataBaseService;
        this.usersService = usersService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{table}")
    public AjaxResponse generateTableToExcel(@PathVariable String table) throws ClassNotFoundException {

        List<Columns> list = dataBaseService.getColumnName(table);
        List<String> columns = new ArrayList<>();
        for (Columns c : list) {
            columns.add(c.getColumnName());
        }
        List<Users> users = usersService.findAll();

        EasyExcelUtil easyExcelUtil = new EasyExcelUtil();
        easyExcelUtil.write(fileName, users, new Users());
        // write(users);

        return AjaxResponse.success(list);
    }


}

