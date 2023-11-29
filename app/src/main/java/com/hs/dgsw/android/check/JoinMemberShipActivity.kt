package com.hs.dgsw.android.check

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.hs.dgsw.android.check.databinding.ActivityJoinMembershipBinding
import com.hs.dgsw.android.check.local.TokenEntity
import com.hs.dgsw.android.check.remote.RetrofitBuilder
import com.hs.dgsw.android.check.remote.request.StudentRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

            lifecycleScope.launch(Dispatchers.IO){
                kotlin.runCatching {
                    RetrofitBuilder.getStudentService().postStudent(
                        body = StudentRequest(
                            stdId = classNumberSave
                        )
                    )
                }.onSuccess {
                    Log.d(TAG, "성공 ㅇㅇㅇㅇㅇㅇ: $it")
                }.onFailure {
                    Log.d(TAG, "실패: $it")
                }
            }


            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}