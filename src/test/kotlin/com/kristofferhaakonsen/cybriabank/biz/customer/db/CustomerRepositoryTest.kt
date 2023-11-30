package com.kristofferhaakonsen.cybriabank.biz.customer.db

import com.kristofferhaakonsen.cybriabank.biz.customer.Address
import com.kristofferhaakonsen.cybriabank.biz.customer.ContactInformation
import com.kristofferhaakonsen.cybriabank.biz.customer.Customer
import com.kristofferhaakonsen.cybriabank.biz.customer.PersonalInformation
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

@SpringBootTest
class CustomerRepositoryTests(
    @Autowired val customerRepository: CustomerRepository,
    @Autowired val jdbcTemplate: NamedParameterJdbcTemplate
) {

    val customer1 = Customer(
        name = "Test Testesen",
        personalInformation = PersonalInformation(dateOfBirth = "01.01.1970", socialSecurityNumber = "11111111111"),
        contactInformation = ContactInformation(email = "test1@test.com", phone = "12345678"),
        address = Address(address = "Testveien 1", postalCode = "1234", city = "Testby", country = "Testland")
    )

    val customer2 = Customer(
        name = "Line linesen",
        personalInformation = PersonalInformation(dateOfBirth = "05.05.1955", socialSecurityNumber = "555555555"),
        contactInformation = ContactInformation(email = "test2@test.com", phone = "12345678"),
        address = Address(address = "Testveien 2", postalCode = "1234", city = "Testby", country = "Testland")
    )

    val customer3 = Customer(
        name = "Gunnar Gunnersen",
        personalInformation = PersonalInformation(dateOfBirth = "09.09.1999", socialSecurityNumber = "99999999999"),
        contactInformation = ContactInformation(email = "test3@test.com", phone = "12345678"),
        address = Address(address = "Testveien 3", postalCode = "1234", city = "Testby", country = "Testland")
    )

    val listOfCustomers = listOf(customer1, customer2, customer3)


    @Test
    fun `test getCustomer retrieves correct customer`() {
        //customerRepository.createCustomer(customer1)

        // Act: Retrieve the customer using the repository
        //val found = customerRepository.getCustomer(customer1.personalInformation.socialSecurityNumber)
        val found = customerRepository.getAllCustomers()
        println(found)

        // Assert: Check that the retrieved customer matches the test data
        assertNotNull(found)
        //assertEquals(customer1.name, found.name)
    }
}