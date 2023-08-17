package com.kristofferhaakonsen.cybriabank.biz.customer

import com.kristofferhaakonsen.cybriabank.biz.customer.db.CustomerRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockitoExtension::class)
class CustomerServiceTest {

    @Mock
    lateinit var customerRepository: CustomerRepository

    @InjectMocks
    lateinit var customerService: CustomerService

    private val mockCustomers = listOf(
        Customer(
            name = "Mock Customer 1",
            personalInformation = PersonalInformation(
                dateOfBirth = "01.01.2000",
                socialSecurityNumber = "1234567890"
            ),
            contactInformation = ContactInformation(email = "mock2@test.com", phone = "123456789"),
            address = Address(
                address = "Mock Street 1",
                postalCode = "1234",
                city = "Mock City",
                country = "Mock Country"
            )
        ),
        Customer(
            name = "Mock Customer 2",
            personalInformation = PersonalInformation(
                dateOfBirth = "02.02.2002",
                socialSecurityNumber = "234567891"
            ),
            contactInformation = ContactInformation(email = "mock2@test.com", phone = "123456789"),
            address = Address(
                address = "Mock Street 2",
                postalCode = "1234",
                city = "Mock City",
                country = "Mock Country"
            )
        )
    )

    @Test
    fun `Should get all customers`() {
        `when`(customerRepository.getAllCustomers()).thenReturn(mockCustomers)

        val customers = customerService.getAllCustomers()
        assertEquals(2, customers.size)
    }

    @Test
    fun `Should get customer by ssn`() {
        val ssn = "1234567890"
        `when`(customerRepository.getCustomer(ssn)).thenReturn(mockCustomers.find { it.personalInformation.socialSecurityNumber == ssn }!!)

        val customer = customerService.getCustomer(ssn)
        assertNotNull(customer)
        assertEquals(customer.personalInformation.socialSecurityNumber, ssn)
    }

}