package com.sample.app.here

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sample.app.here.model.People
import com.sample.app.here.ui.theme.AppDemoTheme

class MainActivity : ComponentActivity() {
    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityViewModel.getPeople()

        setContent {
            AppDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewState by mainActivityViewModel.viewState.collectAsState()
                    MainScreen(viewState.people)
                }
            }
        }
    }
}


@Composable
fun MainScreen(people: List<People>) {
    LazyColumn(
        modifier = Modifier,
    ) {
        items(
            items = people,
            itemContent = { item ->
                Column {
                    Text(
                        modifier = Modifier,
                        fontSize = 20.sp,
                        text = item.name ?: "",
                    )

                    Text(
                        modifier = Modifier,
                        fontSize = 20.sp,
                        text = item.language ?: "",
                    )
                }

            }
        )
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppDemoTheme {
        Greeting("Android")
    }
}