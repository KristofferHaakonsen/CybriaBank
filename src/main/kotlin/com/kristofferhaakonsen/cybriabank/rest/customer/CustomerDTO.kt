package com.kristofferhaakonsen.cybriabank.rest.customer

import com.kristofferhaakonsen.cybriabank.biz.customer.Address
import com.kristofferhaakonsen.cybriabank.biz.customer.ContactInformation
import com.kristofferhaakonsen.cybriabank.biz.customer.Customer
import com.kristofferhaakonsen.cybriabank.biz.customer.PersonalInformation

data class CustomerDTO(
    val name: String,
    val personalInformation: PersonalInformationDTO,
    val contactInformation: ContactInformationDTO,
    val address: AddressDTO
)

fun Customer.toDTO(): CustomerDTO {
    return CustomerDTO(
        name = this.name,
        personalInformation = this.personalInformation.toDTO(),
        contactInformation = this.contactInformation.toDTO(),
        address = this.address.toDTO()
    )
}
    
fun CustomerDTO.toCustomer(): Customer {
    return Customer(
        name = this.name,
        personalInformation = this.personalInformation.toPersonalInformation(),
        contactInformation = this.contactInformation.toContactInformation(),
        address = this.address.toAddress()
    )
}


data class PersonalInformationDTO(
    val dateOfBirth: String,
    val socialSecurityNumber: String
)

fun PersonalInformationDTO.toPersonalInformation(): PersonalInformation {
    return PersonalInformation(
        dateOfBirth = this.dateOfBirth,
        socialSecurityNumber = this.socialSecurityNumber
    )
}

fun PersonalInformation.toDTO(): PersonalInformationDTO {
    return PersonalInformationDTO(
        dateOfBirth = this.dateOfBirth,
        socialSecurityNumber = this.socialSecurityNumber
    )
}


data class ContactInformationDTO(
    val email: String,
    val phone: String
)

fun ContactInformationDTO.toContactInformation(): ContactInformation {
    return ContactInformation(
        email = this.email,
        phone = this.phone
    )
}

fun ContactInformation.toDTO(): ContactInformationDTO {
    return ContactInformationDTO(
        email = this.email,
        phone = this.phone
    )
}

data class AddressDTO(
    val address: String,
    val postalCode: String,
    val city: String,
    val country: String
)

fun AddressDTO.toAddress(): Address {
    return Address(
        address = this.address,
        postalCode = this.postalCode,
        city = this.city,
        country = this.country
    )
}

fun Address.toDTO(): AddressDTO {
    return AddressDTO(
        address = this.address,
        postalCode = this.postalCode,
        city = this.city,
        country = this.country
    )
}



