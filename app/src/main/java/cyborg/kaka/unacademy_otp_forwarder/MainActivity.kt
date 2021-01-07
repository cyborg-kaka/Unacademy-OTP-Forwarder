package cyborg.kaka.unacademy_otp_forwarder

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestSmsPermission()
    }

    private fun requestSmsPermission() {
        val permissions = arrayOf(Manifest.permission.RECEIVE_SMS,Manifest.permission.SEND_SMS)
        for (permission in permissions) {
            val grant = ContextCompat.checkSelfPermission(this, permission)
            if (grant != PackageManager.PERMISSION_GRANTED) {
                val permissionList = arrayOfNulls<String>(1)
                permissionList[0] = permission
                ActivityCompat.requestPermissions(this, permissionList, 1)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {

            requestSmsPermission()
            finish()
        }
    }
}