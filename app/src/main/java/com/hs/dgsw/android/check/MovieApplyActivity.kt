package com.hs.dgsw.android.check

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.hs.dgsw.android.check.databinding.ActivityMovieApplyBinding
import com.hs.dgsw.android.check.databinding.ActivityMovieBinding
import com.hs.dgsw.android.check.remote.RetrofitBuilder
import com.hs.dgsw.android.check.remote.request.MovieApplyRequest
import com.hs.dgsw.android.check.remote.request.StudentRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieApplyActivity : AppCompatActivity() {



    private val binding by lazy {
        ActivityMovieApplyBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var movieApply = binding.movieName



        binding.movieApplyBtn.setOnClickListener {
            var movieApplySave = movieApply.text.toString()

            // 서버로 보내고
            lifecycleScope.launch(Dispatchers.IO){
                kotlin.runCatching {
                    RetrofitBuilder.getMovieApplyService().postMovieApply(
                        body = MovieApplyRequest(
                            title = movieApplySave
                        )
                    )
                }.onSuccess {
                    Log.d(TAG, "성공 ㅇㅇㅇㅇㅇㅇ: $it")
                }.onFailure {
                    Log.d(TAG, "실패: $it")
                }
            }

            // 메인으로 돌아가기
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}