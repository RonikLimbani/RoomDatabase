package com.ronik.roomdatabasewithmvvm.di

import android.content.Context
import androidx.room.Room
import com.ronik.roomdatabasewithmvvm.UserRepository
import com.ronik.roomdatabasewithmvvm.db.AppDatabase
import com.ronik.roomdatabasewithmvvm.db.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun providesUserDao(userDatabase: AppDatabase):UserDao = userDatabase.userDao()
    @Provides
    @Singleton
    fun providesUserDatabase(@ApplicationContext context: Context):AppDatabase
    = Room.databaseBuilder(context,AppDatabase::class.java,"UserDatabase").build()

    @Provides
    fun providesUserRepository(userDao: UserDao) : UserRepository = UserRepository(userDao)

}

