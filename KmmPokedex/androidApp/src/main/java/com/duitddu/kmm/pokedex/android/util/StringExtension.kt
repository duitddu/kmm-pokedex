package com.duitddu.kmm.pokedex.android.util

import java.util.*

fun String.capitalizeFirstLetter(): String =
    lowercase().replaceFirstChar { it.titlecase(Locale.ROOT) }