package data

import java.time.LocalDate

data class OptionalUserInfo(
    val userId: Int,
    val birthDate: LocalDate?,
    val favoriteColor: String?
)
