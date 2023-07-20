package com.kristofferhaakonsen.cybriabank.model.customer

data class Address(val address: String, val postalCode: String, val city: String, val country: String)

data class ContactInformation(val email: String, val phone: String)

data class PersonalInformation(val dateOfBirth: String, val socialSecurityNumber: String)

data class Customer(
    val name: String,
    val personalInformation: PersonalInformation,
    val contactInformation: ContactInformation,
    val address: Address
)