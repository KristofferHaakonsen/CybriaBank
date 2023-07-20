package com.kristofferhaakonsen.cybriabank.exception

import jakarta.ws.rs.WebApplicationException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class GeneralExceptionMapper : ExceptionMapper<Exception> {
    override fun toResponse(exception: Exception): Response {
        if (exception is WebApplicationException) {
            // Skip exceptions that are already handled by JAX-RS
            return exception.response
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build()
    }
}

