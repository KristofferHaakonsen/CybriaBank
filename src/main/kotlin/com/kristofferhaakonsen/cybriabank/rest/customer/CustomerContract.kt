package com.kristofferhaakonsen.cybriabank.rest.customer

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.ws.rs.HttpMethod
import jakarta.ws.rs.core.Context
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.core.UriInfo

@OpenAPIDefinition(
    info = Info(
        title = "Customer API",
        description = "API for managing customers"
    ),
    tags = [Tag(name = "v1")]
)
interface CustomerContract {
    @Operation(
        summary = "Get all customers",
        method = HttpMethod.GET,
        responses = [ApiResponse(content = [Content(schema = Schema(implementation = CustomerDTO::class))])]
    )
    fun getAllCustomers(): Response

    @Operation(
        summary = "Get customer by social security number",
        method = HttpMethod.GET,
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Customer found",
                content = [Content(schema = Schema(implementation = CustomerDTO::class))]
            ),
            ApiResponse(
                responseCode = "400",
                description = "Invalid social security number",
            ),
            ApiResponse(
                responseCode = "404",
                description = "Customer not found",
            )
        ]
    )
    fun getCustomer(ssn: String): Response

    @Operation(
        summary = "Create customer",
        method = HttpMethod.POST,
        requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Customer to create",
            required = true,
            content = [Content(schema = Schema(implementation = CustomerDTO::class))]
        ),
        responses = [
            ApiResponse(
                responseCode = "201",
                description = "Customer created",
            ),
            ApiResponse(
                responseCode = "400",
                description = "Bad request",
            ),
            ApiResponse(
                responseCode = "409",
                description = "Customer already exists",
            )
        ]
    )
    fun createCustomer(customer: CustomerDTO, @Context uriInfo: UriInfo): Response

    @Operation(
        summary = "Delete customer",
        method = HttpMethod.POST,
        parameters = [Parameter(
            name = "ssn",
            description = "Social security number of customer to delete",
            required = true,
            schema = Schema(implementation = String::class)
        )],
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Customer deleted",
            ),
            ApiResponse(
                responseCode = "404",
                description = "Customer not found",
            )
        ]
    )
    fun deleteCustomer(ssn: String): Response

    @Operation(
        summary = "Update contact information",
        method = HttpMethod.PATCH,
        parameters = [Parameter(
            name = "ssn",
            description = "Social security number of customer to update",
            required = true,
            schema = Schema(implementation = String::class)
        )],
        requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Updated contact information",
            required = true,
            content = [Content(schema = Schema(implementation = ContactInformationDTO::class))]
        ),
        responses = [
            ApiResponse(
                responseCode = "204",
                description = "Customer updated",
            ),
            ApiResponse(
                responseCode = "404",
                description = "Customer not found",
            )
        ]
    )
    fun updateContactInformation(ssn: String, contactInformation: ContactInformationDTO): Response
}