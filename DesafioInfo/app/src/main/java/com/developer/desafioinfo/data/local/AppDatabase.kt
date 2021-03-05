package com.developer.desafioinfo.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.developer.desafioinfo.data.entities.Noticia
import com.developer.desafioinfo.utils.converters.ConverterAny
import com.developer.desafioinfo.utils.converters.ConverterImage
import com.developer.desafioinfo.utils.converters.ConverterSecao
import com.developer.desafioinfo.utils.converters.ConverterString

@Database(entities = [Noticia::class], version = 1, exportSchema = false)
@TypeConverters(ConverterImage::class, ConverterString::class, ConverterAny::class, ConverterSecao::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun NoticiaDao(): NoticiaDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "noticias")
                .fallbackToDestructiveMigration()
                .build()
    }

}