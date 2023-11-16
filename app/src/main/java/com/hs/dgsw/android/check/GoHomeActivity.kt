package com.hs.dgsw.android.check

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hs.dgsw.android.check.databinding.ActivityGoHomeBinding
import com.hs.dgsw.android.check.databinding.ActivityHomeBinding

class GoHomeActivity : Fragment(), MainActivity.onBackPressedListener {
    companion object{
        private const val TAG = "로그"

        fun newInstance(): GoHomeActivity {
            return GoHomeActivity()
        }
    }
    //  메모리에 올라갔을 때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: 1")
    }
    //  프레그먼트를 안고 있는 액티비티를 올렸을 때
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach: ")
        
    }
    //  프레그 먼트와 레이아웃을 연결시켜줌
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: ")
        val binding = ActivityGoHomeBinding.inflate(inflater)

        // 외박 날짜 누르면 캘린더 보여주기
        binding.passDay.setOnClickListener{

        }

        // 복귀 날짜 누르면 캘린더 보여주기
        binding.returnDay.setOnClickListener{

        }

        // 외박 날짜, 복귀 날짜, 샤유, 서버로 보내기
        binding.sleepoverBtn.setOnClickListener{

        }


        return binding.root
    }

    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }
}