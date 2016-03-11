package org.feuyeux.restful.oauth2.common.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class Tarot implements Serializable {
    private String card;
    private String v;
    private String rv;

    @XmlAttribute
    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    @XmlAttribute
    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    @XmlAttribute
    public String getRv() {
        return rv;
    }

    public void setRv(String rv) {
        this.rv = rv;
    }
}
