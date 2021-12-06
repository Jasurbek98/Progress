package uz.jsoft.progress.ui.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.jsoft.iproapi.entities.DataWrapper
import uz.jsoft.iproapi.entities.response.Data
import uz.jsoft.progress.data.MainRepositoryImpl

/**
 * Created by Jasurbek Kurganbaev on 12/5/2021 11:52 PM
 **/
class MainViewModel(private val repo: MainRepositoryImpl) : ViewModel() {

    private val _responseState: MutableLiveData<DataWrapper<Data>> = MutableLiveData()
    val responseState: LiveData<DataWrapper<Data>> = _responseState

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        initData()
    }

    private fun initData() = viewModelScope.launch(Dispatchers.IO) {
        _isLoading.postValue(true)
        _responseState.postValue(repo.getData())
        _isLoading.postValue(false)

    }

}


class MainViewModelFactory(private val repo: MainRepositoryImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repo) as T
        }
        throw Exception("asd")
    }

}