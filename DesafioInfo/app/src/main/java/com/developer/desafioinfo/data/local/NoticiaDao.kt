package com.developer.desafioinfo.data.local
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.developer.desafioinfo.data.entities.Noticia
import kotlinx.coroutines.flow.Flow

@Dao
interface NoticiaDao {

    @Query("SELECT * FROM noticias")
    fun getAllNoticias() : LiveData<List<Noticia>>

    @Query("SELECT * FROM noticias WHERE id = :id")
    fun getNoticia(id: Int): LiveData<Noticia>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAl( noticia: List<Noticia>)




}