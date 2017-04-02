package com.example.resource;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api/*")
public class JsonResourceConfig extends ResourceConfig {
    public JsonResourceConfig() {
        register(BookResource.class);
        
        /**
         * 自动探索Moxy更能关闭后。 没有指定json解析器，就报错了
         */
//        property(org.glassfish.jersey.CommonProperties.MOXY_JSON_FEATURE_DISABLE, true);   
    }
}
