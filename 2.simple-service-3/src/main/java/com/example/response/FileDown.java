package com.example.response;

import java.io.File;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 文件下载
 * 
 * @author and0429
 *
 */
@Path("down")
public class FileDown {
    /**
     * 
     * @param image
     * @return
     */
    @GET
    @Path("/file")
    public Response getImage() {
      File f = new File(this.getClass().getClassLoader().getResource("log4j.xml").getPath());
      if (!f.exists()) {
        throw new WebApplicationException(404);
      }
      String mt = new MimetypesFileTypeMap().getContentType(f);
      return Response.ok(f, mt).header("Content-Disposition","attachment;filename=log4j.pdf").build();
    }
    

    /**
     * 
     * @param response
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public File down(@Context HttpServletResponse response) {
        String pathname = this.getClass().getClassLoader().getResource("log4j.xml").getPath();
        
        response.setHeader("Content-Disposition","attachment;filename=log4j.pdf");//为文件命名  
        response.addHeader("content-type","application/pdf");  
        
        return new File(pathname);
    }

}
