package com.animesafar.animecontent.Datastorageclasses;

import java.io.Serializable;

public class Datastorage implements Serializable {

    private String imageuri;
    private String descrip;
    private String titl;



    public Datastorage(String imageurl, String description, String title){

        this.imageuri = imageurl;
        this.descrip = description;
        this.titl = title;


    }

    public Datastorage(String mimg , String rname , String ldate , String status){

        this.imageuri = mimg;
        this.titl = rname;
        this.descrip = ldate;


    }

    public String getImageuri() {
        return imageuri;
    }


    public String getDescrip() {
        return descrip;
    }


    public String getTitl() {
        return titl;
    }

}
