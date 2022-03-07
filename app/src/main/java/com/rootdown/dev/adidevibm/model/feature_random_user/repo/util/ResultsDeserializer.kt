package com.rootdown.dev.adidevibm.model.feature_random_user.repo.util

import android.util.Log
import com.google.gson.*
import java.lang.Exception
import java.lang.reflect.Type

class ResultsDeserializer : JsonDeserializer<User> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): User {
        json as JsonObject

        val gender = json.get("gender").toString()
        val email = json.get("email").toString()
        val phone = json.get("phone").toString()
        val cell = json.get("cell").toString()
        val nat = json.get("nat").toString()

        val idJson = json.get("id")
        val id = if (idJson.isJsonObject) idJson.asJsonObject.toString() else idJson.toString()
        val nameJson = json.get("name")
        val name = if (nameJson.isJsonObject) nameJson.asJsonObject.toString() else nameJson.toString()
        val locationJson = json.get("location")
        val location = if (locationJson.isJsonObject) locationJson.asJsonObject.toString() else locationJson.toString()
        val loginJson = json.get("login")
        val login = if (loginJson.isJsonObject) loginJson.asJsonObject.toString() else loginJson.toString()
        val dobJson = json.get("dob")
        val dob = if (dobJson.isJsonObject) dobJson.asJsonObject.toString() else dobJson.toString()
        val registeredJson = json.get("registered")
        val registered = if (registeredJson.isJsonObject) registeredJson.asJsonObject.toString() else registeredJson.toString()
        val pictureJson = json.get("picture")
        val picture = if (pictureJson.isJsonObject) pictureJson.asJsonObject.toString() else pictureJson.toString()

        return User(id,name,location,login,dob,registered,picture,gender,email,phone,cell,nat)
    }
}

class ResultsSerializer : JsonSerializer<User>{
    override fun serialize(
        src: User?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        val jsonObj = JsonObject()
        try {
            jsonObj.add("id", context?.serialize(src?.id))
            jsonObj.add("name", context?.serialize(src?.name))
            jsonObj.add("location", context?.serialize(src?.location))
            jsonObj.add("login", context?.serialize(src?.login))
            jsonObj.add("dob", context?.serialize(src?.dob))
            jsonObj.add("registered", context?.serialize(src?.registered))
            jsonObj.add("picture", context?.serialize(src?.picture))

            jsonObj.add("gender", context?.serialize(src?.gender))
            jsonObj.add("email", context?.serialize(src?.email))
            jsonObj.add("phone", context?.serialize(src?.phone))
            jsonObj.add("cell", context?.serialize(src?.cell))
            jsonObj.add("nat", context?.serialize(src?.nat))
        }catch (e: Exception){
            Log.w("JSON", "${e.message}")
        }
        return jsonObj
    }

}

data class User(
    var id: String? = null,
    var name: String? = null,
    var location: String? = null,
    var login: String? = null,
    var dob: String? = null,
    var registered: String? = null,
    var picture: String? = null,

    var gender: String? = null,
    var email: String? = null,
    var phone: String? = null,
    var cell: String? = null,
    var nat: String? = null,
)