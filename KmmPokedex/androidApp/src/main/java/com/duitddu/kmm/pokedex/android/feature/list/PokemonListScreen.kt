package com.duitddu.kmm.pokedex.android.feature.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.duitddu.kmm.pokedex.data.model.Pokemon

@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel,
    onPokemonClick: (Pokemon) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "KMM Pokedex",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            color = Color(0xFF222222),
            modifier = Modifier.padding(20.dp)
        )
        PokemonGrid(viewModel.pokemonList.value, onPokemonClick) {
            viewModel.loadPokemonList()
        }
    }
    if (viewModel.isLoading.value) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun PokemonGrid(
    pokemonList: List<Pokemon>,
    onPokemonClick: (Pokemon) -> Unit,
    onReachEnd: () -> Unit
) {
    val gridState = rememberLazyGridState()
    val reachedEnd = !gridState.canScrollForward

    LaunchedEffect(reachedEnd){
        if (reachedEnd) onReachEnd()
    }
    
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        state = gridState
    ) {
        items(pokemonList) {
            PokemonGridItem(it, onPokemonClick)
        }
    }
}

@Composable
fun PokemonGridItem(
    pokemon: Pokemon,
    onClick: (Pokemon) -> Unit
) {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick.invoke(pokemon) }
            .aspectRatio(1f)
            .fillMaxWidth(),
        backgroundColor = Color(0xFFF3F3F3),
        shape = RoundedCornerShape(12.dp),
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            AsyncImage(
                model = pokemon.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxSize()
                    .padding(24.dp)
            )
            Text(
                text = "#${pokemon.order}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.TopStart),
                color = Color(0xFF222222)
            )
            Text(
                text = pokemon.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.align(Alignment.BottomCenter),
                color = Color(0xFF222222)
            )
        }
    }
}