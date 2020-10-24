package com.samir.foodvloggers

import android.app.Application
import com.parse.Parse

class Ec2Server : Application() {
    override fun onCreate() {
        super.onCreate()
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId("myappID") // if defined
                .clientKey("W2ivnDxyPsfy")
                .server("http://localhost:1337/parse/")
                .build()
        )
    }
}