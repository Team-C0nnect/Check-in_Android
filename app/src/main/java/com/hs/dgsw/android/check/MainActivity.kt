package com.hs.dgsw.android.check

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hs.dgsw.android.check.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener{

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val TAG = "로그"

//    private lateinit var GoHomeActivity : GoHomeActivity
//    private lateinit var MovieActivity : MovieActivity



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Log.d(TAG, "onCreate: rr")


////         화면 전환
//        val intent = Intent(this, LoginActivity::class.java)
//        startActivity(intent)

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
}