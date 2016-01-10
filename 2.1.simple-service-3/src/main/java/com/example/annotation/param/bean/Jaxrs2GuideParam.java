package com.example.annotation.param.bean;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

public class Jaxrs2GuideParam {
    @HeaderParam("accept")
    private String acceptParam;
    @PathParam("region")
    private String regionParam;
    @PathParam("district")
    private String districtParam;
    @QueryParam("station")
    private String stationParam;
    @QueryParam("vehicle")
    private String vehicleParam;


    public String getAcceptParam() {
        return acceptParam;
    }

    public void setAcceptParam(String acceptParam) {
        this.acceptParam = acceptParam;
    }

    public String getRegionParam() {
        return regionParam;
    }

    public void setRegionParam(String regionParam) {
        this.regionParam = regionParam;
    }

    public String getDistrictParam() {
        return districtParam;
    }

    public void setDistrictParam(String districtParam) {
        this.districtParam = districtParam;
    }

    public String getStationParam() {
        return stationParam;
    }

    public void setStationParam(String stationParam) {
        this.stationParam = stationParam;
    }

    public String getVehicleParam() {
        return vehicleParam;
    }

    public void setVehicleParam(String vehicleParam) {
        this.vehicleParam = vehicleParam;
    }

}
