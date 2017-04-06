package com.example.client;

import static javax.ws.rs.HttpMethod.DELETE;
import static javax.ws.rs.HttpMethod.GET;
import static javax.ws.rs.HttpMethod.POST;
import static javax.ws.rs.HttpMethod.PUT;

import java.util.Collections;
import java.util.Set;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.glassfish.grizzly.utils.Pair;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;

public class PoolingClient extends Jaxrs2Client {
    public PoolingClient() {
        buildClient();
    }

    void buildClient() {
        final ClientConfig clientConfig = new ClientConfig();
        final PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(20000);
        cm.setDefaultMaxPerRoute(10000);
        clientConfig.property(ApacheClientProperties.CONNECTION_MANAGER, cm);
        clientConfig.connectorProvider(new ApacheConnectorProvider());
        client = ClientBuilder.newClient(clientConfig);
        checkConfig();
    }


    public <T> T rest(String method, String requestUrl,
                  Set<Pair<String, Object>> headParams, Set<Pair<String, Object>> queryParams,
                  MediaType requestDataType, Class<T> returnType, Object requestData) {
        //关注点1：构造Client
        if (clientConfig == null) {
            clientConfig = new ClientConfig();
        }
        Client client = ClientBuilder.newClient(clientConfig);

        //关注点2：构造WebTarget
        WebTarget webTarget = client.target(requestUrl);
        for (Pair<String, Object> queryPair : queryParams) {
            webTarget = webTarget.queryParam(queryPair.getFirst(), queryPair.getSecond());
        }
        //关注点3：构造Invocation.Builder
        Invocation.Builder invocationBuilder = webTarget.request(requestDataType);
        for (Pair<String, Object> headParam : headParams) {
            invocationBuilder.header(headParam.getFirst(), headParam.getSecond());
        }
        //关注点4：发起请求和结果处理
        javax.ws.rs.core.Response response;
        Entity<Object> entity;
        switch (method) {
            case GET:
                response = invocationBuilder.get();
                return response.readEntity(returnType);
            case DELETE:
                response = invocationBuilder.delete();
                return response.readEntity(returnType);
            case PUT:
                entity = Entity.entity(requestData, requestDataType);
                response = invocationBuilder.put(entity);
                return response.readEntity(returnType);
            case POST:
                entity = Entity.entity(requestData, requestDataType);
                response = invocationBuilder.post(entity);
                return response.readEntity(returnType);
            default:
                return null;
        }
    }
    
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        
        PoolingClient client = new PoolingClient();
       String  s  = client.rest(HttpMethod.GET, "https://github.com/", Collections.EMPTY_SET, Collections.EMPTY_SET, MediaType.TEXT_HTML_TYPE, String.class, null);
        System.out.println(s);
    }
    
    
    
}
