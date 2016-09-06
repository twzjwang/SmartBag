package com.example.wang.smartbag;

/**
 * Created by Wang on 2016/8/26.
 */

public class Data {
    private long id;
    private String name;
    private String day;
    private String tag;
    private int state;
    private String remark1;
    private String remark2;

    public Data(String name, String day, String tag, int state, String remark1, String remark2) {
        this.name = name;
        this.day = day;
        this.tag = tag;
        this.state = state;
        this.remark1 = remark1;
        this.remark2 = remark2;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }
}
