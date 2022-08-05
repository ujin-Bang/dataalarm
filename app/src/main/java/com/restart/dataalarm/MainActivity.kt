package com.restart.dataalarm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    private val resultTextView: TextView by lazy {
        findViewById(R.id.resultTextView)
    }

    private val firebaseToken: TextView by lazy {
        findViewById(R.id.firebaseTokenTextView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFirebase()
        updateResult()

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        setIntent(intent)
        updateResult(true)
    }

    //파이어 베이스 토큰 불러오기
    private fun initFirebase() {
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    firebaseToken.text = it.result
                    Log.d("파이어베이스 토큰값", it.result.toString())
                }
            }
    }

    private fun updateResult(isNewIntent: Boolean = false) {
        resultTextView.text = (intent.getStringExtra("notificationType") ?: "앱런처")
        if (isNewIntent) {
            "(으)로 갱신했습니다."
        } else {
            "(으)로 실행했습니다."
        }
    }
}