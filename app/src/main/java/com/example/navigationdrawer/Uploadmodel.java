package com.example.navigationdrawer;

public class Uploadmodel {
    private String desc;
    private String img;
    private String location;
    private String name;
    private String phone;
    private String price;


    public Uploadmodel()
    {

    }

    public Uploadmodel(String desc, String img,String location,String name,String phone, String price)
    {
        if(desc.trim().equals(""))
        {
            desc="";
        }
        if(name.trim().equals(""))
        {
            name="";
        }
        if(location.trim().equals(""))
        {
            location="";
        }
        this.desc=desc;
        this.img=img;
        this.location=location;
        this.name=name;
        this.phone=phone;
        this.price=price;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
