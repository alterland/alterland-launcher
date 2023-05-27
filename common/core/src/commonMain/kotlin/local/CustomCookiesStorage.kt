package local

import io.ktor.client.plugins.cookies.*
import io.ktor.http.*
import io.ktor.util.date.*
import local.persistent.PersistentStorage
import local.persistent.structure.PersistentCookie

class CustomCookiesStorage(private val persistentStorage: PersistentStorage) : CookiesStorage {
    override suspend fun addCookie(requestUrl: Url, cookie: Cookie) {
        val pc = PersistentCookie(
            name = cookie.name,
            value = cookie.value,
            maxAge = cookie.maxAge,
            expires = cookie.expires?.timestamp,
            domain = cookie.domain,
            path = cookie.path,
            secure = cookie.secure,
            httpOnly = cookie.httpOnly,
            extensions = cookie.extensions
        )
        persistentStorage.storeCookie(requestUrl.host, pc)
    }

    override fun close() {}

    override suspend fun get(requestUrl: Url): List<Cookie> {
        return persistentStorage.getAllCookies(requestUrl.host)?.map {
            it.toCookie()
        } ?: emptyList()
    }

    private fun PersistentCookie.toCookie(): Cookie {
        return Cookie(
            name = this.name,
            value = this.value,
            encoding = CookieEncoding.URI_ENCODING,
            maxAge = this.maxAge ?: 0,
            expires = GMTDate(this.expires),
            domain = this.domain,
            path = this.path,
            secure = this.secure,
            httpOnly= this.httpOnly,
            extensions = this.extensions ?: emptyMap()
        )
    }
}