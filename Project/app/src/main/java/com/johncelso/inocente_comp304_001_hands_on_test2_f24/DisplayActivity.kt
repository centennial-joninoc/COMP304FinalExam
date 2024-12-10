package com.johncelso.inocente_comp304_001_hands_on_test2_f24

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.johncelso.inocente_comp304_001_hands_on_test2_f24.Model.StockInfo
import com.johncelso.inocente_comp304_001_hands_on_test2_f24.ui.theme.JohnCelsoInocente_COMP304_001_HandsOn_Test2_F24Theme


class DisplayActivity : ComponentActivity() {

    private var stockSymbol: String = ""
    private var companyName: String = ""
    private var stockQuote: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            JohnCelsoInocente_COMP304_001_HandsOn_Test2_F24Theme {
                Scaffold (

                ) { innerPadding ->
                    extractDataFromIntent()
                    ContentUI(innerPadding = innerPadding)
                }
            }
        }
    }

    @Composable
    private fun ContentUI(innerPadding: PaddingValues) {

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp)
        )
        {
            Text(stockSymbol, fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp))
            Text(companyName, fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp))
            Text(stockQuote.toString(), fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp))


            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                onClick = {
                    goBackToMain(this@DisplayActivity)
                }
            ) {
                Text("Back")
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun extractDataFromIntent() {
        stockSymbol = intent.getStringExtra("Stock_Symbol").toString()
        companyName = intent.getStringExtra("Company_Name").toString()
        stockQuote = intent.getDoubleExtra("Stock_Quote", 0.0)
    }

    // Go to Main activity
    private fun goBackToMain(context: Context) {
        val intent = Intent(this, MainActivity::class.java)
        context.startActivity(intent)
    }
}