package repository

import api.IdentityApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import model.request.*

class UserRepositoryImpl(private val identityApi: IdentityApi): UserRepository {
    //TODO api errors handling

    override suspend fun signIn(email: String, password: String) = withContext(Dispatchers.IO) {
        val result = identityApi.signIn(
            SignInRequest(
                email = email,
                password = password
            )
        )
        result.status.value in 200..299
    }

    override suspend fun signUp(nickname: String, email: String, password: String) = withContext(Dispatchers.IO) {
        val result = identityApi.signUp(
            SignUpRequest(
                nickname = nickname,
                email = email,
                password = password
            )
        )
        result.status.value in 200..299
    }

    override suspend fun resetPassword(email: String) = withContext(Dispatchers.IO) {
        val result = identityApi.resetPassword(ResetPasswordRequest(email = email))
        result.status.value in 200..299
    }

    override suspend fun signOut() = withContext(Dispatchers.IO) {
        val result = identityApi.signOut()
        result.status.value in 200..299
    }

    override suspend fun checkNick(nickname: String) = withContext(Dispatchers.IO) {
        identityApi.checkNick(nickname).exists
    }

    override suspend fun getUser() = withContext(Dispatchers.IO) {
        identityApi.getUser()
    }
}