package com.itheima.security.springboot.support;

import java.util.regex.Pattern;

public class SimpleResponse {

    public SimpleResponse(Object content){
        this.content = content;
    }

    private Object content;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }


    public static void main(String[] args) {


    }
}