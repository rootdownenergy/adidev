package com.rootdown.dev.adidevibm.model.feature_random_user.repo.util

import android.util.Log
import com.google.gson.*
import java.lang.Exception
import java.lang.reflect.Type

data class Name(
    var title: String? = null,
    var first: String? = null,
    var last: String? = null,
)

data class Picture(
    var large: String? = null,
    var medium: String? = null,
    var thumbnail: String? = null,
)

class NameSerializer : JsonSerializer<Name>{
    override fun serialize(
        src: Name?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        val jsonObj = JsonObject()
        try {
            jsonObj.add("title", context?.serialize(src?.title))
            jsonObj.add("first", context?.serialize(src?.first))
            jsonObj.add("last", context?.serialize(src?.last))
        }catch (e: Exception){
            Log.w("ERROR", "${e.message}")
        }
        return jsonObj
    }
}
class NameDeserializer : JsonDeserializer<Name>{
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Name {
        json as JsonObject
        val titleJson = json.get("title")
        val title = if (titleJson.isJsonObject) titleJson.asJsonObject.toString() else titleJson.toString()
        val firstJson = json.get("first")
        val first = if (firstJson.isJsonObject) firstJson.asJsonObject.toString() else firstJson.toString()
        val lastJson = json.get("last")
        val last = if (lastJson.isJsonObject) lastJson.asJsonObject.toString() else lastJson.toString()
        return Name(title,first,last)
    }
}

class PictureSerializer : JsonSerializer<Picture> {
    override fun serialize(
        src: Picture?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        val jsonObj = JsonObject()
        try {
            jsonObj.add("large", context?.serialize(src?.large))
            jsonObj.add("medium", context?.serialize(src?.medium))
            jsonObj.add("thumbnail", context?.serialize(src?.thumbnail))
        }catch (e: Exception){
            Log.w("ERROR", "${e.message}")
        }
        return jsonObj
    }

}
class PictureDeserializer : JsonDeserializer<Picture> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Picture {
        json as JsonObject
        val largeJson = json.get("large")
        val large = if (largeJson.isJsonObject) largeJson.asJsonObject.toString() else largeJson.toString()
        val mediumJson = json.get("medium")
        val medium = if (mediumJson.isJsonObject) mediumJson.asJsonObject.toString() else mediumJson.toString()
        val thumbnailJson = json.get("thumbnail")
        val thumbnail = if (thumbnailJson.isJsonObject) thumbnailJson.asJsonObject.toString() else thumbnailJson.toString()
        return Picture(large,medium,thumbnail)
    }

}