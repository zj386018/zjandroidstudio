package com.example.asus.mytreeview.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2015/9/18.
 */
public class Node {



    private int id;

    /**
     * 跟节点的pid = 0
     */
    private int pId = 0;
    private String name;

    /**
     * 树的层级
     */
    private int level;

    /**
     * 是否是展开的
     */
    private boolean isExpand = false;
    private int icon;

    private Node parent;
    private List<Node> children = new ArrayList<Node>();


    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> childer) {
        this.children = childer;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    /**
     * 是否是根节点
     * @return 父节点是否等于空（若为空则返回值是true）
     */
    public boolean isRoot(){
        return parent == null;
    }

    /**
     * 判断当前父节点的收缩状态
     * @return
     */
    public boolean isParentExpand() {

        if(parent == null) return false;
        return parent.isExpand();
    }

    /**
     * 判断是否为叶子节点（若为叶子节点，则子节点为0）
     * @return
     */
    public boolean isLeaf(){

        return children.size() == 0;
    }

    /**
     * 得到当前节点的层级
     * @return
     */
    public int getLevel() {

        return parent == null?0:(parent.getLevel()+1);
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setIsExpand(boolean isExpand) {
        this.isExpand = isExpand;

        if(!isExpand)
        {
            for(Node node:children)
            {
                node.setIsExpand(false);
            }
        }


    }

    public Node() {
    }

    public Node(int id, int pId, String name) {
        this.id = id;
        this.pId = pId;
        this.name = name;
    }
}




