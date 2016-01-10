package com.example.annotation.param;

import com.example.annotation.param.bean.ParamCache;
import com.example.domain.Link;
import com.example.domain.Yijing;
import com.example.domain.Yijings;
import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Path("query-resource")
public class QueryResource {
    private static final Logger LOGGER = Logger.getLogger(QueryResource.class);
    @Context
    UriInfo uriInfo;

    /*http://localhost:9998/query-resource/yijings?start=24&size=10*/
    @Path("yijings")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Yijings getByPaging(@QueryParam("start") final int start, @QueryParam("size") final int size) {
        final Yijings result = new Yijings();
        final List<Link> links = new ArrayList<>();
        final List<Yijing> yijings = new ArrayList<>();
        final UriBuilder ub = uriInfo.getAbsolutePathBuilder().replacePath("query-resource/yijing");
        ArrayList<Yijing> globalList = ParamCache.LIST;
        int listSize = globalList.size();
        final int max = size > listSize ? listSize : size;
        for (int i = 0; i < max; i++) {
            final Yijing yijing = globalList.get(start + i);
            final URI location = ub.clone().queryParam("id", yijing.getSequence()).build();
            final Link link = new Link("detail", location.toASCIIString(), MediaType.APPLICATION_XML);
            links.add(link);
            yijings.add(yijing);
        }
        result.setLinks(links);
        result.setGuas(yijings);
        QueryResource.LOGGER.debug(result);
        return result;
    }

    /*http://localhost:9998/query-resource/sorted-yijings?limit=5&sort=pronounce*/
    @Path("sorted-yijings")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Yijings getByOrder(@QueryParam("limit") final int limit, @QueryParam("sort") final String sortName) {
        final Yijings result = new Yijings();
        final List<Link> links = new ArrayList<>();
        final List<Yijing> yijings = new ArrayList<>();
        final ArrayList<Yijing> list = ParamCache.copy();
        Collections.sort(list, new Comparator<Yijing>() {
            @Override
            public int compare(final Yijing o1, final Yijing o2) {
                switch (sortName) {
                    case "sequence":
                        return o1.getSequence().compareTo(o2.getSequence());
                    case "name":
                        return o1.getName().compareTo(o2.getName());
                    case "pronounce":
                        return o1.getPronounce().compareTo(o2.getPronounce());
                }
                return 0;
            }
        });
        final UriBuilder ub = uriInfo.getAbsolutePathBuilder().replacePath("query-resource/yijing");
        final int max = limit > list.size() ? list.size() : limit;
        for (int i = 0; i < max; i++) {
            final Yijing yijing = list.get(i);
            final URI location = ub.clone().queryParam("id", yijing.getSequence()).build();
            final Link link = new Link("detail", location.toASCIIString(), MediaType.APPLICATION_XML);
            links.add(link);
            yijings.add(yijing);
        }
        result.setLinks(links);
        result.setGuas(yijings);
        QueryResource.LOGGER.debug(result);
        return result;
    }

    @Path("yijing")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Yijing getByQuery(@QueryParam("id") final int seqId) {
        return ParamCache.find("" + seqId);
    }
}
