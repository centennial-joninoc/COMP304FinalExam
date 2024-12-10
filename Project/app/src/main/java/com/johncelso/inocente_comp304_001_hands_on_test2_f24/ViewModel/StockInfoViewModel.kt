package com.johncelso.inocente_comp304_001_hands_on_test2_f24.ViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johncelso.inocente_comp304_001_hands_on_test2_f24.Model.StockInfo
import kotlinx.coroutines.launch

class StockInfoViewModel (private val repository: AppRepository) : ViewModel() {

    var stockInfoVMList = mutableStateListOf<StockInfo>()
        private set

    var stock by mutableStateOf<StockInfo?>(null)
        private set

    var dbstocks by mutableStateOf<List<StockInfo>>(emptyList())
        private set

    var stockInfoNames by mutableStateOf<List<String>>(emptyList())
        //var cities by mutableListOf<String>()
        private set

    init {
        viewModelScope.launch {
            val stocksFromDB = repository.getAllStocks()
            dbstocks = stocksFromDB
        }
    }


    fun getDBStocks(): List<StockInfo> {
        viewModelScope.launch {
            val fetchCities = repository.getAllStocks()
            dbstocks = fetchCities
        }
        return dbstocks
    }

    fun insertStockToDB(c: StockInfo) {
        viewModelScope.launch {
            repository.addStockToDB(c)
            val dbfetch = repository.getAllStocks()
            dbstocks = dbfetch
        }
    }

    fun updateStock(c: StockInfo) {
        viewModelScope.launch {
            repository.updateStock(c)
            val dbfetchCities = repository.getAllStocks()
            dbstocks = dbfetchCities
        }
    }

    fun deleteOneStock(c: StockInfo) {
        viewModelScope.launch {
            repository.deleteStock(c)
            val dbfetch = repository.getAllStocks()
            dbstocks = dbfetch
        }
    }

    fun findStockInDB(c: String): StockInfo?
    {
        viewModelScope.launch {
            try {
                val stockInfo = repository.searchStockBy(c)
                stock = stockInfo
            } catch (e: Exception) {
                Log.d("error", e.toString())
            }
        }
        return stock
    }
}