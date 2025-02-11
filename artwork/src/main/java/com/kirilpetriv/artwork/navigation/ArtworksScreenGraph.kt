package com.kirilpetriv.artwork.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.kirilpetriv.artwork.composable.ArtworkDetailScreen
import com.kirilpetriv.artwork.composable.ArtworkListScreen
import kotlinx.serialization.Serializable

@Serializable
object ArtworksScreen

@Serializable
private object ArtworkList

@Serializable
private data class ArtworksDetail(val artworkId: Long)

fun NavGraphBuilder.artworksScreenGraph(
    graphController: NavController,
) {
    navigation<ArtworksScreen>(startDestination = ArtworkList) {
        composable<ArtworkList> { _ ->
            ArtworkListScreen(
                onArtworkItem = { id -> graphController.navigateToArtworkDetail(id) },
                modifier = Modifier.fillMaxSize()
            )
        }
        composable<ArtworksDetail> { backStack ->
            val detail = backStack.toRoute<ArtworksDetail>()
            val onBack: () -> Unit = { graphController.navigateUp() }
            ArtworkDetailScreen(
                artworkId = detail.artworkId,
                onBack = onBack,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

fun NavController.navigateToArtworkDetail(
    artworkId: Long,
    navOptions: NavOptions? = null
) = navigate(
    route = ArtworksDetail(artworkId = artworkId),
    navOptions = navOptions
)