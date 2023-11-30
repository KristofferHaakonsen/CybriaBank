package com.kristofferhaakonsen.cybriabank.biz.customer.db

import com.kristofferhaakonsen.cybriabank.biz.customer.Address
import com.kristofferhaakonsen.cybriabank.biz.customer.ContactInformation
import com.kristofferhaakonsen.cybriabank.biz.customer.Customer
import com.kristofferhaakonsen.cybriabank.biz.customer.PersonalInformation
import jakarta.ws.rs.NotFoundException
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.Date
import java.time.LocalDate

@Repository
class CustomerRepository(
    private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate
) {

    fun getCustomer(ssn: String): Customer {
        val sql = "SELECT * FROM CUSTOMER WHERE social_security_number = :ssn"
        val result = namedParameterJdbcTemplate.query(sql, mapOf("ssn" to ssn)) { rs, _ ->
            Customer(
                name = rs.getString("name"), personalInformation = PersonalInformation(
                    dateOfBirth = rs.getString("date_of_birth"),
                    socialSecurityNumber = rs.getString("social_security_number")
                ), contactInformation = ContactInformation(
                    email = rs.getString("email"), phone = rs.getString("phone")
                ), address = Address(
                    address = rs.getString("address"),
                    postalCode = rs.getString("postal_code"),
                    city = rs.getString("city"),
                    country = rs.getString("country")
                )
            )
        }
            .firstOrNull()

        if (result != null) {
            return result
        } else {
            throw NotFoundException("Customer not found")
        }
    }


    fun getAllCustomers(): List<Customer> {
        return namedParameterJdbcTemplate.query("SELECT * FROM CUSTOMER") { rs, _ ->
            Customer(
                name = rs.getString("name"), personalInformation = PersonalInformation(
                    dateOfBirth = rs.getString("date_of_birth"),
                    socialSecurityNumber = rs.getString("social_security_number")
                ), contactInformation = ContactInformation(
                    email = rs.getString("email"), phone = rs.getString("phone")
                ), address = Address(
                    address = rs.getString("address"),
                    postalCode = rs.getString("postal_code"),
                    city = rs.getString("city"),
                    country = rs.getString("country")
                )
            )
        }
    }

    fun createCustomer(customer: Customer) {
        val sql =
            "INSERT INTO CUSTOMER (name, date_of_birth, social_security_number, email, phone, address, postal_code, city, country) VALUES (:name, :date_of_birth, :social_security_number, :email, :phone, :address, :postal_code, :city, :country)"
        val localDate = LocalDate.parse(customer.personalInformation.dateOfBirth)
        val sqlDate = Date.valueOf(localDate)
        namedParameterJdbcTemplate.update(
            sql.trimIndent(), mapOf(
                "name" to customer.name,
                "date_of_birth" to sqlDate,
                "social_security_number" to customer.personalInformation.socialSecurityNumber,
                "email" to customer.contactInformation.email,
                "phone" to customer.contactInformation.phone,
                "address" to customer.address.address,
                "postal_code" to customer.address.postalCode,
                "city" to customer.address.city,
                "country" to customer.address.country
            )
        )

    }

    fun deleteCustomer(ssn: String) {
        val sql = "DELETE FROM CUSTOMER WHERE social_security_number = :ssn"
        val result = namedParameterJdbcTemplate.update(sql, mapOf("ssn" to ssn))
        if (result == 0) {
            throw NotFoundException("Customer not found")
        }
    }

    fun updateContactInformation(ssn: String, contactInformation: ContactInformation) {
        val sql = "UPDATE CUSTOMER SET email = :email, phone = :phone WHERE social_security_number = :ssn"
        val result = namedParameterJdbcTemplate.update(
            sql, mapOf(
                "email" to contactInformation.email, "phone" to contactInformation.phone, "ssn" to ssn
            )
        )

        if (result == 0) {
            throw NotFoundException("Customer not found")
        }
    }

    fun updateAddress(ssn: String, address: Address) {
        val sql =
            "UPDATE CUSTOMER SET address = :address, postalCode = :postal_code, city = :city, country = :country WHERE social_security_number = :ssn"
        val result = namedParameterJdbcTemplate.update(
            sql, mapOf(
                "address" to address.address,
                "postal_code" to address.postalCode,
                "city" to address.city,
                "country" to address.country,
                "ssn" to ssn
            )
        )

        if (result == 0) {
            throw NotFoundException("Customer not found")
        }
    }
}