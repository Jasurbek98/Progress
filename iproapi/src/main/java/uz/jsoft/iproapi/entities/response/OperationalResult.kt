package uz.jsoft.iproapi.entities.response

/**
 * Created by Jasurbek Kurganbaev on 12/4/2021 9:36 PM
 **/
data class OperationResult(
    val duration: Int,
    val messageDev: String,
    val codeResult: Int,
    val message: String,
    val status: Int,
    val idLog: String
)