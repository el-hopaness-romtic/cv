package com.github.el_hopaness_romtic.fintech_tinkoff.oop

data class Contact(val name: String, var status: Channel.ClientStatus)

abstract class Channel(val contact: Contact) {
    enum class ClientStatus {
        STANDARD, VIP
    }

    abstract fun getClientContact(): String
    abstract fun sendOffer(text: String)

    fun String.shorten() = replace("%CLIENT_NAME%", contact.name).substring(0, 16) + "..."
    fun String.shorten(range: IntRange) = replace("%CLIENT_NAME%", contact.name).substring(range) + "..."
}