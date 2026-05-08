package com.programacion.resource;

import com.programacion.domain.Product;
import com.programacion.repository.ProductRepository;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductRepository productRepository;

    @GET
    public List<Product> list() {
        return productRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Product findById(@PathParam("id") Long id) {
        return productRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));
    }

    @POST
    @RolesAllowed("admin")
    @Transactional
    public Response create(Product product) {
        productRepository.persist(product);
        return Response.status(Response.Status.CREATED).entity(product).build();
    }
}
