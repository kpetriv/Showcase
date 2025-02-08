package com.kirilpetriv.showcase.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kirilpetriv.showcase.R
import com.kirilpetriv.showcase.models.Artwork
import com.kirilpetriv.showcase.presentation.basecomposables.LoadingIndicatorScreen
import com.kirilpetriv.showcase.presentation.basecomposables.ScreenErrorView
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ArtworkDetailScreen(
    artworkId: Long,
    viewModel: ArtworksDetailViewModel = koinViewModel(parameters = { parametersOf(artworkId) }),
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.testTag("back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        when (val state = viewModel.state.collectAsState().value) {
            is ArtworkDetailScreenState.Error -> ScreenErrorView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                actions = {
                    IconButton(onClick = { viewModel.loadArtwork() }) {
                        Text(
                            text = stringResource(R.string.retry),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            )

            ArtworkDetailScreenState.Loading -> LoadingIndicatorScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )

            is ArtworkDetailScreenState.Success -> ArtworkDetailContent(
                artwork = state.artwork,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(innerPadding),
            )
        }
    }
}

@Composable
private fun ArtworkDetailContent(
    artwork: Artwork,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.verticalScroll(rememberScrollState()),
    ) {
        AsyncImage(
            model = artwork.getMainImageUrl(),
            contentDescription = "Artwork image",
            error = painterResource(R.drawable.img_no_image),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("artwork_image")
        )

        Text(
            text = getFormattedDescription(artwork.description),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("artwork_description")
        )
    }
}
