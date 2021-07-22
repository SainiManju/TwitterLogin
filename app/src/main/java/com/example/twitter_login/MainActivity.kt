package com.example.twitter_login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.identity.TwitterLoginButton

class MainActivity : AppCompatActivity() {
    var twitter_consumer_key = "PLInvfkxBEa5a6VV6N3ZSeOJV"
    var twitter_consumer_secret = "Ds37OOLP0SnknMdPt0w1gthvxCCbh6QUosHko2vbuj5XwlQL2z"
    var loginButton: TwitterLoginButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //  Twitter.initialize(this);
        val config = TwitterConfig.Builder(this)
            .logger(DefaultLogger(Log.DEBUG))
            .twitterAuthConfig(TwitterAuthConfig(twitter_consumer_key, twitter_consumer_secret))
            .debug(true)
            .build()
        Twitter.initialize(config)
        setContentView(R.layout.activity_main)
        loginButton = findViewById<View>(R.id.login_button) as TwitterLoginButton
        loginButton!!.callback = object : Callback<TwitterSession?>() {
            override fun success(result: Result<TwitterSession?>) {

                // Do something with result, which provides a TwitterSession for making API calls
                val session = TwitterCore.getInstance().sessionManager.activeSession
                val authToken = session.authToken
                loginMethod(session)
            }

            override fun failure(exception: TwitterException) {

                // Do something on failure
                Toast.makeText(applicationContext, "Login fail", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun loginMethod(twitterSession: TwitterSession) {
        val userName = twitterSession.userName
        val intent = Intent(this@MainActivity, HomeActivity::class.java)
        intent.putExtra("username", userName)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result to the login button.
        loginButton!!.onActivityResult(requestCode, resultCode, data)
    }
}