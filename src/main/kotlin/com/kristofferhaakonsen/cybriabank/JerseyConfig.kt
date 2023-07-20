package com.kristofferhaakonsen.cybriabank

import com.kristofferhaakonsen.cybriabank.exception.DuplicateKeyExceptionMapper
import com.kristofferhaakonsen.cybriabank.exception.GeneralExceptionMapper
import com.kristofferhaakonsen.cybriabank.exception.NotFoundExceptionMapper
import com.kristofferhaakonsen.cybriabank.rest.CustomerResource
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.stereotype.Component


@Component
class JerseyConfig : ResourceConfig() {
    init {
        register(CustomerResource::class.java)

        // Exception mappers
        register(GeneralExceptionMapper::class.java)
        register(NotFoundExceptionMapper::class.java)
        register(DuplicateKeyExceptionMapper::class.java)
    }
}