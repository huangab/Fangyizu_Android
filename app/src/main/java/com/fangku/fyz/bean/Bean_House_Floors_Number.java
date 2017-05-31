package com.fangku.fyz.bean;

import java.util.ArrayList;

/**
 * 楼层号 里面包含房间号
 * Created by   jie.wang
 * Date: 2016/7/26
 * Time: 15:20
 */
public class Bean_House_Floors_Number {

    public Bean_House_Floors_Number() {
        super();
    }

    private int floor;//楼层号
    private ArrayList<Room> data;

    private boolean selectfloor;//是否选中

    @Override
    public String toString() {
        return "房间集合{" +
                "楼号=" + floor + ", 是否全选=" + selectfloor +
                ", data=" + data.toString() + "}\n";
    }


    public boolean isselect() {
        return selectfloor;
    }

    public void setIsselect(boolean isselect) {
        this.selectfloor = isselect;
    }


    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public ArrayList<Room> getData() {
        return data;
    }

    public void setData(ArrayList<Room> data) {
        this.data = data;
    }


    public static class Room {
        private int room;  //房间号
        private boolean isselect;//是否选中

        public boolean isselect() {
            return isselect;
        }

        public void setIsselect(boolean isselect) {
            this.isselect = isselect;
        }


        public int getRoom() {
            return room;
        }

        public void setRoom(int roomsize) {
            this.room = roomsize;
        }


        @Override
        public String toString() {
            return "Room{" +
                    "房间号=" + room +
//                    ", 是否选中=" + isselect +
                    '}';
        }
    }


}
