package uz.jsoft.progress.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.jsoft.iproapi.IProBonusClient
import uz.jsoft.iproapi.entities.DataWrapper
import uz.jsoft.iproapi.entities.response.Data

/**
 * Created by Jasurbek Kurganbaev on 12/5/2021 11:46 PM
 **/
class MainRepositoryImpl(private val api: IProBonusClient) : MainRepository {

    override suspend fun getData(): DataWrapper<Data> =
        withContext(Dispatchers.IO) { api.getData() }
}