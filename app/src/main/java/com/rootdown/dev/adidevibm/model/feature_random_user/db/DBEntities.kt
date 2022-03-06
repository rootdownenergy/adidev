package com.rootdown.dev.adidevibm.model.feature_random_user.db


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.room.Entity
import androidx.room.PrimaryKey


@Serializable
@Entity(tableName = "users")
data class User(
    @PrimaryKey
    @SerialName("id") val userId: MutableList<String> = mutableListOf(),
    @SerialName("name") val name: MutableList<String> = mutableListOf(),
    @SerialName("location") val location: MutableList<String> = mutableListOf(),
    @SerialName("login") val login: MutableList<String> = mutableListOf(),
    @SerialName("dob") val dob: MutableList<String> = mutableListOf(),
    @SerialName("registered") val registered: MutableList<String> = mutableListOf(),
    @SerialName("picture") val picture: MutableList<String> = mutableListOf(),
    @SerialName("gender") val gender: String = "",
    @SerialName("email") val email: String = "",
    @SerialName("phone") val phone: String = "",
    @SerialName("cell") val cell: String = "",
    @SerialName("nat") val nat: String = "",
)


