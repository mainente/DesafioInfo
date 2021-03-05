package com.developer.desafioinfo.utils.converters

import androidx.room.TypeConverter
import com.developer.desafioinfo.data.entities.Imagen
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class ConverterString {

        @TypeConverter
        fun fromString(value: String?): List<String>? {
            val listType: Type =
                object : TypeToken<List<String?>?>() {}.type
            return Gson().fromJson<List<String>>(value, listType)
        }

        @TypeConverter
        fun listToString(list: List<String?>?): String? {
            val gson = Gson()
            return gson.toJson(list)
        }

}