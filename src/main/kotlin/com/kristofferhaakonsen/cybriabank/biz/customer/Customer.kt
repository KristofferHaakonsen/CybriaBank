package com.kristofferhaakonsen.cybriabank.biz.customer

data class Customer(
    val name: String,
    val personalInformation: PersonalInformation,
    val contactInformation: ContactInformation,
    val address: Address
)