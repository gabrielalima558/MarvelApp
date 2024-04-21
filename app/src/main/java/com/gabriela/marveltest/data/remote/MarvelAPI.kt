package com.gabriela.marveltest.data.remote

import com.gabriela.marveltest.data.remote.model.MarvelResult
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.Calendar
import java.util.TimeZone
import java.util.concurrent.TimeUnit

interface MarvelAPI {
    @GET("characters")
    suspend fun getMarvelCharacters(
        @Query("offset") offset: Int? = 1
    ): MarvelResult

    companion object {
        const val API_KEY = "595bf3ac203eef0c7723b8b1828b500b"
        const val PRIVATE_KEY = "664271b4e17e8e7c1e5631719d73fd1cb9cf38b2"
        fun getService(): MarvelAPI {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            lateinit var httpClient: OkHttpClient

            synchronized(this) {
                httpClient = OkHttpClient.Builder().apply {
                    addInterceptor { chain ->
                        crateParamsInterceptor(chain)
                    }
                    cache(null)
                    connectTimeout(60, TimeUnit.SECONDS)
                    readTimeout(60, TimeUnit.SECONDS)
                    writeTimeout(60, TimeUnit.SECONDS).build()
                    addInterceptor(logging)
                }.build()

                val gson = GsonBuilder().setLenient().create()
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://gateway.marvel.com/v1/public/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient)
                    .build()

                return retrofit.create(MarvelAPI::class.java)
            }
        }


        private fun generateHash(publicKey: String, privateKey: String, timeStamp: Long): String? {
            val hash = timeStamp.toString() + privateKey + publicKey
            try {
                val md = MessageDigest.getInstance("MD5")
                val messageDigest = md.digest(hash.toByteArray())
                val hexString = StringBuilder()
                for (b in messageDigest) {
                    val hex = Integer.toHexString(0xff and b.toInt())
                    if (hex.length == 1) hexString.append('0')
                    hexString.append(hex)
                }
                return hexString.toString()
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            }
            return ""
        }

        private fun crateParamsInterceptor(chain: Interceptor.Chain): Response {
            val original = chain.request()
            val originalHttpUrl = original.url
            val ts = (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L)
            val hash = generateHash( API_KEY, PRIVATE_KEY, ts) // Calculate MD5 hash
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("ts", ts.toString())
                .addQueryParameter("hash", hash) // Use calculated hash
                .build()
            return chain.proceed(original.newBuilder().url(url).build())
        }
    }
}