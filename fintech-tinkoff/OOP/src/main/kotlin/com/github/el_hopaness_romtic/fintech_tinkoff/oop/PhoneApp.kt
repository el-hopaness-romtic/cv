package com.github.el_hopaness_romtic.fintech_tinkoff.oop

class PhoneApp(contact: Contact, val phoneID: String) : Channel(contact) {

    override fun getClientContact() = "${contact.name} (${contact.status}): $phoneID"

    override fun sendOffer(text: String) {
        // arguments processing
        println("\"${text.shorten()}\" was sent to a ${contact.status} client via notification ($phoneID)\n")
    }

    fun sendSilentNotification(text: String) {
        // arguments processing
        println("\"${text.shorten(0..25)}\" was sent to a ${contact.status} client via silent notification ($phoneID)\n")
    }
}