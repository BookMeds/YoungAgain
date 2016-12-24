package com.bookmeds.youngagain;

/**
 * Created by sanjit on 24/12/16.
 * Project: YoungAgain
 */

public class MenuItem {
    private int image;
    private String name;
    private String detail;

    public MenuItem(int image, String name, String detail) {
        this.image = image;
        this.name = name;
        this.detail = detail;
    }

    public MenuItem() {

    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDetail() {
        return detail;
    }
}
