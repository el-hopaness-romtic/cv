package com.github.el_hopaness_romtic.fintech_tinkoff.oop

data class PhoneNumber(val code: String, val number: String) {
    override fun toString() = "+$code$number"
}

class Phone(contact: Contact, val phoneNumber: PhoneNumber) : Channel(contact) {

    override fun getClientContact() = "${contact.name} (${contact.status}): $phoneNumber"

    override fun sendOffer(text: String) {
        // arguments processing
        println("\"${text.shorten()}\" was sent to a ${contact.status} client via SMS ($phoneNumber)\n")
    }
}