package com.developer.desafioinfo.di

import android.content.Context
import com.developer.desafioinfo.data.api.NoticiasApi
import com.developer.desafioinfo.data.api.NoticiasRemoteDataSource
import com.developer.desafioinfo.data.entities.Noticia
import com.developer.desafioinfo.data.local.AppDatabase
import com.developer.desafioinfo.data.local.NoticiaDao
import com.developer.desafioinfo.data.repository.NoticiaRepository

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    const val CONNECTION_TIMEOUT = 50000L

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient) : Retrofit = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/Infoglobo/desafio-apps/master/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
     fun provideOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)


        return client.build()
    }

    @Provides
    fun provideNoticiaService(retrofit: Retrofit):  NoticiasApi = retrofit.create(NoticiasApi::class.java)

    @Singleton
    @Provides
    fun provideNoticiaRemoteDataSource(NoticiaService: NoticiasApi) = NoticiasRemoteDataSource(NoticiaService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.NoticiaDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: NoticiasRemoteDataSource,
                          localDataSource: NoticiaDao
    ) = NoticiaRepository(remoteDataSource, localDataSource)
}