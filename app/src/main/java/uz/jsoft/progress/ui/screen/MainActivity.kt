package uz.jsoft.progress.ui.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import uz.jsoft.iproapi.IProBonusClient
import uz.jsoft.iproapi.entities.DataWrapper
import uz.jsoft.iprofrag.BonusFragment
import uz.jsoft.progress.R
import uz.jsoft.progress.data.MainRepositoryImpl
import uz.jsoft.progress.databinding.ActivityMainBinding
import uz.jsoft.progress.ui.viewmodel.MainViewModel
import uz.jsoft.progress.ui.viewmodel.MainViewModelFactory
import uz.jsoft.progress.utils.ACCESS_KEY
import uz.jsoft.progress.utils.CLIENT_ID
import uz.jsoft.progress.utils.DEVICE_ID

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private val viewModel by lazy { initViewModel() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.isLoading.observe(this) {
            binding.pbLoading.isVisible = it
        }

        viewModel.responseState.observe(this) { wrapper ->
            when (wrapper) {
                is DataWrapper.Success -> {

                    val ft = supportFragmentManager.beginTransaction()
                    ft.add(
                        R.id.frag,
                        BonusFragment.newInstance(
//asd                            Pair(wrapper.data.currentQuantity, wrapper.data.typeBonusName)
                            wrapper.data.currentQuantity,
                            wrapper.data.forBurningQuantity,
                            wrapper.data.dateBurning
                        )
                    ).commit()
                }
                is DataWrapper.Error -> Toast.makeText(this, wrapper.message, Toast.LENGTH_SHORT)
                    .show()
            }

        }

    }


    private fun initViewModel() = ViewModelProvider(
        this,
        MainViewModelFactory(
            MainRepositoryImpl(
                IProBonusClient(
                    ACCESS_KEY,
                    this,
                    CLIENT_ID,
                    DEVICE_ID
                )
            )
        )
    ).get(MainViewModel::class.java)
}