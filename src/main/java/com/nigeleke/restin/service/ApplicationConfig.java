package com.nigeleke.restin.service;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        // following code can be used to customize Jersey 2.0 JSON provider:
        try {
            // Class jsonProvider = Class.forName("org.glassfish.jersey.jackson.JacksonFeature");
            Class jsonProvider = Class.forName("org.glassfish.jersey.moxy.json.MoxyJsonFeature");
            // Class jsonProvider = Class.forName("org.glassfish.jersey.jettison.JettisonFeature");
            resources.add(jsonProvider);
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically re-generated by NetBeans REST support to populate
     * given list with all resources defined in the project.
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.nigeleke.restin.service.AnEntityFacadeREST.class);
    }

}
