package com.example;

import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(
initParams = @WebInitParam(name = "jersey.config.server.provider.packages", value = "com.example"), 
urlPatterns = "/webapi/*", 
loadOnStartup = 1)
public class AirServlet extends ServletContainer {
	private static final long serialVersionUID = 1L;
}
