package com.example.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author and0429
 *
 */
@XmlRootElement
public class ErrorMessage {

    private int code;
    private String message;

    public ErrorMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorMessage() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
