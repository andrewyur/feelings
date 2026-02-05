package com.purple.feelings

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
// Added imports
import android.os.Build
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.OnApplyWindowInsetsListener
import kotlin.math.max

// Fix: wrap this into a utility class (singleton object) for global reuse
object WindowInsetsUtil {
    fun applySystemBarsPadding(view: View) {

        // If you need to check API version
        // if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.VANILLA_ICE_CREAM) {
        //     return
        // }

        ViewCompat.setOnApplyWindowInsetsListener(
            view,
            OnApplyWindowInsetsListener { v: View?, windowInsets: WindowInsetsCompat? ->
                val systemBars = windowInsets!!.getInsets(WindowInsetsCompat.Type.systemBars())
                val ime = windowInsets.getInsets(WindowInsetsCompat.Type.ime())
                v!!.setPadding(
                    systemBars.left,
                    systemBars.top,
                    systemBars.right,
                    max(systemBars.bottom, ime.bottom)
                )
                WindowInsetsCompat.CONSUMED
            })
    }
}

class MainActivity : TauriActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        // Call the utility function to fix keyboard overlap
        window.decorView?.let { WindowInsetsUtil.applySystemBarsPadding(it) }
    }
}

