package com.example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
public class DemoApplicationTests {

    @Test
    public void testHi() {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:18080/hi");
        List list = webTarget.request(MediaType.APPLICATION_JSON_TYPE).get(List.class);
        Assert.assertNotNull(list);
        Assert.assertEquals("hello spring boot", list.get(0));
    }

}
