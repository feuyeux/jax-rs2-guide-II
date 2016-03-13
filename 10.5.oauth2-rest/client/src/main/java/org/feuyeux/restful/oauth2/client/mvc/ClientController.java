package org.feuyeux.restful.oauth2.client.mvc;

import org.feuyeux.restful.oauth2.common.domain.Tarots;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import java.net.URI;

@Controller
public class ClientController {
    @Value("${tarotsURL}")
    private String tarotsUrl;

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
