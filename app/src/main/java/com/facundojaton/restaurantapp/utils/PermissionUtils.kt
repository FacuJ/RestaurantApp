package com.autosolve.yasale.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.nio.file.Files.size


object PermissionUtils {
    private val TAG: String = PermissionUtils::class.java.simpleName

    val CAMERA_REQUEST_CODE = 4
    val READ_STORAGE_REQUEST_CODE = 5
    val WRITE_STORAGE_REQUEST_CODE = 6
    val CALL = 7

    fun isCameraPermissionGranted(context: Context): Boolean {
        return isPermissionGranted(context, Manifest.permission.CAMERA)
    }

    fun isReadStoragePermissionGranted(context: Context): Boolean {
        return isPermissionGranted(context, Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    fun isWriteStoragePermissionGranted(context: Context): Boolean {
        return isPermissionGranted(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    fun isCallPhonePermissionGranted(context: Context): Boolean {
        return isPermissionGranted(context, Manifest.permission.CALL_PHONE)
    }

    fun askForUseCameraPermission(fragment: Fragment) {
        askForPermission(fragment, Manifest.permission.CAMERA, CAMERA_REQUEST_CODE)
    }

    fun askForReadStoragePermission(fragment: Fragment) {
        askForPermission(fragment, Manifest.permission.READ_EXTERNAL_STORAGE, READ_STORAGE_REQUEST_CODE)
    }

    fun askForWriteStoragePermission(fragment: Fragment) {
        askForPermission(fragment, Manifest.permission.WRITE_EXTERNAL_STORAGE, WRITE_STORAGE_REQUEST_CODE)
    }

    fun askForWriteStoragePermission(activity: Activity) {
        askForPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE, WRITE_STORAGE_REQUEST_CODE)
    }

    fun askForCallPhonePermission(activity: Activity) {
        askForPermission(activity, Manifest.permission.CALL_PHONE, CALL)
    }

    fun isPermissionGranted(context: Context, permission: String): Boolean {
        return if (Build.VERSION.SDK_INT >= 23) {
            ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission $permission is granted")
            true
        }
    }

    fun askForPermission(fragment: Fragment, permission: String, requestCode: Int) {
        fragment.activity?.let {
            if (ContextCompat.checkSelfPermission(it.applicationContext, permission) != PackageManager.PERMISSION_GRANTED) {
                // Should we show an explanation?
                if (fragment.shouldShowRequestPermissionRationale(permission)) {
                    //This is called if user has denied the permission before
                    //In this case I am just asking the permission again
                    fragment.requestPermissions(arrayOf(permission), requestCode)
                } else {
                    fragment.requestPermissions(arrayOf(permission), requestCode)
                }
            } else {
                Log.v(TAG, "Permission $permission is granted")
            }
        }
    }

    fun askForPermission(activity: Activity, permission: String, requestCode: Int) {
        activity.runOnUiThread {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                    //This is called if user has denied the permission before
                    //In this case I am just asking the permission again
                    ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
                } else {
                    ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
                }
            } else {
                Log.v(TAG, "Permission $permission is granted")
            }
        }
    }

}
