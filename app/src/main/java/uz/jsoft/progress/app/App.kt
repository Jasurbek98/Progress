package uz.jsoft.progressterra.app

import android.app.Application

/**
 * Created by Jasurbek Kurganbaev on 12/5/2021 11:09 PM
 **/
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance: App
    }
}