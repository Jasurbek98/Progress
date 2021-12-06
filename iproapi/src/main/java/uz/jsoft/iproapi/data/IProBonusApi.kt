package uz.jsoft.iproapi.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import uz.jsoft.iproapi.entities.request.AccessTokenRequest
import uz.jsoft.iproapi.entities.response.AccessTokenResponse
import uz.jsoft.iproapi.entities.response.GeneralInfoResponse

/**
 * Created by Jasurbek Kurganbaev on 12/4/2021 9:32 PM
 **/
internal interface IProBonusApi {

    @POST("api/v3/clients/accesstoken")
    suspend fun getAccessToken(@Body accessTokenRequest: AccessTokenRequest): Response<AccessTokenResponse>

    @GET("api/v3/ibonus/generalinfo/{AccessToken}\n")
    suspend fun getGeneralInfo(@Path("AccessToken") token: String): Response<GeneralInfoResponse>
}