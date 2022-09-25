package com.github.el_hopaness_romtic.fintech_tinkoff.oop

data class EmailAddress(val username: String, val domain: String) {
    override fun toString() = "$username@$domain"
}

class Email(contact: Contact, val email: EmailAddress) : Channel(contact) {

    override fun getClientContact() = "${contact.name} (${contact.status}): $email"

    override fun sendOffer(text: String) {
        // arguments processing
        println("\"${text.shorten()}\" was sent to ${contact.status} client via EMail ($email)\n")
    }
}