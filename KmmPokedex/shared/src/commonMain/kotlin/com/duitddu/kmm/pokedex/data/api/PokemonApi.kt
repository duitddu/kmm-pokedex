package com.duitddu.kmm.pokedex.data.api

import com.duitddu.kmm.pokedex.data.api.response.PokemonDetailResponse
import com.duitddu.kmm.pokedex.data.api.response.PokemonResultsResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class PokemonApi {
    private val httpClient = HttpClient(CIO) {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
            filter { request ->
                request.url.host.contains("ktor.io")
            }
        }
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = "pokeapi.co"
                path("api/v2/")
            }
            accept(ContentType.Application.Json)
            contentType(ContentType.Application.Json)
        }
    }

    suspend fun getPokemonResults(
        limit: Int,
        offset: Int
    ): PokemonResultsResponse =
        httpClient.get("pokemon") {
            parameter("limit", limit)
            parameter("offset", offset)
        }.body()

    suspend fun getPokemonDetail(
        name: String
    ): PokemonDetailResponse =
        httpClient.get("pokemon/$name").body()
}