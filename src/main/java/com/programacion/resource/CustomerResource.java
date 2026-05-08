package com.programacion.resource;

import com.programacion.domain.Customer;
import com.programacion.repository.CustomerRepository;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    @Inject
    CustomerRepository customerRepository;

    @GET
    @RolesAllowed({"admin", "seller"})
    public List<Customer> list() {
        return customerRepository.listAll();
    }

    @POST
    @Transactional
    public Response create(Customer customer) {
        customerRepository.persist(customer);
        return Response.status(Response.Status.CREATED).entity(customer).build();
    }
}
