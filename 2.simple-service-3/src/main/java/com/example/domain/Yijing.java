package com.example.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "gua")
@XmlType(propOrder = { "sequence", "name", "pronounce" })
public class Yijing implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sequence;
    private String name;
    private String pronounce;

    public Yijing() {

    }

    public Yijing(String sequence, String name, String pronounce) {
        this.sequence = sequence;
        this.name = name;
        this.pronounce = pronounce;
    }

    @XmlAttribute
    public String getName() {
        return name;
    }

    @XmlAttribute
    public String getPronounce() {
        return pronounce;
    }

    @XmlAttribute
    public String getSequence() {
        return sequence;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public void setPronounce(String pronounce) {
        this.pronounce = pronounce;
    }

    @Override
    public String toString() {
        return sequence + ":" + name + ":" + pronounce;
    }
}
