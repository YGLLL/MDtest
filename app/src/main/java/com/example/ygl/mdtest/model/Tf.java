package com.example.ygl.mdtest.model;

/**
 * Created by YGL on 2017/3/15.
 */

public class Tf {
    private String name;
    private int imageId;
    public Tf(String name,int imageId){
        this.name=name;
        this.imageId=imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
