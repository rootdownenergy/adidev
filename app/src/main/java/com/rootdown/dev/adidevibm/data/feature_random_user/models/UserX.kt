package com.rootdown.dev.adidevibm.data.feature_random_user.models

import android.os.Parcelable
import com.rootdown.dev.adidevibm.data.feature_random_user.repo.util.User
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@JsonClass(generateAdapter = true)
data class UserX(
    val gender: String? = null,
    val name: Name? = null,
    val location: Location? = null,
    val email: String? = null,
    val login: Login? = null,
    val dob: Dob? = null,
    val registered: Dob? = null,
    val phone: String? = null,
    val cell: String? = null,
    val id: Id? = null,
    val picture: Picture? = null,
    val nat: String? = null
) : Parcelable {
    // static
    companion object {
        fun createRandom(): UserX {
            return UserX(
                name = Name(first = randomString(), last = randomString()),
                location = Location(coordinates = Coordinates(randomDouble().toString(), randomDouble().toString())),
                email = randomString() + "@gmail.com",
                dob = Dob(age = 25)
            )
        }

        private fun randomString() = UUID.randomUUID().toString().take(6)
        private fun randomDouble() = Random().nextDouble() * 100
    }
}