package com.johncelso.inocente_comp304_001_hands_on_test2_f24

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.johncelso.inocente_comp304_001_hands_on_test2_f24.Model.StockInfo
import com.johncelso.inocente_comp304_001_hands_on_test2_f24.RoomDB.StockInfoDatabase
import com.johncelso.inocente_comp304_001_hands_on_test2_f24.ViewModel.AppRepository
import com.johncelso.inocente_comp304_001_hands_on_test2_f24.ViewModel.StockInfoViewModel
import com.johncelso.inocente_comp304_001_hands_on_test2_f24.ViewModel.ViewModelFactory
import com.johncelso.inocente_comp304_001_hands_on_test2_f24.ui.theme.JohnCelsoInocente_COMP304_001_HandsOn_Test2_F24Theme

class MainActivity : ComponentActivity() {

    var stockDataList by mutableStateOf<List<StockInfo>>(emptyList())
        private set

    var selectedStock by mutableStateOf<StockInfo?>(null)
        private set

    private var selectedStockSymbol: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = StockInfoDatabase.getInstance(applicationContext)
        val repository = AppRepository(database.getStockInfoDAO())
        val viewModelFactory = ViewModelFactory(repository)
        val stockInfoViewModel = ViewModelProvider(this, viewModelFactory!!)[StockInfoViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            JohnCelsoInocente_COMP304_001_HandsOn_Test2_F24Theme {
                MainUI(stockInfoViewModel)
            }
        }
    }

    @Composable
    private fun MainUI(
        stockViewModel: StockInfoViewModel,
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
        ) { innerPadding ->

            stockDataList = stockViewModel.getDBStocks()
            ContentUI(innerPadding, stockViewModel)
        }
    }


    //TODO: Custom City
    @Composable
    private fun ContentUI(
        innerPaddingValues: PaddingValues,
        stockViewModel: StockInfoViewModel
    ) {

        var stockSymbol by remember { mutableStateOf(TextFieldValue(""))}
        var companyName by remember { mutableStateOf(TextFieldValue(""))}
        var stockQuote by remember { mutableStateOf(TextFieldValue(""))}

        var selectedIndex by remember {
            mutableStateOf(-1)
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp))
        {
            Text("Insert Stocks", fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp))

            BasicTextField(
                value = stockSymbol,
                onValueChange = { stockSymbol = it },
                modifier = Modifier.fillMaxWidth() .padding(bottom = 8.dp),
                decorationBox = { innerTextField ->
                    Box(
                        Modifier
                            .padding(8.dp)
                            .background(Color.LightGray)
                    ) {
                        if (stockSymbol.text.isEmpty())
                        { Text("Stock Symbol") }
                        innerTextField()
                    }
                })

            BasicTextField(
                value = companyName,
                onValueChange = { companyName = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                decorationBox = { innerTextField ->
                    Box( Modifier
                        .padding(8.dp)
                        .background(Color.LightGray)
                    ) {
                        if (companyName.text.isEmpty()) {
                            Text("Company Name")
                        }
                        innerTextField()
                    }
                })

            BasicTextField(
                value = stockQuote,
                onValueChange = { stockQuote = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                decorationBox = { innerTextField ->
                    Box( Modifier .padding(8.dp)
                        .background(Color.LightGray)
                    ) {
                        if (stockQuote.text.isEmpty())
                        { Text("Stock Quote") }
                        innerTextField()
                    }
                })

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                onClick = {
                    val newStock = StockInfo(stockSymbol.text, companyName.text, stockQuote.text.toDouble())
                    stockViewModel.insertStockToDB(newStock)
                    stockDataList = stockViewModel.getDBStocks()
                }
            ) {
                Text("Insert Stocks")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Display Stock Info",
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 8.dp))
            val stockList = stockViewModel.getDBStocks()

            LazyColumn(
                Modifier.selectableGroup()) {
                items(stockList.size) { id ->
                    Text(stockDataList[id].stockSymbol,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                            .selectable(
                                selected = id == selectedIndex,
                                onClick = {
                                    if (selectedIndex != id) {
                                        selectedIndex = id
                                    }
                                    else selectedIndex = -1
                                }
                            )
                    )
                }
            }
            Button( onClick = {
                gotoDisplayActivity(this@MainActivity, stockDataList[selectedIndex])

            },
                modifier = Modifier .fillMaxWidth() .padding(vertical = 8.dp) )
            { Text("Display Stock Info") }
        }
    }
}

private fun gotoDisplayActivity(context: Context, stock: StockInfo?) {
    val intent = Intent(context, DisplayActivity::class.java)

    intent.putExtra("Stock_Symbol", stock?.stockSymbol)
    intent.putExtra("Company_Name", stock?.companyName)
    intent.putExtra("Stock_Quote", stock?.stockQuote)
    context.startActivity(intent)

}