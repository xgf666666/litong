package com.weibiaogan.litong.entity;

import java.util.List;

/**
 * author: xiaoguagnfei
 * date: 2018/7/11
 * describe:
 */
public class Worker {

    /**
     * area_id : 1
     * area_name : 装修施工
     * area_img : /static/api/images/liquors_.png
     * area_show : 0
     * area_sort : 0
     * pid : 0
     */

    private int area_id;
    private String area_name;
    private String area_img;
    private int area_show;
    private int area_sort;
    private int pid;
    private List<WorkerSonBean> son;

    public List<WorkerSonBean> getSon() {
        return son;
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public String getArea_img() {
        return area_img;
    }

    public void setArea_img(String area_img) {
        this.area_img = area_img;
    }

    public int getArea_show() {
        return area_show;
    }

    public void setArea_show(int area_show) {
        this.area_show = area_show;
    }

    public int getArea_sort() {
        return area_sort;
    }

    public void setArea_sort(int area_sort) {
        this.area_sort = area_sort;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "area_id=" + area_id +
                ", area_name='" + area_name + '\'' +
                ", area_img='" + area_img + '\'' +
                ", area_show=" + area_show +
                ", area_sort=" + area_sort +
                ", pid=" + pid +
                ", son=" + son +
                '}';
    }
}
