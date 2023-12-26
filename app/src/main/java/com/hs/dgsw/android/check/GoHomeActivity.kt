package com.hs.dgsw.android.check

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hs.dgsw.android.check.databinding.ActivityGoHomeBinding
import android.app.DatePickerDialog
import androidx.lifecycle.lifecycleScope
import com.hs.dgsw.android.check.remote.RetrofitBuilder
import com.hs.dgsw.android.check.remote.request.GoHomeRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar

class GoHomeActivity : Fragment(), MainActivity.onBackPressedListener {

    var returnDayData = ""
    var passDayData = ""

    companion object{
        const val TAG = "로그"

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
        binding.passDay.setOnClickListener {
            val cal = Calendar.getInstance()
            val dateSetListener =
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    passDayData = "${year}년 ${month + 1}월 ${dayOfMonth}일"
                }
            DatePickerDialog(requireContext(), dateSetListener, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()
            binding.passDay.setText(passDayData)
        }

        // 복귀 날짜 누르면 캘린더 보여주기
        binding.returnDay.setOnClickListener{
            val cal = Calendar.getInstance()
            val dateSetListener =
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    returnDayData = "${year}년 ${month + 1}월 ${dayOfMonth}일"
                }
            DatePickerDialog(requireContext(), dateSetListener, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()
            binding.returnDay.setText(returnDayData)
        }

        var reason = binding.reason
        var reasonSave = reason.text.toString()

        // 외박 날짜, 복귀 날짜, 샤유, 서버로 보내기
        binding.sleepoverBtn.setOnClickListener{

            // 외박 날짜, 복귀 날짜, 샤유, 서버로 보내고
            lifecycleScope.launch(Dispatchers.IO){
                kotlin.runCatching {
                    RetrofitBuilder.getGoHomeService().postGoHome(
                        body = GoHomeRequest(
                            userId = 0,
                            reason = reasonSave,
                            approval = "",
                            startDateTime = passDayData,
                            endDateTime = returnDayData
                        )
                    )
                }.onSuccess {
                    Log.d(TAG, "성공: $it")
                }.onFailure {
                    Log.d(TAG, "실패: $it")
                }
            }


            // 메인으로 돌아가기
            val HomeFragment = HomeActivity.newInstance()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentFrame, HomeFragment)
                .commit()
        }
        return binding.root
    }

    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }
}