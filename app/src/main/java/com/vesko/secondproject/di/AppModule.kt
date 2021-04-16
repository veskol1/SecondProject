package com.vesko.secondproject.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vesko.secondproject.model.JobDao
import com.vesko.secondproject.model.JobDatabase
import com.vesko.secondproject.network.WebService
import com.vesko.secondproject.repository.JobsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://jobs.github.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson() : Gson = GsonBuilder().create()

    @Provides
    fun provideWebservice(retrofit: Retrofit): WebService = retrofit.create(WebService::class.java)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = JobDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideJobDao(db: JobDatabase) = db.jobDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: WebService, localDataSource: JobDao) =
        JobsRepository(remoteDataSource, localDataSource)
}