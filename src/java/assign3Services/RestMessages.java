/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign3Services;

import DB.MessageData;
import DB.SqliteDB;
import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author klr232
 */
@Path("RestMessages")
public class RestMessages {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RestMessages
     */
    public RestMessages() {
    }

    /**
     * Retrieves representation of an instance of assign3Services.RestMessages
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        Gson gson = new Gson();
        return gson.toJson(SqliteDB.getInstance().getMessages());
    }

    /**
     * POST method for creating an instance of RestMessages
     * @param content representation for the resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void postJson(String content) {
        Gson gson = new Gson();
        System.out.println(content);
        MessageData obj = gson.fromJson(content, MessageData.class);
        SqliteDB.getInstance().insert(obj.save());
    }
    
    
}