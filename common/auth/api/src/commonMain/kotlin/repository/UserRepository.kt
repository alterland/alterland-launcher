package repository

import model.response.GetUserResponse

interface UserRepository {
    suspend fun signIn(email: String, password: String): Boolean
    suspend fun signUp(nickname: String, email: String, password: String): Boolean
    suspend fun resetPassword(email: String): Boolean
    suspend fun signOut(): Boolean
    suspend fun checkNick(nickname: String): Boolean
    suspend fun getUser(): GetUserResponse
}