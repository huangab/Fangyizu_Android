package com.fangku.fyz.modular_house.bean;

/**
 * 合约中显示水表电表
 * Created by   jie.wang
 * Date: 2016/8/23
 * Time: 15:00
 */
public class Bean_House_Heyue_biao {


    private String name;
    private String fanwei;
    private String size;

    @Override
    public String toString() {
        return "Bean_House_Heyue_biao{" +
                "name='" + name + '\'' +
                ", fanwei='" + fanwei + '\'' +
                ", size='" + size + '\'' +
                ", danwei='" + danwei + '\'' +
                '}';
    }

    private String danwei;

    public String getDanwei() {
        return danwei;
    }

    public void setDanwei(String danwei) {
        this.danwei = danwei;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFanwei() {
        return fanwei;
    }

    public void setFanwei(String fanwei) {
        this.fanwei = fanwei;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


}
