package com.developer.desafioinfo.utils.converters

import androidx.room.TypeConverter
import com.developer.desafioinfo.data.entities.Secao
import com.google.gson.Gson

class ConverterSecao {

        @TypeConverter
        fun appToString(secao: Secao): String? = Gson().toJson(secao)

        @TypeConverter
        fun stringToApp(string: String?): Secao = Gson().fromJson(string, Secao::class.java)


}