package org.feuyeux.restful.oauth2.common.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
public class Tarots implements Serializable {
    private List<Tarot> tarotList;

    @XmlElement(name = "tarot")
    @XmlElementWrapper
    public List<Tarot> getTarotList() {
        return tarotList;
    }

    public void setTarotList(List<Tarot> tarotList) {
        this.tarotList = tarotList;
    }
}
