package id.buaja.pagination.network

import id.buaja.pagination.network.model.ResponseMovie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created By Julsapargi Nursam 4/18/20
 */

interface ApiService {
    @GET("movie/now_playing?")
    suspend fun getMovie(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("page") page: Int?
    ): Response<ResponseMovie>
}