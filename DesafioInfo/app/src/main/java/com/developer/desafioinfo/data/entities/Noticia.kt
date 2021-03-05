package com.developer.desafioinfo.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.developer.desafioinfo.utils.converters.ConverterAny
import com.developer.desafioinfo.utils.converters.ConverterImage
import com.developer.desafioinfo.utils.converters.ConverterSecao
import com.developer.desafioinfo.utils.converters.ConverterString


@Entity(tableName = "noticias")
data class Noticia (
    @TypeConverters(ConverterString::class)
     val autores: List<String>?,
     val informePublicitario: Boolean?,
     val subTitulo: String?,
     val texto: String?,
    @TypeConverters(ConverterAny::class)
    val videos: List<Any>?,
    val atualizadoEm: String?,
    @PrimaryKey
    val id: Int,
    val publicadoEm: String?,
    @TypeConverters(ConverterSecao::class)
    val secao: Secao?,
    val tipo: String?,
    val titulo: String?,
    val url: String?,
    val urlOriginal: String?,
    @TypeConverters(ConverterImage::class)
    val imagens: List<Imagen>?
)