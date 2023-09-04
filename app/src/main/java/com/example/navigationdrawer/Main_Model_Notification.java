package com.example.navigationdrawer;

public class Main_Model_Notification {
    public Main_Model_Notification() {
        this.img = img;
        this.desc = desc;
        this.link=link;
        this.date=date;
        this.time=time;
    }

    String date,desc,img,link,time;



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }



    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
