package com.example.annotation.param;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Root resource (exposed at "kukyresource" path)
 */
@Path("kuky-resource")
public class CookieResource {
    @GET
    public String getHeaderParams(@CookieParam("longitude") final String longitude,
                                  @CookieParam("latitude") final String latitude,
                                  @CookieParam("population") final double population,
                                  @CookieParam("area") final int area) {
        return longitude + "," + latitude + " population=" + population + ",area=" + area;
    }
}
