package com.hs.dgsw.android.check

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hs.dgsw.android.check.databinding.ActivityMainBinding
import com.hs.dgsw.android.check.local.CheckInDataBase
import com.hs.dgsw.android.check.remote.RetrofitBuilder
import com.hs.dgsw.android.check.remote.request.LoginRequest
import com.hs.dgsw.android.check.remote.response.StudentResponse
import com.hs.dgsw.android.check.remote.service.StudentService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okio.Timeout
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import kotlin.math.log


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener{

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val TAG = "로그"




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Log.d(TAG, "onCreate: rr")

        lifecycleScope.launch(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitBuilder.getStudentService().getStudent()
            }.onSuccess {
                Log.d(TAG, "성공: $it")
            }.onFailure {
                moveJoin()
            }
        }






        binding.bottomNav.setOnNavigationItemSelectedListener(this)

        val homeFragment = HomeActivity.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.fragmentFrame, homeFragment).commit()


    }



    // 바텀 네비게이션 기능 구현
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.menu_movie -> {
                Log.d(TAG, "onNavigationItemSelected: 영화 버튼 클릭")
                val movieFragment = MovieActivity.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragmentFrame, movieFragment).commit()
            }
            R.id.menu_go_home -> {
                Log.d(TAG, "onNavigationItemSelected: 외박버튼 클릭")
                val goHomeFragment = GoHomeActivity.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragmentFrame, goHomeFragment).commit()

            }
            R.id.menu_home -> {
                Log.d(TAG, "onNavigationItemSelected: 홈 버튼 클릭")
                val homeFragment = HomeActivity.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragmentFrame, homeFragment).commit()
            }
        }

        return true
    }


    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            // FCM SDK (and your app) can post notifications.
        } else {
            // TODO: Inform user that that your app will not show notifications.
        }
    }

    // 뒤로가기 구현
    interface onBackPressedListener{
        fun onBackPressed()
    }

    // 뒤로 가기 기능 구현
    override fun onBackPressed() {
        Log.d(TAG, "onBackPressed: 가즈아")
        val fragmentList = supportFragmentManager.fragments
        val homeFragment = HomeActivity.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentFrame, homeFragment)
            .commit()

        for (fragment in fragmentList){
            if(fragment is onBackPressedListener ) {
                (fragment as onBackPressedListener).onBackPressed()
                return
            }
        }
    }

    //요청알림권한
    private fun askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private fun moveJoin(){
        intent = Intent(this, JoinMemberShipActivity::class.java)
        startActivity(intent)
    }
}