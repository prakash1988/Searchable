package com.example.searchable.di

import android.content.Context
import androidx.room.Room
import com.example.searchable.data.UserDAO
import com.example.searchable.data.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext appContext: Context): UserDatabase =
        Room.databaseBuilder(appContext, UserDatabase::class.java, "users").build()

    @Singleton
    @Provides
    fun providesUserDao(userDatabase: UserDatabase): UserDAO =
        userDatabase.getUserDao()

}