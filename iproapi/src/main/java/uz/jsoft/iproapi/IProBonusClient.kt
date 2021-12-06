package uz.jsoft.iproapi

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import uz.jsoft.iproapi.data.Retrofit2
import uz.jsoft.iproapi.entities.DataWrapper
import uz.jsoft.iproapi.entities.request.AccessTokenRequest
import uz.jsoft.iproapi.entities.response.Data
import uz.jsoft.iproapi.utils.ACCESS_KEY
import uz.jsoft.iproapi.utils.ACCESS_TOKEN
import uz.jsoft.iproapi.utils.CACHE_KEY_TOKEN

/**
 * Created by Jasurbek Kurganbaev on 12/4/2021 9:43 PM
 **/
class IProBonusClient(
    accessKey: String,
    private val context: Context,
    private val clientId: String,
    private val deviceId: String,
    private val latitude: Float = 1f,
    private val longitude: Float = 1f,
    private val sourceQuery: Int = 1
) {
    private val api by lazy { Retrofit2(context).iProBonusApi }
    private val sharedPref by lazy {
        context.getSharedPreferences(CACHE_KEY_TOKEN, Context.MODE_PRIVATE)
    }
    private val editor by lazy { sharedPref.edit() }

    init {
        editor.putString(ACCESS_KEY, accessKey).commit()
    }

    suspend fun getData(): DataWrapper<Data> = withContext(Dispatchers.IO) {
        var token = sharedPref.getString(ACCESS_TOKEN, null)

        val wrapper = if (token == null) {
            when (val tokenResponse = getAccessToken()) {
                is DataWrapper.Success -> {
                    token = tokenResponse.data
                    getGeneralInfo(token)
                }
                is DataWrapper.Error -> DataWrapper.Error(
                    tokenResponse.message,
                    tokenResponse.error
                )
            }
        } else {
            getGeneralInfo(token)
        }

        wrapper
    }

    private suspend fun getAccessToken(): DataWrapper<String> =
        withContext(Dispatchers.IO) {
            val response = api.getAccessToken(createTokenRequest())

            val wrapper: DataWrapper<String> = when {
                response.isSuccessful && response.body()?.accessToken != null -> {
                    editor.putString(ACCESS_TOKEN, response.body()?.accessToken).commit()
                    DataWrapper.Success(response.body()?.accessToken!!)
                }
                response.isSuccessful -> DataWrapper.Error(
                    response.body()?.error?.message ?: "",
                    response.body()?.error
                )
                else -> DataWrapper.Error(response.message() ?: "")
            }

            wrapper
        }

    private suspend fun getGeneralInfo(token: String): DataWrapper<Data> =
        withContext(Dispatchers.IO) {
            val response = api.getGeneralInfo(token)

            when {
                response.isSuccessful && response.body()?.data != null ->
                    DataWrapper.Success(response.body()?.data!!)
                response.isSuccessful -> DataWrapper.Error(
                    response.body()?.error?.message ?: "",
                    response.body()?.error
                )
                else -> DataWrapper.Error(response.message() ?: "")
            }
        }

    private fun createTokenRequest() = AccessTokenRequest(
        clientId,
        deviceId,
        latitude,
        longitude,
        sourceQuery
    )
}