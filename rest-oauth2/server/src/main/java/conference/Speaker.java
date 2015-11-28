package conference;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class Speaker implements Serializable {

    private Long id;
    private String name;
    private String title;
    private String photoUri;
    private String twitter;
    private String bio;
    private String company;

    public Speaker() {
        super();
    }

    public Speaker(Long id, String name, String title,
                   String photoUri, String twitter,
                   String bio, String company) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.photoUri = photoUri;
        this.twitter = twitter;
        this.bio = bio;
        this.company = company;
    }

    @XmlAttribute
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlAttribute
    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    @XmlAttribute
    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    @XmlAttribute
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @XmlAttribute
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
