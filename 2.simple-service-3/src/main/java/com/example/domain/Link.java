package com.example.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "link")
public class Link {
  protected String relationship;
  protected String href;
  protected String type;

  public Link() {}

  public Link(String relationship, String href, String type) {
    this.relationship = relationship;
    this.href = href;
    this.type = type;
  }

  @XmlAttribute(name = "rel")
  public String getRelationship() {
    return relationship;
  }

  public void setRelationship(String relationship) {
    this.relationship = relationship;
  }

  @XmlAttribute
  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  @XmlAttribute
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return relationship + ":" + href + ":" + type;
  }
}
