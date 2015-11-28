package conference.rest;

import conference.Session;
import conference.Speaker;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Service
@Path("/")
public class ConferenceService {

    //list of conference speakers available
    private List<Speaker> speakers;
    //list of conference sessions available
    private List<Session> sessions;

    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "test";
    }

    @GET
    @Path("/speakers")
    @Produces(MediaType.APPLICATION_XML)
    public List<Speaker> getSpeakers() {
        return speakers;
    }

    @GET
    @Path("/speakers/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Speaker getSpeaker(@PathParam("id") Long id) {
        for (Speaker s : speakers) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    @GET
    @Path("/sessions")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Session> getSessions() {
        return sessions;
    }

    @GET
    @Path("/sessions/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Session getSession(String id) {
        for (Session s : sessions) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    @GET
    @Path("/trusted/message")
    @Produces(MediaType.TEXT_PLAIN)
    @PreAuthorize("#oauth2.clientHasRole('ROLE_CLIENT')")
    public String getTrustedClientMessage() {
        return "Hello, Trusted Client";
    }

    @PostConstruct
    public void init() {
        loadSpeakers();
        loadSessions();
    }

    public void loadSpeakers() {
        if (speakers == null) {
            //mock all the conference speakers data
            speakers = new ArrayList<Speaker>();
            speakers.add(new Speaker(1l, "Reza Rahman", "Java Evangelist",
                    "http://confoo.ca/images/speakers/2015/reza-rahman.jpg",
                    "http://twitter.com/reza_rahman", null, "Oracle"));
            speakers.add(new Speaker(2l, "Hanneli Tavante", "Senior Software Developer",
                    "http://confoo.ca/images/speakers/2015/hanneli-tavante.jpg",
                    "http://twitter.com/hannelita", null, "CodeMiner 42"));
            speakers.add(new Speaker(3l, "Rodrigo Cândido da Silva", "Software Architect",
                    "http://confoo.ca/images/speakers/2015/rodrigo-candido-da-silva.jpg",
                    "http://twitter.com/rcandidosilva", null, "Integritas"));
            speakers.add(new Speaker(4l, "Eduardo Shiota", "Senior Front-end Developer",
                    "http://confoo.ca/images/speakers/2015/eduardo-shiota-yasuda.jpg",
                    "http://twitter.com/shiota", null, "Booking.com"));
        }
    }

    public void loadSessions() {
        if (sessions == null) {
            //mock all the conference sessions data
            sessions = new ArrayList<Session>();
            sessions.add(new Session("CONFOO01", "JMS.Next(): JMS 2 and Beyond", null));
            sessions.add(new Session("CONFOO02", "Java EE 8 - Future, Wishes and Predictions", null));
            sessions.add(new Session("CONFOO03", "Supporting Multi-Tenancy Applications with Java EE", null));
            sessions.add(new Session("CONFOO04", "Modular JavaScript Heaven with AMD and Events", null));

        }
    }

}
