package com.programacion;


import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.time.LocalDateTime;


@Path(value = "/")
@ApplicationScoped
public class HolaMundoRest {

    @Inject
    @ConfigProperty(name = "mensaje")
    private String mensaje;

    @Inject
    @ConfigProperty(name = "quarkus.http.port", defaultValue = "7001")
    private Integer puerto;


    @GET
    @Path("/hola")
    public String hola(){

        Config cfg = ConfigProvider.getConfig(); //referencia a config


        cfg.getConfigSources()
                .forEach(s->{
                    System.out.printf("%d: %s\n",s.getOrdinal(),s.getName());
                });


        return String.format("(%d): %s %s ",puerto,mensaje ,LocalDateTime.now());
    }
}
