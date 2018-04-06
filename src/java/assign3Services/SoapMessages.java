/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign3Services;

import DB.MessageData;
import DB.SqliteDB;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author klr232
 */
@WebService(serviceName = "SoapMessages")
public class SoapMessages {

    @WebMethod(operationName = "get")
    public ArrayList<MessageData> get() {
        return SqliteDB.getInstance().getMessages();
    }

    @WebMethod(operationName = "post")
    public void post(MessageData data) {
        SqliteDB.getInstance().insert(data.save());
    }

    @WebMethod(operationName = "post2")
    public void post2(@WebParam(name = "user") String user, @WebParam(name = "message") String message) {
        MessageData msg = new MessageData();
        msg.message = message;
        msg.name = user;
        msg.timestamp = LocalDateTime.now();
        SqliteDB.getInstance().insert(msg.save());
    }
}