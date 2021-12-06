package uz.jsoft.iproapi.entities.response

/**
 * Created by Jasurbek Kurganbaev on 12/4/2021 9:35 PM
 **/


import com.google.gson.annotations.SerializedName

internal data class AccessTokenResponse(
    @SerializedName("result")
    val error: OperationResult?,
    val accessToken: String?
)