package com.johncelso.inocente_comp304_001_hands_on_test2_f24.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.johncelso.inocente_comp304_001_hands_on_test2_f24.Model.StockInfo

@Database(entities = [StockInfo::class], version = 1)
abstract class StockInfoDatabase : RoomDatabase() {
    abstract fun getStockInfoDAO(): StockInfoDAO

    companion object {
        @Volatile
        private var INSTANCE: StockInfoDatabase? = null
        fun getInstance(context: Context): StockInfoDatabase {
            // ensuring that only one thread can execute the block
            // of code inside the synchronized block at any given time
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    // Creating the Database Object
                    instance = Room.databaseBuilder(
                        context = context,
                        StockInfoDatabase::class.java,
                        "stock_info"
                    ).build()
                }
                INSTANCE = instance
                return instance
            }
        }
    }
}