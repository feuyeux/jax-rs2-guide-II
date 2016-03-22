package com.example.client;

import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

public class ApacheClient extends Jaxrs2Client {
    public ApacheClient() {
        buildClient();
    }

    void buildClient() {
        final ClientConfig clientConfig = new ClientConfig();
        /**代理服务器配置
        clientConfig.property(ClientProperties.PROXY_URI, "http://192.168.0.100");
        clientConfig.property(ClientProperties.PROXY_USERNAME, "erichan");
        clientConfig.property(ClientProperties.PROXY_PASSWORD , "han");
        clientConfig.property(ClientProperties.CONNECT_TIMEOUT, 1000);
        clientConfig.property(ClientProperties.READ_TIMEOUT, 2000);
        **/
        clientConfig.connectorProvider(new ApacheConnectorProvider());
        client = ClientBuilder.newClient(clientConfig);
        checkConfig();
    }
}
