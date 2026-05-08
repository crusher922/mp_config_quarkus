package com.programacion.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/api/health")
public class HealthResource {

    @GET
    public String health() {
        return "ok";
    }
}
