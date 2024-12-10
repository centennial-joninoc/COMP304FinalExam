package com.johncelso.inocente_comp304_001_hands_on_test2_f24.RoomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.johncelso.inocente_comp304_001_hands_on_test2_f24.Model.StockInfo

@Dao
interface StockInfoDAO {
    @Query("SELECT * FROM `stock_info`")
    suspend fun getAll(): List<StockInfo>

    @Query("SELECT * FROM `stock_info` WHERE stockSymbol LIKE :stockInfo")
    suspend fun findStockByName(stockInfo: String): StockInfo

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStock(stock: StockInfo)

    @Delete
    suspend fun deleteStock(stock: StockInfo)

    @Update
    suspend fun updateStock(stock: StockInfo)
}