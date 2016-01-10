package com.first_teck.locationmarkerv2drawer;

import android.media.Image;

/**
 * Created by zyqzh_000 on 2015/11/18.
 */
public class Item2 extends MenuItem{
    private String name;
    private Image myImage;

    public Item2(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getMyImage() {
        return myImage;
    }

    public void setMyImage(Image myImage) {
        this.myImage = myImage;
    }
}
