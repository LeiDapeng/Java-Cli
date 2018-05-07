package com.crm.dto;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ResponseInfo {
    public ResponseInfo(String code,String msg,Object data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    String code;
    String msg;
    Object data;
    public String toJson(){
        Map returnMap=new HashMap();
        returnMap.put("code",this.code);
        returnMap.put("msg",this.msg);
        returnMap.put("data",this.data);
        JSONArray array = JSONArray.fromObject(returnMap);
        String jsonstr = array.toString();
        return array.toString();

    }
}
