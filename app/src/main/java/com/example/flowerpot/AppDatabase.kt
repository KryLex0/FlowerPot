package com.example.flowerpot

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Donnee::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    companion object{
        fun get(application: Application) : AppDatabase {
            return Room.databaseBuilder(application, AppDatabase::class.java, "Donnee").allowMainThreadQueries().build()
        }
    }
    abstract fun donneeDao() : DonneeDao
}