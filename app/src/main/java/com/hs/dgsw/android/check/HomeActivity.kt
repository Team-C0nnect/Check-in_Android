package com.hs.dgsw.android.check

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.icu.text.Collator.ReorderCodes
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.hs.dgsw.android.check.databinding.ActivityHomeBinding
import java.security.Permission


class HomeActivity : Fragment(), MainActivity.onBackPressedListener{
    companion object{
        private const val TAG = "로그"


        fun newInstance(): HomeActivity {
            return HomeActivity()
        }

        val CAMERA_PERMISSION = arrayOf(Manifest.permission.CAMERA)
        val FLAG_PERM_CAMERA = 98
        val FLAG_REQ_CAMERA = 101

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
        Log.d(TAG, "onCreateView: ")
        val binding = ActivityHomeBinding.inflate(inflater)
//        val view = inflater.inflate(R.layout.activity_home, container, false)

        // 출석 체크 확인
        binding.attendanceCheck.setOnClickListener() {
            if(isPermitted(CAMERA_PERMISSION)){
                openCamera()
            } else{
                ActivityCompat.requestPermissions(requireActivity(), CAMERA_PERMISSION, FLAG_PERM_CAMERA)
            }

        }

        // 외박 신청
        binding.goHomeApply.setOnClickListener{
            Log.d(TAG, "onCreate: 됬어!")
            val goHomeFragment = GoHomeActivity.newInstance()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentFrame, goHomeFragment)
                .commit()
        }

        // 영화 투표
        binding.movieVote.setOnClickListener{
            Log.d(TAG, "onCreate: 됬어!")
            val intent = Intent(activity, MovieVoteActivity::class.java)
            startActivity(intent)
        }

        // 영화 신청
        binding.movieApply.setOnClickListener(){
            Log.d(TAG, "onCreate: 됬어!")
            val intent = Intent(activity, MovieApplyActivity::class.java)
            startActivity(intent)
        }

        // 값이 만족되면 텍스트 봐꾸기
//        var a = 0
//        if (a == 0){
//            binding.attendanceCheck.setText("출석 체크가 완료됬어요!!")
//        }

        return binding.root
    }

    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }


    // 카메라 권한 확인
    private fun isPermitted(permissions: Array<String>) : Boolean {

        for (permission in permissions){
            val result = ContextCompat.checkSelfPermission(requireContext(), permission)
            if (result != PackageManager.PERMISSION_GRANTED){
                return false
            }
        }
        return true
    }

    // 카메리 열기
    private fun openCamera(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, FLAG_REQ_CAMERA)
    }

    // 사진 찍으면
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {
            when(requestCode) {
                FLAG_REQ_CAMERA -> {
//                    val bitmap = data?.extras?.get("data") as Bitmap
                }
            }
        }
    }


    // 카메라 권환 요첨
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            FLAG_PERM_CAMERA -> {
                var checked = true
                for (grant in grantResults){
                    if (grant != PackageManager.PERMISSION_GRANTED){
                        checked = false
                        break
                    }
                }
                if (checked){
                    openCamera()
                }
            }
        }
    }
}