package com.kirilpetriv.showcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kirilpetriv.showcase.presentation.ArtworksScreen
import com.kirilpetriv.showcase.presentation.artworksScreenGraph
import com.kirilpetriv.showcase.ui.theme.ShowcaseTheme
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShowcaseTheme {
                KoinContext {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = ArtworksScreen) {
                        artworksScreenGraph(graphController = navController)
                    }
                }
            }
        }
    }
}