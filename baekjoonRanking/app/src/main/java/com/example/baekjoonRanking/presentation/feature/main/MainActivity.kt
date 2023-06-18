package com.example.baekjoonRanking.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.baekjoonRanking.domain.model.Rank
import com.example.baekjoonRanking.domain.model.ranks
import com.example.baekjoonRanking.data.repository.UserRepositoryImpl
import com.example.baekjoonRanking.domain.repository.UserRepository
import com.example.baekjoonRanking.presentation.ui.theme.BaekjoonRankingTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor(
    private val userRepository: UserRepository
) : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        CoroutineScope(Dispatchers.IO).launch {
            val user = userRepository.getUser("hhhello0507")
            val solved = user.solvedCount
            Log.d("로그", "solved - $solved")
        }
        setContent {
            BaekjoonRankingTheme(
                darkTheme = true,
            ) {
                ContentView()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentView() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(topBar = { MyAppBar() }) { it -> it.toString()
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TitleText(title = "Baekjoon Ranking")
                Spacer(modifier = Modifier.height(10.dp))
                LazyColumn {
                    items(ranks) { rank ->
                        RankingText(rank)
                    }
                }
            }
        }
    }
}

@Composable
fun MyAppBar() {
}

@Composable
fun TitleText(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
fun RankingText(rank: Rank) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(modifier = Modifier
        .width(280.dp)
        .height(40.dp)
        .clickable { isExpanded = !isExpanded },
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = if (isExpanded) Color.Black else Color.White,
        ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = rank.name,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(end = 10.dp),
                color = if (isExpanded) Color.White else Color.Black
            )
            Text(
                text = rank.solved.toString() + " solved",
                style = MaterialTheme.typography.labelLarge,
                color = if (isExpanded) Color.White else Color.Black
            )
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BaekjoonRankingTheme(
        darkTheme = true,
    ) {
        ContentView()
    }
}