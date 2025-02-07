package com.kirilpetriv.showcase.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import coil.compose.AsyncImage
import com.kirilpetriv.showcase.models.Artwork
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
internal object ArtworksScreen

@Serializable
private object ArtworkList

@Serializable
private data class ArtworksDetail(val description: String, val imageUrl: String?)

internal fun NavGraphBuilder.artworksScreenGraph(
    graphController: NavController,
) {
    navigation<ArtworksScreen>(startDestination = ArtworkList) {
        composable<ArtworkList> { _ ->
            ArtworkListScreen(
                onArtworkItem = { description, imageUrl ->
                    graphController.navigateToArtworkDetail(
                        description = description,
                        imageUrl = imageUrl
                    )
                },
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(horizontal = 16.dp)
                    .fillMaxSize()
            )
        }
        composable<ArtworksDetail> { backStack ->
            val detail = backStack.toRoute<ArtworksDetail>()
            val onBack: () -> Unit = { graphController.navigateUp() }
            ArtworkDetailScreen(
                description = detail.description,
                imageUrl = detail.imageUrl,
                onBack = onBack,
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(horizontal = 16.dp)
                    .fillMaxSize()
            )
        }
    }
}

fun NavController.navigateToArtworkDetail(
    description: String,
    imageUrl: String?,
    navOptions: NavOptions? = null
) = navigate(
    route = ArtworksDetail(description = description, imageUrl = imageUrl),
    navOptions = navOptions
)