/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mypkg;


import java.io.IOException;

import com.mysql.jdbc.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("guests")
public class GuestsService {
    

    
    static String connectionURL="jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12304603" ;
    static String dbuname="sql12304603";
    static String dbpass="I5ZxjZYeCa";
    
   static String driverClass="com.mysql.jdbc.Driver";
   
    @GET  
    
  @Produces("text/html")
         public Response getAllGuests() throws SQLException 
    {
       String responsedata="";
       Connection con=null;
        try {
            Class.forName(driverClass);
        
           
           con = DriverManager.getConnection(connectionURL,dbuname,dbpass);
          Statement stat=con.createStatement();
           
           String query="SELECT * from T_GUESTLIST";
           ResultSet results = stat.executeQuery(query);
               
           while (results.next()) {
               String templine=results.getInt("ID")
                       +" , "+results.getString("NAME")
                       +" , "+results.getString("COUNT")
                       +" , "+results.getString("RESPONSE")
                       +" , "+results.getString("TIMESTAMP")+"<br>\n";
                    
               responsedata=responsedata+templine;
             }
            }
         catch (SQLException e) {
               
               responsedata= e.toString();
           }
        catch (ClassNotFoundException e) {
            
            responsedata= e.toString();
        }    
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            responsedata= e.toString();
            
        }
         finally
         {
             con.close();
         }
        return Response.status(200).entity(responsedata).build();
    } 

   
    @POST
     
  @Consumes("application/json") 
  @Produces("application/json")
     public Response addGuest(String input) throws IOException ,NullPointerException,SQLException
    {
         String responsedata="";
        String[] guest = input.split("//");
      responsedata= guest[0]+" : "+guest[1]+" : "+guest[2];
        
         Connection con=null;
         try
        {
           Class.forName(driverClass);
            con=DriverManager.getConnection(connectionURL,dbuname,dbpass);
           Statement stat=con.createStatement();
           
           
           
           String query="INSERT INTO T_GUESTLIST (NAME, COUNT,RESPONSE) VALUES ('"+guest[0]+"','"+guest[1]+"','"+guest[2]+"')";
                  int i=stat.executeUpdate(query);
                  
                  responsedata =Integer.toString(i);
                  
           
        }
        catch(SQLException e)
        {
            responsedata= e.toString();
        }
         catch(ClassNotFoundException e)
              {
            responsedata= e.toString();
        }
         catch(NullPointerException e)
         {
              responsedata= e.toString();
         }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            responsedata= e.toString();
            
        }
         finally
         {
             con.close();
         }
             
      return Response.ok(responsedata).build();
      
    }

    
 
     
     
}
