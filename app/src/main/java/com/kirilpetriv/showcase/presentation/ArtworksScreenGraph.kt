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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import coil.compose.AsyncImage
import com.kirilpetriv.showcase.core.Artwork
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

// Assume it's fine to use ExperimentalMaterial3Api in this case for the TopAppBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ArtworkDetailScreen(
    description: String,
    imageUrl: String?,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        modifier = modifier.statusBarsPadding(),
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "Artwork image",
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = getFormattedDescription(description),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun ArtworkListScreen(
    // Description and imageUrl. In that order
    onArtworkItem: (String, String?) -> Unit,
    mainViewModel: ArtworksViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
) {
    val state = mainViewModel.state.collectAsState().value
    Scaffold(modifier = modifier) { innerPadding ->
        when (state) {
            // Not handling error state here. With the errors caught in the VM, we can differentiate
            // errors and provide multiple variations of the error screen with retry buttons or
            // whatever configuration is needed
            is ArtworkScreenState.Error -> Unit
            // Loading spinner or shimmer here. Not adding in this case as I assume the load time
            // is non-existent and the user won't be able to see the loading state
            ArtworkScreenState.Loading -> Unit
            is ArtworkScreenState.Success -> ArtworkResults(
                innerPadding = innerPadding,
                artworks = state.artworks,
                onArtwork = onArtworkItem
            )
        }
    }
}

@Composable
private fun ArtworkResults(
    innerPadding: PaddingValues,
    artworks: List<Artwork>,
    // Description and imageUrl. In that order
    onArtwork: (String, String?) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .padding(innerPadding)
            .statusBarsPadding()
            .padding(top = 16.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(artworks) { item ->
            Card(
                onClick = { onArtwork(item.description, item.getMainImageUrl()) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp)
                ) {
                    item.imageUuid?.let {
                        AsyncImage(
                            model = item.getThumbnailImageUrl(),
                            contentDescription = "Artwork thumbnail",
                            modifier = Modifier.size(75.dp)
                        )
                    }
                    Column {
                        Text(text = item.title, fontWeight = FontWeight.Bold)
                        Text(text = item.artist)
                    }
                }
            }
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