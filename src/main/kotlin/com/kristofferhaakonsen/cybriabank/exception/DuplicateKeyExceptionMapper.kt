package com.kristofferhaakonsen.cybriabank.exception

import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider
import org.springframework.dao.DuplicateKeyException

@Provider
class DuplicateKeyExceptionMapper : ExceptionMapper<DuplicateKeyException> {

    override fun toResponse(exception: DuplicateKeyException): Response {
        return Response.status(Response.Status.CONFLICT)
            .build()
    }
}