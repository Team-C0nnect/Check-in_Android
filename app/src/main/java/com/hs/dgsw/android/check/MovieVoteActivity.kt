package com.hs.dgsw.android.check

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hs.dgsw.android.check.databinding.ActivityMovieVoteBinding

class MovieVoteActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMovieVoteBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.movieVoteBtn.setOnClickListener{

            // 서버로 보내고


            // 시작 화면으로 돌아가기
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}