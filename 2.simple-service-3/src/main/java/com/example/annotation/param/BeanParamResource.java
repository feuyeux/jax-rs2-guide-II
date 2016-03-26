package com.example.annotation.param;

import com.example.annotation.param.bean.Jaxrs2GuideParam;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("bean-resource")
public class BeanParamResource {

    @GET
    @Path("{region:.+}/shenyang/{district:\\w+}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getByAddress(@BeanParam Jaxrs2GuideParam param) {
        return param.getRegionParam() + ":" + param.getDistrictParam() + ":" + param.getStationParam() + ":" + param.getVehicleParam();
    }
}
