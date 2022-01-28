package com.ojanbelajar.aronta.data.source.local.room

import android.content.Context
import androidx.room.*
import com.ojanbelajar.aronta.data.source.local.entity.BuruhEntity
import com.ojanbelajar.aronta.data.source.local.entity.FarmerEntity
import com.ojanbelajar.aronta.utils.Converters

@Database(entities = [FarmerEntity::class,BuruhEntity::class],version = 1,exportSchema = false)
@TypeConverters(Converters::class)
abstract class ArontaDatabase: RoomDatabase() {

    abstract fun arontaDao(): ArontaDao

    companion object {

        @Volatile
        private var INSTANCE: ArontaDatabase? = null

        fun getInstance(context: Context): ArontaDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    ArontaDatabase::class.java,
                    "aronta.db"
                ).fallbackToDestructiveMigration()
                    .build().apply {
                        INSTANCE = this
                    }
            }
    }
}