package com.github.el_hopaness_romtic.fintech_tinkoff.oop

fun main() {
    val contacts = listOf<Channel>(
        Email(Contact("Белов Владислав", Channel.ClientStatus.VIP), EmailAddress("vladislav007", "gmail.com")),
        PhoneApp(Contact("Голубева Ксения", Channel.ClientStatus.STANDARD), "Android/iitsfg8d465g1h"),
        Email(Contact("Юдина Елизавета", Channel.ClientStatus.STANDARD), EmailAddress("udina1998", "mail.ru")),
        Phone(Contact("Петров Глеб", Channel.ClientStatus.STANDARD), PhoneNumber("7", "3657891438")),
        Phone(Contact("Аксёнов Денис", Channel.ClientStatus.STANDARD), PhoneNumber("375", "8791959562")),
        PhoneApp(Contact("Новикова Алиса", Channel.ClientStatus.VIP), "iOS/ty8r9h46df5g1b3c")
    )

    contacts.forEach { it.sendOffer("Акция: 3 сырка по цене 2! Успейте до 22.02") }

    println("--------------------------------\n")

    contacts
        .filterIsInstance<PhoneApp>()
        .forEach { it.sendSilentNotification("Рекомендуется обновить приложение") }

    println("--------------------------------\n")

    contacts
        .filterIsInstance<Phone>()
        .forEach { println(it.getClientContact()) }

    println("\n--------------------------------\n")

    contacts
        .filter { it.contact.status == Channel.ClientStatus.VIP }
        .forEach { it.sendOffer("%CLIENT_NAME%, для вас доступно специальное предложение: . . . ") }
}