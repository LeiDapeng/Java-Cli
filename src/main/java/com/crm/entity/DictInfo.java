package com.crm.entity;

import java.util.Date;

public class DictInfo {
    private Integer id;

    private String dictKey;

    private String dictValue;

    private String dictGroup;

    private Date startDate;

    private Date endDate;

    public DictInfo(Integer id, String dictKey, String dictValue, String dictGroup, Date startDate, Date endDate) {
        this.id = id;
        this.dictKey = dictKey;
        this.dictValue = dictValue;
        this.dictGroup = dictGroup;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public DictInfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDictKey() {
        return dictKey;
    }

    public void setDictKey(String dictKey) {
        this.dictKey = dictKey == null ? null : dictKey.trim();
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue == null ? null : dictValue.trim();
    }

    public String getDictGroup() {
        return dictGroup;
    }

    public void setDictGroup(String dictGroup) {
        this.dictGroup = dictGroup == null ? null : dictGroup.trim();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}