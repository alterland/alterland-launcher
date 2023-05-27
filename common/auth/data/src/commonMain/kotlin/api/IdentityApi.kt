package api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import model.request.*
import model.response.CheckNicknameResponse
import model.response.GetUserResponse

class IdentityApi(private val httpClient: HttpClient) {
    companion object {
        private const val path: String = "/identity"
    }

    suspend fun signIn(req: SignInRequest) = httpClient.post("$path/signin/") {
        setBody(req)
    }

    suspend fun signUp(req: SignUpRequest): HttpResponse = httpClient.post("$path/signup/") {
        setBody(req)
    }

    suspend fun resetPassword(req: ResetPasswordRequest): HttpResponse = httpClient.post("$path/reset-password/") {
        setBody(req)
    }

    suspend fun checkNick(nickname: String): CheckNicknameResponse = httpClient.get("$path/check-nickname/") {
        url {
            parameters.append("nickname", nickname)
        }
    }.body()

    suspend fun getUser(): GetUserResponse = httpClient.get("$path/user/").body()

    suspend fun signOut() = httpClient.post("$path/signout/")
}