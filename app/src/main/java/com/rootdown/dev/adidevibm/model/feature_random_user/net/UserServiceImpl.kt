package com.rootdown.dev.adidevibm.model.feature_random_user.net

import com.rootdown.dev.adidevibm.model.feature_random_user.db.User
import com.rootdown.dev.adidevibm.model.feature_random_user.net.util.HttpRoutes
import io.ktor.client.HttpClient
import io.ktor.client.features.*
import io.ktor.client.request.*
import java.lang.Exception

class UserServiceImpl(
    private val client: HttpClient
    ) : UserService {

    override suspend fun getuser(): List<UserResponse> {
        return try {
            client.get {
                url(HttpRoutes.BASE_URL)
            }
        } catch (e: RedirectResponseException){
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException){
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException){
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch(e: Exception){
            // general
            println("Error: ${e.message}")
            emptyList()
        }

    }
}