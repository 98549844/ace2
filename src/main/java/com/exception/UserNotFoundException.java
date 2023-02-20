package com.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @Classname: UserNotFoundException
 * @Date: 10/12/2021 4:43 AM
 * @Author: garlam
 * @Description:
 */


public class UserNotFoundException extends Exception{
    private static final Logger log = LogManager.getLogger(UserNotFoundException.class.getName());



    public UserNotFoundException(String message) {
        super(message);
        log.error("User Not Exist {} ", message );
    }
}

