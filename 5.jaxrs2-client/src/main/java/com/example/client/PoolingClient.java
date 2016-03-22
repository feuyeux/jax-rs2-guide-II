package com.example.client;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.glassfish.grizzly.utils.Pair;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;

import java.util.Set;

import static javax.ws.rs.HttpMethod.*;

import org.glassfish.grizzly.utils.Pair;

public class PoolingClient<T> extends Jaxrs2Client {
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


    public T rest(String method, String requestUrl,
                  Set<Pair<String, Object>> headParams, Set<Pair<String, Object>> queryParams,
                  MediaType requestDataType, Class<T> returnType, T requestData) {
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
        Entity<T> entity;
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
}
