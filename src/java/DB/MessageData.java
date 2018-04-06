package DB;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kayland's Surface
 */
public class MessageData {

    public String name;
    public LocalDateTime timestamp;
    public String message;

    public MessageData() {
    }

    public String save() {
        return "INSERT INTO MESSAGE (NAME,TIME,MESSAGE) "
                + "VALUES ('" + name + "','" + timestamp + "','" + message + "');";
    }
}
