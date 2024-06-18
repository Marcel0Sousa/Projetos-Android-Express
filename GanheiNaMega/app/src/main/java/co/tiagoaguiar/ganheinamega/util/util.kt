package co.tiagoaguiar.ganheinamega.util

import android.content.Context
import android.widget.Toast
import androidx.core.app.NotificationCompat.MessagingStyle.Message
import kotlin.time.Duration

fun toast(context: Context, message: String, duration: Int) {
    Toast.makeText(context, message, duration).show()
}