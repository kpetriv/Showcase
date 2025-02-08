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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kirilpetriv.showcase.R
import com.kirilpetriv.showcase.models.Artwork
import com.kirilpetriv.showcase.presentation.basecomposables.ErrorView
import com.kirilpetriv.showcase.presentation.basecomposables.LoadingIndicatorScreen
import org.koin.androidx.compose.koinViewModel


@Composable
internal fun ArtworkListScreen(
    // Description and imageUrl. In that order
    onArtworkItem: (String, String?) -> Unit,
    mainViewModel: ArtworksViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
) {
    val state = mainViewModel.state.collectAsState().value
    Scaffold(modifier = modifier) { innerPadding ->
        when (state) {
            is ArtworkScreenState.Error -> ArtworkListScreenErrorView(
                onRetry = mainViewModel::getArtworks
            )

            ArtworkScreenState.Loading -> LoadingIndicatorScreen(
                modifier = Modifier.fillMaxSize()
            )

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
            .testTag("artwork_list"),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(artworks) { item ->
            Card(
                onClick = { onArtwork(item.description, item.getMainImageUrl()) },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("artwork_card")
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp)
                ) {
                    AsyncImage(
                        model = item.getThumbnailImageUrl(),
                        contentDescription = "Artwork thumbnail",
                        error = painterResource(R.drawable.img_no_image),
                        modifier = Modifier
                            .size(75.dp)
                            .testTag("artwork_thumbnail")
                    )
                    Column {
                        Text(
                            text = item.title,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.testTag("artwork_title")
                        )
                        Text(text = item.artist, modifier = Modifier.testTag("artwork_artist"))
                    }
                }
            }
        }
    }
}

@Composable
private fun ArtworkListScreenErrorView(
    onRetry: () -> Unit,
) {
    ErrorView(
        icon = {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = "Back",
                tint = Color.Red,
                modifier = Modifier.size(32.dp)
            )
        },
        title = "An error has occurred",
        subtitle = "Please retry again",
        color = Color.Red,
        modifier = Modifier.fillMaxWidth(),
        actions = {
            IconButton(onClick = onRetry) {
                Text("Retry", fontWeight = FontWeight.Bold)
            }
        }
    )
}