package com.restart.dataalarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

//파이어베이스 메시지를 처리하기 위한 클래스생성 FirebaseMessagingService상속
class MyFirebaseMessagingService : FirebaseMessagingService() {

    //온뉴토큰 오버라이딩 왜? => 토큰은 언제든지 변경될 가능성이 있기 때문에 실제 앱을 출시할 경우애는 토큰이 갱신될때마다 해당 토큰이 서버에 갱신되도록 해야 함.
    //지금앱은 학습용이기 때문에 특별한 설정은 하지 않겠음
    override fun onNewToken(token: String) {
        super.onNewToken(token)

    }

    //메시지를 받았을때 처리하는 함수 오버라이딩.파이어베이스 클라우드 메시지에서 메시지를 수신할때마다 온메시지리시브드를 호출하게 된다.
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        createNotificationChannel()

        val title = remoteMessage.data["title"]
        val message = remoteMessage.data["message"]
        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        NotificationManagerCompat.from(this)
            .notify(1, notificationBuilder.build())
    }

    //알림채널 만들기
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES .O){ //기기 버전이 오래오 버전 이상이면
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = CHANNEL_DESCRIPTION

            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
                .createNotificationChannel(channel)
        }
    }

    companion object {
        private const val CHANNEL_NAME = "Emoji Party"
        private const val CHANNEL_DESCRIPTION = "Emoji Party를 위한 채널"
        private const val CHANNEL_ID = "Channel Id"
    }
}