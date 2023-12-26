package com.hs.dgsw.android.check

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import kotlin.math.log


class UserProfileActivity : Fragment(), MainActivity.onBackPressedListener{

    companion object{
        private const val TAG = "로그"

        fun newInstance(): UserProfileActivity {
            return UserProfileActivity()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: 유저")
    }
    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }
}