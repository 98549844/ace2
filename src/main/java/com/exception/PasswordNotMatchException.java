package com.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @Classname: PasswordNotMatchException
 * @Date: 10/12/2021 4:47 AM
 * @Author: garlam
 * @Description:
 */


public class PasswordNotMatchException extends Exception{
    private static final Logger log = LogManager.getLogger(PasswordNotMatchException.class.getName());


    public PasswordNotMatchException() {
        super();
    }

    public PasswordNotMatchException(String message) {
        super(message);
        log.error("Password match result: {} ", message);
    }


}

