package co.tiagoaguiar.ganheinamega.util

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.core.app.NotificationCompat.MessagingStyle.Message
import com.google.android.material.snackbar.Snackbar
import kotlin.time.Duration

fun toast(context: Context, message: String, duration: Int) {
    Toast.makeText(context, message, duration).show()
}

fun snackBar(view: View, message: String, duration: Int) {
    Snackbar.make(view, message, duration).show()
}