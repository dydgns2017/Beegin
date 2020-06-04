package com.sungkyul.aa;

import java.io.Serializable;

public class Option implements Serializable {

    String name;
    String addr;

    public Option(){

    }
    public Option(String name, String addr){
        this.name = name;
        this.addr = addr;
    }

    public String getName() {
        return name;
    }

    public void setPhone(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}