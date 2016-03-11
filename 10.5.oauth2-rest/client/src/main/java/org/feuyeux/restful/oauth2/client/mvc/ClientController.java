package org.feuyeux.restful.oauth2.client.mvc;

import org.feuyeux.restful.oauth2.common.domain.Tarots;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import java.net.URI;

@Controller
public class ClientController {

    private static final String tarotsUrl = "http://localhost:8080/server/rest/tarots";

    private RestOperations restTemplate;

    public void setRestTemplate(RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/tarots")
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
