package com.hs.dgsw.android.check

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler


class ScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen)

        var handler = Handler()
        handler.postDelayed({
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }, 1500)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}