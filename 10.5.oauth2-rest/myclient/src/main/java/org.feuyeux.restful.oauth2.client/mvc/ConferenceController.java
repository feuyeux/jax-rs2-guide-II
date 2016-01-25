package org.feuyeux.restful.oauth2.client.mvc;

import org.feuyeux.restful.oauth2.client.Session;
import org.feuyeux.restful.oauth2.client.Speaker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestOperations;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ConferenceController {

    private String tarotsListURL = "http://localhost:8080/myserver/rest/tarots";
    private String speakersListURL = "http://localhost:8080/myserver/rest/speakers";
    private String sessionsListURL = "http://localhost:8080/myserver/rest/sessions";
    private String trustedMessageURL = "http://localhost:8080/myserver/rest/trusted/message";
    private String testMessageURL = "http://localhost:8080/myserver/rest/test";

    private RestOperations restTemplate;
    private RestOperations trustedClientRestTemplate;
    private RestOperations unprotectedRestTemplate;

    @RequestMapping("/myserver/tarots")
    public String getTarots(Model model) throws Exception {
        try {
            String message = restTemplate.getForObject(URI.create(tarotsListURL), String.class);
            return "tarots";
        } catch (Exception e) {
            throw new IllegalStateException(e);
            //return null;
        }
    }

    @RequestMapping("/myserver/speakers")
    public String getSpeakers(Model model) throws Exception {
        try {
            InputStream photosXML = new ByteArrayInputStream(restTemplate.getForObject(
                    URI.create(speakersListURL), byte[].class));

            List<Speaker> speakers = new ArrayList<Speaker>();
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser parser = parserFactory.newSAXParser();
            parser.parse(photosXML, parserSpeaker(speakers));

            model.addAttribute("speakers", speakers);

            return "speakers";
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @RequestMapping("/myserver/sessions")
    public String getSessions(Model model) throws Exception {
        try {
            InputStream photosXML = new ByteArrayInputStream(unprotectedRestTemplate.getForObject(
                    URI.create(sessionsListURL), byte[].class)
            );

            List<Session> sessions = new ArrayList<Session>();
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser parser = parserFactory.newSAXParser();
            parser.parse(photosXML, parserSessions(sessions));

            model.addAttribute("sessions", sessions);

            return "sessions";
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }


    @RequestMapping("/myserver/trusted/message")
    public String trusted(Model model) throws Exception {
        String message = trustedClientRestTemplate.getForObject(URI.create(trustedMessageURL), String.class);
        model.addAttribute("message", message);
        return "message";
    }

    @RequestMapping("/myserver/test")
    public String test(Model model) throws Exception {
        String message = restTemplate.getForObject(URI.create(testMessageURL), String.class);
        model.addAttribute("message", message);
        return "message";
    }

    public void setRestTemplate(RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void setTrustedClientRestTemplate(RestOperations trustedClientRestTemplate) {
        this.trustedClientRestTemplate = trustedClientRestTemplate;
    }

    public void setUnprotectedRestTemplate(RestOperations unprotectedRestTemplate) {
        this.unprotectedRestTemplate = unprotectedRestTemplate;
    }

    private DefaultHandler parserTarot(final List<Speaker> tarots) throws Exception {
        return new DefaultHandler() {
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes)
                    throws SAXException {
                if ("map".equals(qName)) {
                    Speaker speaker = new Speaker();
                    speaker.setId(new Long(attributes.getValue("id")));
                    speaker.setName(attributes.getValue("name"));
                    speaker.setCompany(attributes.getValue("company"));
                    speaker.setPhotoUri(attributes.getValue("photoUri"));
                    speaker.setTitle(attributes.getValue("title"));
                    speaker.setTwitter(attributes.getValue("twitter"));
                    speaker.setBio(attributes.getValue("bio"));
                    tarots.add(speaker);
                }
            }
        };
    }

    private DefaultHandler parserSpeaker(final List<Speaker> speakers) throws Exception {
        return new DefaultHandler() {
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes)
                    throws SAXException {
                if ("speaker".equals(qName)) {
                    Speaker speaker = new Speaker();
                    speaker.setId(new Long(attributes.getValue("id")));
                    speaker.setName(attributes.getValue("name"));
                    speaker.setCompany(attributes.getValue("company"));
                    speaker.setPhotoUri(attributes.getValue("photoUri"));
                    speaker.setTitle(attributes.getValue("title"));
                    speaker.setTwitter(attributes.getValue("twitter"));
                    speaker.setBio(attributes.getValue("bio"));
                    speakers.add(speaker);
                }
            }
        };
    }

    private DefaultHandler parserSessions(final List<Session> sessions) throws Exception {
        return new DefaultHandler() {
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes)
                    throws SAXException {
                if ("session".equals(qName)) {
                    Session session = new Session();
                    session.setId(attributes.getValue("id"));
                    session.setTitle(attributes.getValue("title"));
                    session.setDescription(attributes.getValue("description"));
                    sessions.add(session);
                }
            }
        };
    }

}
