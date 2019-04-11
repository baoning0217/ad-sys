package com.xishanqu.ad.constant;

import lombok.Getter;

/**
 * @Function:
 * @Author: BaoNing
 * @Date: 2019-04-11
 */
@Getter
public enum CreativeMaterialType {

    JPG(1,"jpg"),
    BMP(2,"bmp"),

    MP4(3,"mp4"),
    AVI(4,"avi"),

    TEXT(5,"txt");

    private int type;
    private String desc;

    CreativeMaterialType(int type, String desc){
        this.type = type;
        this.desc = desc;
    }

}
