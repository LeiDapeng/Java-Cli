package com.crm.controller;

import com.crm.dao.DictInfoMapper;
import com.crm.entity.DictInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@CrossOrigin
@RequestMapping("dictInfo")
public class DicInfoController extends BaseController{
    @Autowired
    DictInfoMapper dictInfoMapper;

    @RequestMapping(value="get")
    public String getDicInfo(){
        List<DictInfo> list=dictInfoMapper.selectByGroupKey("cust_type");
        return ResponseJson("0","交易成功",list);

    }

    @RequestMapping("insert")
    public String insert(){
        DictInfo dictInfo=new DictInfo();
        dictInfo.setDictKey("123");
        dictInfoMapper.insert(dictInfo);
        return "ok";
    }

}
