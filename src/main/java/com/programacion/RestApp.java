package com.programacion;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.event.Observes;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.UUID;

//esta clase es similar a un servlet debido, a que no usamos servlets y lo mapeamos directamente a la raíz
@ApplicationPath("/")
public class RestApp extends Application {
//    @Inject
//    @ConfigProperty(name = "server.port", defaultValue = "7001")
//    private Integer puerto;
//
//    @Inject
//    @ConfigProperty(name = "consul.ip", defaultValue = "127.0.0.1")
//    private String consulIP;

    public static final String NAME = "mp";
    public static String ID;
    @PostConstruct
    public void inicializar(){
        ID = UUID.randomUUID().toString();
    }
//    @PreDestroy
//    public void destruir(){
//        System.out.println("***destruir");
//    }
//    public void init(@Observes @Initialized(ApplicationScoped.class)Object obj){
//        System.out.println("App.inicializada");
//        /**
//         * Registrar
//         *
//         * nombre: mp
//         * id: aleatoria
//         * ip: 127.0.0.1
//         * puerto
//         *
//         **/
//        Consul client = Consul.builder()
//                .whithHostAndPort(HostAndPort.fromParts(consulIP,8500))
//                .build();//connect on localhost
//
//        AgentClient agentClient = client.agentClient();
//
//        Registration service = InmutableRegistration.builder()
//                .id(ID)
//                .name(NAME)
//                .address("127.0.0.1")
//                .port(puerto)
//                .putMeta("ip","127.0.0.1")
//                .putMeta("puerto",puerto)
//                .build();
//        .agentClient.register(service);
//    }

    public void destroy(@Observes @Destroyed(ApplicationScoped.class)Object obj){
        System.out.println("App.destruida");
    }
}
