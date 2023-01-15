package data

import java.time.LocalDate

data class AllUserInfo(
    val id: Int,
    val name: String,
    val birthDate: LocalDate?,
    val favoriteColor: String?
) {
    constructor(mainUserInfo: MainUserInfo, userInfo: OptionalUserInfo?) : this(
        mainUserInfo.id,
        mainUserInfo.name,
        userInfo?.birthDate,
        userInfo?.favoriteColor
    )
}
