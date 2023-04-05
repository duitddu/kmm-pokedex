package com.duitddu.kmm.pokedex.android.feature.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.duitddu.kmm.pokedex.android.util.capitalizeFirstLetter
import com.duitddu.kmm.pokedex.android.util.getColor
import com.duitddu.kmm.pokedex.data.model.PokemonDetail
import com.duitddu.kmm.pokedex.data.model.PokemonType

@Composable
fun PokemonDetailScreen(
    viewModel: PokemonDetailViewModel,
    onBackClick: () -> Unit
) {
    when (val state = viewModel.state.value) {
        is PokemonDetailState.Success -> {
            PokemonDetailContent(state.pokemon, onBackClick)
        }
        is PokemonDetailState.ArgumentError, is PokemonDetailState.Failure -> {
            PokemonDetailErrorView(onBackClick)
        }
        else -> return
    }

    if (viewModel.isLoading.value) {
        Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun PokemonDetailContent(
    pokemon: PokemonDetail,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        PokemonDetailHeader(
            pokemonImageUrl = pokemon.imageUrl,
            pokemonOrder = "#${pokemon.order}",
            pokemonName = pokemon.name,
            pokemonType = pokemon.types.firstOrNull() ?: PokemonType.NORMAL
        ) {
            onBackClick.invoke()
        }
        Spacer(modifier = Modifier.height(32.dp))
        PokemonInfoHeader("Types")
        PokemonTypeList(pokemon.types)

        Spacer(modifier = Modifier.height(32.dp))
        PokemonInfoHeader("Physics")
        PokemonPhysicsInfo(height = pokemon.height, weight = pokemon.weight)

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun PokemonDetailErrorView(
    onBackClick: () -> Unit
) {
    // TODO :: Implement error view. :)
}

@Composable
fun PokemonDetailHeader(
    pokemonImageUrl: String,
    pokemonName: String,
    pokemonOrder: String,
    pokemonType: PokemonType,
    onBackClick: () -> Unit
) {
    Surface(
        color = pokemonType.getColor(),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(onClick = onBackClick) {
                    Image(
                        Icons.Default.ArrowBack,
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = pokemonName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    color = Color.White,
                )
                Text(
                    text = pokemonOrder,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White,
                )
            }
            AsyncImage(
                pokemonImageUrl,
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
        }
    }
}

@Composable
fun PokemonInfoHeader(
    header: String
) {
    Text(
        text = header,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = Color(0xFF222222),
        modifier = Modifier.padding(bottom = 16.dp, start = 20.dp, end = 20.dp)
    )
}

@Composable
fun PokemonTypeList(
    types: List<PokemonType>
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 20.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(types) {
            PokemonTypeListItem(it)
        }
    }
}

@Composable
fun PokemonTypeListItem(
    type: PokemonType
) {
    Card(
        shape = RoundedCornerShape(50),
        backgroundColor = type.getColor(),
        contentColor = Color.White,
    ) {
        Text(
            text = type.name.capitalizeFirstLetter(),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun PokemonPhysicsInfo(
    height: Int,
    weight: Int
) {
    Card(
        backgroundColor = Color.White,
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PokemonPhysicInfo(
                modifier = Modifier.weight(1f),
                header = "Height",
                value = height,
                unit = "dm"
            )
            PokemonPhysicInfo(
                modifier = Modifier.weight(1f),
                header = "Weight",
                value = weight,
                unit = "hg"
            )
        }
    }
}

@Composable
fun PokemonPhysicInfo(
    header: String,
    value: Int,
    unit: String,
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = header,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color(0xFF222222),
        )
        Text(
            text = "$value$unit",
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = Color(0xFF222222),
        )
    }
}