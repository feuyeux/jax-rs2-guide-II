package com.example.annotation.param;

import com.example.domain.Link;
import com.example.domain.Yijing;
import com.example.domain.Yijings;
import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.util.List;

public class QueryTest extends JerseyTest {
    private static final Logger LOGGER = Logger.getLogger(QueryTest.class);

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(QueryResource.class);
    }

    @Test
    public void testQueryByPaging() {
        final WebTarget path = target("query-resource").path("yijings");
        final WebTarget queryParam = path.queryParam("start", 24).queryParam("size", 10);
        getResult(queryParam);
    }

    @Test
    public void testQueryByOrder() {
        final WebTarget path = target("query-resource").path("sorted-yijings");
        final WebTarget queryParam = path.queryParam("limit", 5).queryParam("sort", "pronounce");
        getResult(queryParam);
    }

    private void getResult(final WebTarget queryParam) {
        final Invocation.Builder invocationBuilder = queryParam.request();
        final Yijings result = invocationBuilder.get(Yijings.class);
        final List<Yijing> yijings = result.getGuas();
        final List<Link> links = result.getLinks();
        final int size = links.size();
        for (int i = 0; i < size; i++) {
            final Yijing yijing = yijings.get(i);
            final Link link = links.get(i);
            QueryTest.LOGGER.debug(yijing);
            QueryTest.LOGGER.debug(link);
            final Response response = ClientBuilder.newClient().target(link.getHref()).request().get();
            final String guaName = response.readEntity(Yijing.class).getName();
            Assert.assertEquals(yijing.getName(), guaName);
        }
    }
}
