package com.hs.dgsw.android.check.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TokenEntity::class, FcmTokenEntity::class], version = 3)
abstract class CheckInDataBase: RoomDatabase() {
    abstract fun tokenDao(): TokenDao
    abstract fun fcmTokenDao(): FcmTokenDao

    companion object {
        private var instance: CheckInDataBase? = null

        @Synchronized
        fun getInstance(context: Context): CheckInDataBase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    CheckInDataBase::class.java, "database"
                ).build()
            }
            return instance
        }

        fun getInstanceOrNull(): CheckInDataBase? {
            return instance
        }
    }
}