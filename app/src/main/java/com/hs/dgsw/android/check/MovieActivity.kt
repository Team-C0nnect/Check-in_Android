package com.hs.dgsw.android.check

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hs.dgsw.android.check.databinding.ActivityHomeBinding
import com.hs.dgsw.android.check.databinding.ActivityMovieBinding


class MovieActivity : Fragment(), MainActivity.onBackPressedListener{
    companion object{

        private const val TAG = "로그"

        fun newInstance(): MovieActivity {
            return MovieActivity()
        }
    }
//  메모리에 올라갔을 때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
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


    val binding = ActivityMovieBinding.inflate(inflater)
    Log.d(TAG, "onCreateView: ")
//        val view = inflater.inflate(R.layout.activity_movie, container, false)
    // 영화 투표
    binding.goMovieVoteBtn.setOnClickListener{
        val intent = Intent(activity, MovieVoteActivity::class.java)
        startActivity(intent)
    }

    // 영화 신청
    binding.goMovieApplyBtn.setOnClickListener{
        val intent = Intent(activity, MovieApplyActivity::class.java)
        startActivity(intent)
    }





    return binding.root
    }

    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }
}
