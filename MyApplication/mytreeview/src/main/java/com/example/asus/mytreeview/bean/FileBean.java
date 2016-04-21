package com.example.asus.mytreeview.bean;

import com.example.asus.mytreeview.utils.annotation.TreeNodeId;
import com.example.asus.mytreeview.utils.annotation.TreeNodeLabel;
import com.example.asus.mytreeview.utils.annotation.TreeNodePid;

/**
 * Created by asus on 2015/9/18.
 */
public class FileBean {

    @TreeNodeId
    private int id;

    @TreeNodePid
    private int pId;

    @TreeNodeLabel
    private String  labble;
    private String desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getLabble() {
        return labble;
    }

    public void setLabble(String labble) {
        this.labble = labble;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public FileBean(int id, int pId, String labble) {
        this.id = id;
        this.labble = labble;
        this.pId = pId;
    }
}
