package client.mvc;

import com.example.Session;
import com.example.Speaker;
import com.example.Tarot;
import com.example.Tarots;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClientException;
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
public class ClientController {

    private String tarotsUrl = "http://localhost:8080/conference/rest/tarots";

    private RestOperations restTemplate;

    public void setRestTemplate(RestOperations restTemplate) {
        this.restTemplate = restTemplate;}

    @RequestMapping("/conference/tarots")
    public String getTarots(Model model) throws Exception {
        try {
            Tarots tarots = restTemplate.getForObject(URI.create(tarotsUrl), Tarots.class);
            model.addAttribute("tarots", tarots.getTarotList());
            return "tarots";
        } catch (RestClientException e) {
            throw new IllegalStateException(e);
        }
    }
}
