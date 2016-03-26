package com.example.annotation.param;

import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

@Path("path-resource")
public class PathResource {
    private static final Logger LOGGER = Logger.getLogger(PathResource.class);

    @GET
    /*[a-zA-Z0-9] is equivalent to \w*/
    @Path("{user: [a-zA-Z][a-zA-Z_0-9]*}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getUserInfo(@PathParam("user") final String user, @DefaultValue("Shen Yang") @QueryParam("hometown") final String hometown) {
        return user + ":" + hometown;
    }

    @GET
    @Path("{from:\\d+}-{to:\\d+}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getByCondition(@PathParam("from") final Integer from, @PathParam("to") final Integer to) {
        return "from=" + from + ":to=" + to;
    }

    @GET
    @Path("{beginMonth:\\d+},{beginYear:\\d+}-{endMonth:\\d+},{endYear:\\d+}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getByCondition2(@PathParam("beginMonth") final String m1, @PathParam("beginYear") final Integer y1, @PathParam("endMonth") final String m2,
                                  @PathParam("endYear") final Integer y2) {
        return y1 + "." + m1 + "~" + y2 + "." + m2;
    }

    /*path-resource/q/restful;program=java;type=web*/
    @GET
    @Path("q/{condition}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getByCondition3(@PathParam("condition") final PathSegment condition) {
        final StringBuilder conds = new StringBuilder();
        conds.append(condition.getPath()).append(" ");
        final MultivaluedMap<String, String> matrixParameters = condition.getMatrixParameters();
        final Iterator<Entry<String, List<String>>> iterator = matrixParameters.entrySet().iterator();
        while (iterator.hasNext()) {
            final Entry<String, List<String>> entry = iterator.next();
            conds.append(entry.getKey()).append("=");
            conds.append(entry.getValue()).append(" ");
        }
        return conds.toString();
    }

    /*path-resource/q2/restful;program=java;type=web*/
    @GET
    @Path("q2/{condition}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getByCondition4(@PathParam("condition") final PathSegment condition, @MatrixParam("program") final String program,
                                  @MatrixParam("type") final String type) {
        return condition.getPath() + " program=[" + program + "] type=[" + type + "]";
    }

    @GET
    @Path("{region:.+}/shenyang/{district:\\w+}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getByAddress(@PathParam("region") final List<PathSegment> region, @PathParam("district") final String district) {
        final StringBuilder result = new StringBuilder();
        for (final PathSegment pathSegment : region) {
            result.append(pathSegment.getPath()).append("-");
        }
        result.append("shenyang-").append(district);
        final String r = result.toString();
        PathResource.LOGGER.debug(r);
        return r;
    }
}
