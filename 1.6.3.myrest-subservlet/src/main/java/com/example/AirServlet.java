package com.example;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import org.glassfish.jersey.servlet.ServletContainer;

@WebServlet(
initParams = @WebInitParam(name = "jersey.config.server.provider.packages", value = "com.example"), 
urlPatterns = "/webapi/*", 
loadOnStartup = 1)
public class AirServlet extends ServletContainer {
	private static final long serialVersionUID = 1L;
}
