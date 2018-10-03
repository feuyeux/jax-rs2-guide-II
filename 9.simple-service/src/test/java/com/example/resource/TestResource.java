package com.example.resource;

import com.example.com.example.resource.MyResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;

import java.util.Date;

public class TestResource extends JerseyTest {

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        //enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(MyResource.class);
    }

    @Test
    public void testVersion() {
        Response response = target("rest").path("v1.0").request().get();
        Link link = response.getLink("currentVersion");
        System.out.println(link);

        Response response2 = target("rest").path("v2.0").request().get();
        String result = response2.readEntity(String.class);
        System.out.println(result);
    }

    @Test
    public void testHeadVersion() {
        Response response = target("rest").path("head-version").request().header("X-API-Version", "2").get();
        String result = response.readEntity(String.class);
        System.out.println(result);
    }

    @Test
    public void testCacheControl() {
        Response head = target("rest").path("cache_control").request().get();
        String cacheControl = (String)head.getHeaders().get("Cache-Control").get(0);
        System.out.println(cacheControl);
    }

    @Test
    public void testExpires() {
        Response head = target("rest").path("expires").request().get();
        String expires = (String)head.getHeaders().get("Expires").get(0);
        System.out.println(expires);
    }

    @Test
    public void testLastModified() throws InterruptedException {
        WebTarget webTarget = target("rest").path("last_modified").queryParam("userId", "eric");
        Response head = webTarget.request().get();
        Date lastModified = head.getLastModified();
        System.out.println(head.getStatus() + "\t" + lastModified);
        Assert.assertEquals(200, head.getStatus());
        Thread.sleep(1000);

        Response head1 = webTarget.request().header("If-Modified-Since", lastModified).get();
        System.out.println(head1.getStatus() + "\t" + head1.getLastModified());
        Assert.assertEquals(304, head1.getStatus());
    }

    @Test
    public void testETag() throws InterruptedException {
        WebTarget webTarget = target("rest").path("e_tag").queryParam("userId", "eric");

        Response head = webTarget.request().get();
        EntityTag eTag = head.getEntityTag();
        System.out.println(head.getStatus() + "\t" + eTag);
        Assert.assertEquals(200, head.getStatus());
        Thread.sleep(1000);

        Response head1 = webTarget.request().header("If-None-Match", eTag).get();
        System.out.println(head1.getStatus() + "\t" + head1.getEntityTag());
        Assert.assertEquals(304, head1.getStatus());
    }
}
