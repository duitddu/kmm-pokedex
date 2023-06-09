package com.duitddu.kmm.pokedex.android.util

import androidx.compose.ui.graphics.Color
import com.duitddu.kmm.pokedex.data.model.PokemonType

fun PokemonType.getColor(): Color =
    when (this) {
        PokemonType.NORMAL -> Color(0xFFA8A77A)
        PokemonType.FIRE -> Color(0xFFEE8130)
        PokemonType.WATER -> Color(0xFF6390F0)
        PokemonType.ELECTRIC -> Color(0xFFF7D02C)
        PokemonType.GRASS -> Color(0xFF7AC74C)
        PokemonType.ICE -> Color(0xFF96D9D6)
        PokemonType.FIGHTING -> Color(0xFFC22E28)
        PokemonType.POISON -> Color(0xFFA33EA1)
        PokemonType.GROUND -> Color(0xFFE2BF65)
        PokemonType.FLYING -> Color(0xFFA98FF3)
        PokemonType.PSYCHIC -> Color(0xFFF95587)
        PokemonType.BUG -> Color(0xFFA6B91A)
        PokemonType.ROCK -> Color(0xFFB6A136)
        PokemonType.GHOST -> Color(0xFF735797)
        PokemonType.DRAGON -> Color(0xFF6F35FC)
        PokemonType.DARK -> Color(0xFF705746)
        PokemonType.FAIRY -> Color(0xFFD685AD)
        PokemonType.STEEL -> Color(0xFFB7B7CE)
    }