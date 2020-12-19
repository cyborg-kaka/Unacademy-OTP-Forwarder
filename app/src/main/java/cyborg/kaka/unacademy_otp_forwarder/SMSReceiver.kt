package cyborg.kaka.unacademy_otp_forwarder

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.SmsManager
import android.widget.Toast

class SMSReceiver : BroadcastReceiver() {
    private val smsFilter = arrayOf("OTP", "Unacademy") // Filter Words of Unacademy's OTP SMS
    private val recipients = arrayOf("9876543210", "1234567890") // Numbers to forward SMS

    // On SMS Received
    override fun onReceive(context: Context, intent: Intent) {
        val message =
            Telephony.Sms.Intents.getMessagesFromIntent(intent)[0].messageBody

        // Check if it's Unacademy's OTP SMS
        if (smsFilter.all { it in message }) {
            val pi = PendingIntent.getActivity(context, 0, intent, 0)
            val sms = SmsManager.getDefault()

            // Forward to every recipient
            for (number in recipients) {
                sms.sendTextMessage(number, null, message, pi, null)
                Toast.makeText(context, "Unacademy OTP forwarded to $number", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}