package com.hs.dgsw.android.check

import android.R
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.hs.dgsw.android.check.databinding.ActivityMovieVoteBinding
import com.hs.dgsw.android.check.remote.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MovieVoteActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMovieVoteBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)




        lifecycleScope.launch(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitBuilder.getMovieApplyService().getMovieVote()
            }.onSuccess {
                Log.d(TAG, "성공: $it")

//                val movieList = ArrayList<String>()
//                val listView = binding.movieListView
//
//                val adapter = ArrayAdapter(this@MovieVoteActivity, R.layout.simple_list_item_1, movieList)
//                listView.adapter = adapter
//
//                val editText: EditText = binding.etitText
//                val btn: Button = binding.btn
//
//                btn.setOnClickListener {
//                    movieList.add(editText.text.toString())
//                    adapter.notifyDataSetChanged()
//                }
//
//                listView.setOnItemLongClickListener { parent, view, position, id ->
//                    movieList.removeAt(position)
//                    adapter.notifyDataSetChanged()
//                    false
//                }
            }.onFailure {

            }
        }

        binding.movieVoteBtn.setOnClickListener{

            // 서버로 보내고

            // 시작 화면으로 돌아가기
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}