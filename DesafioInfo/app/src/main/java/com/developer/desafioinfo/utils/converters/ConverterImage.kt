package com.developer.desafioinfo.utils.converters

import androidx.room.TypeConverter
import com.developer.desafioinfo.data.entities.Imagen
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class ConverterImage {

        @TypeConverter
        fun fromString(value: String?): List<Imagen>? {
            val listType: Type =
                object : TypeToken<List<Imagen?>?>() {}.type
            return Gson().fromJson<List<Imagen>>(value, listType)
        }

        @TypeConverter
        fun listToString(list: List<Imagen?>?): String? {
            val gson = Gson()
            return gson.toJson(list)
        }

}