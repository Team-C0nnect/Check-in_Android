package com.hs.dgsw.android.check

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hs.dgsw.android.check.databinding.ActivityMovieApplyBinding
import com.hs.dgsw.android.check.databinding.ActivityMovieBinding

class MovieApplyActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMovieApplyBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.movieApplyBtn.setOnClickListener {

            // 서버로 보내고

            // 메인으로 돌아가기
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}