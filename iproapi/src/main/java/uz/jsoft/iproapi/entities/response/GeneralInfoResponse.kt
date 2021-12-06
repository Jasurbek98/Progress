package uz.jsoft.iproapi.entities.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Jasurbek Kurganbaev on 12/4/2021 9:37 PM
 **/
internal data class GeneralInfoResponse(
    val data: Data?,
    @SerializedName("resultOperation")
    val error: OperationResult?
)

data class Data(
    val typeBonusName: String,
    val forBurningQuantity: Int,
    val dateBurning: String,
    val currentQuantity: Int
)