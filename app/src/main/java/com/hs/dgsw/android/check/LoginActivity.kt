package com.hs.dgsw.android.check

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.hs.dgsw.android.check.databinding.ActivityLoginBinding
import com.hs.dgsw.android.check.local.CheckInDataBase
import com.hs.dgsw.android.check.local.TokenEntity
import com.hs.dgsw.android.check.remote.RetrofitBuilder
import com.hs.dgsw.android.check.remote.request.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.internal.wait


class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val googleSignInClient: GoogleSignInClient by lazy { getGoogleClient() }
    private val googleAuthLauncher =
        this.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            Log.d("TAG", ": ${result.data}")
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                val token = task.result

                // Log and toast
//                val msg = getString(R.string.msg_token_fmt, token)
//                Log.d(TAG, msg)
//                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            })
            try {
                val account = task.getResult(ApiException::class.java)

                val userName = account.givenName
                val serverAuth = account.serverAuthCode
                val idToken = account.idToken ?: ""
                //token = "edvBAy-vQ7OpcB6tKavlvD:APA91bFkMpP0SSSSpxJW0rOpTUtMGYGw4WPAvMe_5uBiIN7Rxxhe_aIYhh54vm4JHeUpLUs8_pEUlqFPF9tfMo0gM-EofW6mrK35_I7WxAAfv-8MZ0KQz3xK4dCf4yQuFMuD12vwhfpX"
                var fcmToken = ""
                // 액티비티 이동해야함
//                moveSignUpActivity()
                lifecycleScope.launch(Dispatchers.IO) {
                    runBlocking {
                        fcmToken = CheckInDataBase.getInstance(applicationContext)?.fcmTokenDao()?.getMembers()?.fcmToken.toString()
                    }

                    RetrofitBuilder.getLoginService().login(
                        LoginRequest(
                            idToken = idToken,
                            fcmToken = fcmToken
                        )
                    ).let { result ->

                        CheckInDataBase.getInstance(applicationContext)?.tokenDao()?.insertMember(
                            TokenEntity(
                                accessToken = result.accessToken,
                                refreshToken = result.refreshToken
                            )
                        ).let {
                            // 서버에서 정보가 있으면 메인으로 가고 없으면 회원가입 하고 서버로 보내기
                            Log.d("TAG", ": 성공했다잉 아잉")

                            lifecycleScope.launch(Dispatchers.Main) {
                                val intent =
                                    Intent(applicationContext, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                        }
                    }
                }
            } catch (e: ApiException) {
                Log.e(LoginActivity::class.java.simpleName, e.stackTraceToString())
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initButton()
//        val googleSignInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestServerAuthCode(getString(R.string.google_login_client_id))
//            .requestEmail()

    }

    private fun getGoogleClient(): GoogleSignInClient {
        val googleSignInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestScopes(Scope("https://www.googleapis.com/auth/pubsub"))
//            .requestServerAuthCode(getString(R.string.google_login_client_id))
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(this, googleSignInOption)
    }

    private fun requestGoogleLogin() {
        googleSignInClient.signOut()
        val signInIntent = googleSignInClient.signInIntent
        googleAuthLauncher.launch(signInIntent)
    }


    override fun onStart() {
        super.onStart()
// 사용자가 이미 로그인한 경우 기존 Google 로그인 계정을 확인합니다.
// GoogleSignInAccount는 null이 아닙니다.
        val account = GoogleSignIn.getLastSignedInAccount(this)
        Log.d("TAG", "onStart: ${account?.idToken}")
//        updateUI(account);

    }

    private fun initButton() {
        with(binding) {
            googleLoginBtn.setOnClickListener {
                requestGoogleLogin()
            }
        }
    }
}