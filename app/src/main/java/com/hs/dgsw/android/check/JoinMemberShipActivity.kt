package com.hs.dgsw.android.check

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hs.dgsw.android.check.databinding.ActivityJoinMembershipBinding
import com.hs.dgsw.android.check.local.TokenEntity

class JoinMemberShipActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding by lazy{
            ActivityJoinMembershipBinding.inflate(layoutInflater)
        }
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var classNumber = binding.classNumber

        binding.joinMemberBtn.setOnClickListener{
            var classNumberSave = classNumber.text.toString()
        }
    }
}