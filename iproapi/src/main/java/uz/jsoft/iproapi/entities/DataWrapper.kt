package uz.jsoft.iproapi.entities

import uz.jsoft.iproapi.entities.response.OperationResult

/**
 * Created by Jasurbek Kurganbaev on 12/4/2021 9:38 PM
 **/
sealed class DataWrapper<T : Any> {
    class Success<T : Any>(val data: T) : DataWrapper<T>()
    class Error<T : Any>(val message: String, val error: OperationResult? = null) : DataWrapper<T>()
}