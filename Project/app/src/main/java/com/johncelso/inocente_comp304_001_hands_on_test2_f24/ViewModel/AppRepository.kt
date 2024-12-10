package com.johncelso.inocente_comp304_001_hands_on_test2_f24.ViewModel

import com.johncelso.inocente_comp304_001_hands_on_test2_f24.Model.StockInfo
import com.johncelso.inocente_comp304_001_hands_on_test2_f24.Networking.RetrofitClass
import com.johncelso.inocente_comp304_001_hands_on_test2_f24.RoomDB.StockInfoDAO

//import com.example.johninocente_comp304lab3_ex1.MainActivity.Companion.weatherDataList


private const val API_KEY = "26660e167276b6950d94f580ab39e6c4"
private const val API_METRIC = "metric"

class AppRepository(private val stockInfoDAO: StockInfoDAO) {

    private val apiService = RetrofitClass.api
    private val weatherApiService = RetrofitClass.weatherApi

    suspend fun getAllStocks(): List<StockInfo>{
        return stockInfoDAO.getAll()
    }

    suspend fun addStockToDB(c: StockInfo){
        stockInfoDAO.insertStock(c)
    }

    suspend fun deleteStock(c: StockInfo){
        stockInfoDAO.deleteStock(c)
    }

    suspend fun searchStockBy(companyName: String) : StockInfo
    {
        return stockInfoDAO.findStockByName(companyName)
    }

    suspend fun updateStock(c: StockInfo){
        return stockInfoDAO.updateStock(c)
    }

}