package com.johncelso.inocente_comp304_001_hands_on_test2_f24.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stock_info")
data class StockInfo(
    @PrimaryKey
    val stockSymbol: String,

    val companyName: String,

    val stockQuote: Double
)
