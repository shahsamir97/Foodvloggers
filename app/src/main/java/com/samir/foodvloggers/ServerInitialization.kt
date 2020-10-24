package com.samir.foodvloggers


import android.app.Application
import android.util.Log
import com.parse.Parse
import com.parse.ParseException
import com.parse.ParseInstallation
import com.parse.ParseUser


class ServerInitialization : Application() {
    override fun onCreate() {
        super.onCreate()


        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id)) // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server("https://parseapi.back4app.com/")
                .build()
        )

        ParseInstallation.getCurrentInstallation()
            .saveInBackground { e ->
                if (e == null) {
                    Log.i("Parse", "Installation saved successfully")
                } else {
                    Log.e("Parse", "Installation failed to save: ${e.message}")
                    if (e.code === ParseException.OBJECT_NOT_FOUND) {

                    }
                }
            }
    }

}