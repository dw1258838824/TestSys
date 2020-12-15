package com.ruoyi.project.system.api.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"return_code","return_msg"})
public class xml {

    @XmlAttribute
    private String return_code;
    @XmlAttribute
    private String return_msg;

    public xml(){}

    public xml(String return_code, String return_msg){
        this.return_code = return_code;
        this.return_msg = return_msg;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }
}
