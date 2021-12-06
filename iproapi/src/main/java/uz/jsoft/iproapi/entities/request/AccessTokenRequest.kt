package uz.jsoft.iproapi.entities.request

/**
 * Created by Jasurbek Kurganbaev on 12/4/2021 9:35 PM
 **/
internal data class AccessTokenRequest(
    val idClient: String,
    val paramValue: String,
    val latitude: Float,
    val longitude: Float,
    val sourceQuery: Int,
    val accessToken: String = "",
    val paramName: String = "device",
)