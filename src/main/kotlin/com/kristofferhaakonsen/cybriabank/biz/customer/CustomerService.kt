package com.kristofferhaakonsen.cybriabank.biz.customer

import com.kristofferhaakonsen.cybriabank.biz.customer.db.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
) {
    fun getAllCustomers(): List<Customer> {
        return customerRepository.getAllCustomers()
    }

    fun getCustomer(ssn: String): Customer {
        return customerRepository.getCustomer(ssn)
    }

    fun createCustomer(customer: Customer) {
        customerRepository.createCustomer(customer)
    }

    fun deleteCustomer(ssn: String) {
        customerRepository.deleteCustomer(ssn)
    }

    fun updateContactInformation(ssn: String, contactInformation: ContactInformation) {
        customerRepository.updateContactInformation(ssn, contactInformation)
    }
}