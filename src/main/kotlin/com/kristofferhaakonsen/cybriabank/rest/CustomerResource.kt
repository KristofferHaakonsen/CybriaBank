package com.kristofferhaakonsen.cybriabank.rest

import com.kristofferhaakonsen.cybriabank.biz.customer.CustomerService
import com.kristofferhaakonsen.cybriabank.model.customer.ContactInformationDTO
import com.kristofferhaakonsen.cybriabank.model.customer.CustomerDTO
import com.kristofferhaakonsen.cybriabank.model.customer.toContactInformation
import com.kristofferhaakonsen.cybriabank.model.customer.toCustomer
import com.kristofferhaakonsen.cybriabank.model.customer.toDTO
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.PATCH
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.Context
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.core.UriInfo
import org.springframework.stereotype.Component


@Component
@Path("/customer")
class CustomerResource(
    private val customerService: CustomerService
) : CustomerContract {


    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    override fun getAllCustomers(): Response {
        val customers = customerService.getAllCustomers()
        return Response.ok(customers.map { it.toDTO() })
            .build()
    }


    @GET
    @Path("/{ssn}")
    @Produces(MediaType.APPLICATION_JSON)
    override fun getCustomer(@PathParam("ssn") ssn: String): Response {
        if (ssn.length != 11) {
            return Response.status(Response.Status.BAD_REQUEST)
                .build()
        }

        val customer = customerService.getCustomer(ssn)

        return Response.ok(customer.toDTO())
            .build()

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    override fun createCustomer(customer: CustomerDTO, @Context uriInfo: UriInfo): Response {
        customerService.createCustomer(customer.toCustomer())

        val uri = uriInfo.absolutePathBuilder.path(customer.personalInformation.socialSecurityNumber)
            .build()
        val createdCustomer = customerService.getCustomer(customer.personalInformation.socialSecurityNumber)

        return Response.created(uri)
            .entity(createdCustomer.toDTO())
            .build()


    }

    @PATCH
    @Path("/{ssn}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    override fun updateContactInformation(
        @PathParam("ssn") ssn: String, contactInformation: ContactInformationDTO
    ): Response {
        customerService.updateContactInformation(ssn, contactInformation.toContactInformation())

        val updatedCustomer = customerService.getCustomer(ssn)
        return Response.ok(updatedCustomer.toDTO())
            .build()
    }

    @DELETE
    @Path("/{ssn}")
    @Produces(MediaType.APPLICATION_JSON)
    override fun deleteCustomer(@PathParam("ssn") ssn: String): Response {
        if (ssn.length != 11) {
            return Response.status(Response.Status.BAD_REQUEST)
                .build()
        }

        customerService.deleteCustomer(ssn)
        return Response.ok()
            .build()

    }

}