package com.xishanqu.ad.constant;

import lombok.Getter;

/**
 * @Function:
 * @Author: BaoNing
 * @Date: 2019-04-11
 */
@Getter
public enum CreativeType {

    IMAGE(1,"图片"),
    VIDEO(2,"视频"),
    TEXT(3,"文本");

    private int type;
    private String desc;

    CreativeType(int type, String desc){
        this.type = type;
        this.desc = desc;
    }

}
