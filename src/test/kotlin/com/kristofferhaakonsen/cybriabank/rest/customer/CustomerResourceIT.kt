package com.kristofferhaakonsen.cybriabank.rest.customer

import jakarta.ws.rs.core.Application
import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.test.JerseyTest

class CustomerResourceIT : JerseyTest() {
    override fun configure(): Application {
        return ResourceConfig(CustomerResource::class.java)
    }



}
