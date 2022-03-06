package com.rootdown.dev.adidevibm.model.feature_random_user.net

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
private const val TIME_OUT = 60_000
interface UserService {
    suspend fun getuser(): List<UserResponse>

    companion object {
        fun create(): UserService {
            return UserServiceImpl(
                client = HttpClient(Android) {
                    install(JsonFeature) {
                        serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                            prettyPrint = true
                            isLenient = true
                            ignoreUnknownKeys = true
                        })

                        engine {
                            connectTimeout = TIME_OUT
                            socketTimeout = TIME_OUT
                        }
                    }

                    install(Logging) {
                        logger = object : Logger {
                            override fun log(message: String) {
                                Log.v("Logger Ktor =>", message)
                            }

                        }
                        level = LogLevel.ALL
                    }

                    install(ResponseObserver) {
                        onResponse { response ->
                            Log.d("HTTP status:", "${response.status.value}")
                        }
                    }

                    install(DefaultRequest) {
                        header(HttpHeaders.ContentType, ContentType.Application.Json)
                    }
                }
            )
        }
    }
}