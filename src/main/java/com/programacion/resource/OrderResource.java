package com.programacion.resource;

import com.programacion.domain.CustomerOrder;
import com.programacion.dto.CreateOrderRequest;
import com.programacion.repository.OrderRepository;
import com.programacion.service.OrderService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    @Inject
    OrderService orderService;

    @Inject
    OrderRepository orderRepository;

    @GET
    @RolesAllowed({"admin", "seller"})
    public List<CustomerOrder> list() {
        return orderRepository.listAll();
    }

    @POST
    @RolesAllowed({"admin", "seller"})
    public Response create(CreateOrderRequest request) {
        CustomerOrder order = orderService.createOrder(request);
        return Response.status(Response.Status.CREATED).entity(order).build();
    }
}
