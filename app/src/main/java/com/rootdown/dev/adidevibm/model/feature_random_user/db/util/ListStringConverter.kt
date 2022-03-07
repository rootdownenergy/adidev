package com.rootdown.dev.adidevibm.model.feature_random_user.db.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import java.lang.reflect.Type

private const val SEPARATOR = ","
class ListStringConverter {
    @TypeConverter
    fun fromString(value: String?): MutableList<String> {
        val listType = object :
            TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }
    @TypeConverter
    fun fromList(list: MutableList<String?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}