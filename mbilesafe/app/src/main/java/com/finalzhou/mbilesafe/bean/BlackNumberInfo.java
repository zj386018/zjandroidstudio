package com.finalzhou.mbilesafe.bean;

/**
 * 版权： 凡路实验室安卓手机应用开发部（c）2015
 * 作者： 周健
 * 版本： 1.0
 * 创建日期：2015/10/3 23：21
 * 添加描述：
 * 黑名单实体类
 * 修订历史版本：
 */
public class BlackNumberInfo {

    /**
     * 黑名单命名
     */
    private String name;

    /**
     * 黑名单的拦截号码
     */
    private String number;

    /**
     * 黑名单的拦截模式：1.全部拦截  2.电话拦截  3.短信拦截
     */
    private String mode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BlackNumberInfo(String mode, String name, String number) {
        this.mode = mode;
        this.name = name;
        this.number = number;
    }
}
