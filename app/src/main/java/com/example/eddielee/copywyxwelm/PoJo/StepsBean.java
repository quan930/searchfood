package com.example.eddielee.copywyxwelm.PoJo;

import java.io.Serializable;

/**
 * Created by eddie on 2017/10/19.
 */

public class StepsBean implements Serializable{

    private String img;
    private String step;

    public StepsBean(String img, String step) {
        this.img = img;
        this.step = step;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

}
