package uz.jsoft.iproapi.data

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import uz.jsoft.iproapi.utils.ACCESS_KEY
import uz.jsoft.iproapi.utils.BASE_URL
import uz.jsoft.iproapi.utils.CACHE_KEY_TOKEN

/**
 * Created by Jasurbek Kurganbaev on 12/4/2021 9:33 PM
 **/
internal class Retrofit2(context: Context) {

    private val sharedPref = context.getSharedPreferences(CACHE_KEY_TOKEN, Context.MODE_PRIVATE)

    private val client = OkHttpClient.Builder()
        .addInterceptor(AddAccessKeyInterceptor())
        .addInterceptor(ChuckerInterceptor.Builder(context).build())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val iProBonusApi: IProBonusApi = retrofit.create()

    inner class AddAccessKeyInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {

            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()

            sharedPref.getString(ACCESS_KEY, "")?.let { key ->
                requestBuilder.addHeader(ACCESS_KEY, key)
            }

            return chain.proceed(requestBuilder.build())
        }
    }
}