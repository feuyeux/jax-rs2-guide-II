package com.example.domain;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "yijings")
public class Yijings implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Yijing> guas;
    private List<Link> links;

    @XmlElementRef
    public List<Yijing> getGuas() {
        return guas;
    }

    public void setGuas(List<Yijing> guas) {
        this.guas = guas;
    }

    @XmlElementRef
    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return guas + ":" + links;
    }
}
