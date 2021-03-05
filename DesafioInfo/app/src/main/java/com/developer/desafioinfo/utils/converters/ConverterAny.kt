package com.developer.desafioinfo.utils.converters

import androidx.room.TypeConverter
import com.developer.desafioinfo.data.entities.Imagen
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class ConverterAny {

        @TypeConverter
        fun fromString(value: String?): List<Any>? {
            val listType: Type =
                object : TypeToken<List<Any?>?>() {}.type
            return Gson().fromJson<List<Any>>(value, listType)
        }

        @TypeConverter
        fun listToString(list: List<Any?>?): String? {
            val gson = Gson()
            return gson.toJson(list)
        }

}