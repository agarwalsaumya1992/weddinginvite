/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mypkg;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author saumya agarwal
 */
@ApplicationPath("api")
public class ApplicationConfig extends Application { 
 
       @Override
       public Set<Class<?>> getClasses() { 
              Set<Class<?>> resources = new java.util.HashSet<>(); 
              addRestResourceClasses(resources); 
              return resources; 
       }

       private void addRestResourceClasses(Set<Class<?>> resources) {  
      //Make an entry for every resource,filters,interceptors created 
        resources.add(com.mypkg.GuestsService.class);
        
        
       } 
}