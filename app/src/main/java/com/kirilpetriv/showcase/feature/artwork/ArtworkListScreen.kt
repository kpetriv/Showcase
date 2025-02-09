package com.kirilpetriv.showcase.feature.artwork

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.kirilpetriv.showcase.R
import com.kirilpetriv.model.Artwork
import com.kirilpetriv.showcase.commonUi.LoadingIndicatorScreen
import com.kirilpetriv.showcase.commonUi.ScreenErrorView
import org.koin.androidx.compose.koinViewModel


@Composable
internal fun ArtworkListScreen(
    onArtworkItem: (Long) -> Unit,
    viewModel: ArtworksListViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
) {
    val items = viewModel.artworks.collectAsLazyPagingItems()

    Scaffold(modifier = modifier) { innerPadding ->

        when {
            items.loadState.refresh is LoadState.Loading -> LoadingIndicatorScreen(
                modifier = Modifier.fillMaxSize()
            )

            items.loadState.refresh is LoadState.Error -> ScreenErrorView(
                modifier = Modifier.fillMaxSize(),
                actions = {
                    IconButton(onClick = { items.retry() }) {
                        Text(text = stringResource(R.string.retry), fontWeight = FontWeight.Bold)
                    }
                }
            )

            items.itemCount == 0 -> ScreenErrorView(
                modifier = Modifier.fillMaxSize(),
                title = stringResource(R.string.artworks_no_results_title),
                subtitle = stringResource(R.string.artwork_no_results_subtitle)
            )

            else -> ArtworkResults(
                innerPadding = innerPadding,
                artworks = items,
                onArtwork = onArtworkItem
            )
        }
    }
}

@Composable
private fun ArtworkResults(
    innerPadding: PaddingValues,
    artworks: LazyPagingItems<Artwork>,
    onArtwork: (Long) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .padding(innerPadding)
            .testTag("artwork_list"),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(count = artworks.itemCount, key = artworks.itemKey { it.id }) { index ->
            artworks[index]?.also {
                Card(
                    onClick = { onArtwork(it.id) },
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
                            model = it.getThumbnailImageUrl(),
                            contentDescription = "Artwork thumbnail",
                            error = painterResource(R.drawable.img_no_image),
                            modifier = Modifier
                                .size(75.dp)
                                .testTag("artwork_thumbnail")
                        )
                        Column {
                            Text(
                                text = it.title,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.testTag("artwork_title")
                            )
                            Text(text = it.artist, modifier = Modifier.testTag("artwork_artist"))
                        }
                    }
                }
            }
        }

        // Last item in the list is showing state when the list is being appended
        item {
            when (artworks.loadState.append) {
                is LoadState.Loading -> CircularProgressIndicator()
                is LoadState.Error -> ScreenErrorView(
                    actions = {
                        IconButton(onClick = { artworks.retry() }) {
                            Text(
                                text = stringResource(R.string.retry),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                )

                else -> {}
            }
        }
    }
}