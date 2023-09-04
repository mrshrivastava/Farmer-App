package com.example.navigationdrawer;

public class ComplaintModel {
    private String aadhar;

    private String date;
    private String desc;
    private String mob;
    private String name;


    public ComplaintModel()
    {

    }

    public ComplaintModel(String aadhar,String date,String desc,String mob,String name)
    {
        if(desc.trim().equals(""))
        {
            desc="";
        }
        if(name.trim().equals(""))
        {
            name="";
        }
        this.aadhar=aadhar;
        this.date=date;
        this.desc=desc;
        this.mob=mob;
        this.name=name;

    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

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

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
