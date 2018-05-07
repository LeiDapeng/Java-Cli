package com.crm.controller;

import com.crm.dto.ResponseInfo;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.EntryMessage;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.util.MessageSupplier;
import org.apache.logging.log4j.util.Supplier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class BaseController {
    Logger log= LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    public String ResponseJson(String code,String msg,Object data){
        return new ResponseInfo(code,msg,data).toJson();
    }
}
