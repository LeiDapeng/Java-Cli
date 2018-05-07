package com.crm.controller;

import com.crm.dto.ResponseInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class BaseController {
    public String ResponseJson(String code,String msg,Object data){
        return new ResponseInfo(code,msg,data).toJson();
    }
}
