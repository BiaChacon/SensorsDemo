package com.biachacon.sensorsdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.biometric.BiometricPrompt
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var executor: Executor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        executor = Executors.newSingleThreadExecutor()

        val biometricPrompt = BiometricPrompt(
            this,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                        setTextInfo(text = "Biometric CANCELLED")
                    } else {
                        setTextInfo(text = "Biometric ERROR")
                    }
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    setTextInfo(text = "Biometric OK")
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    setTextInfo(text = "Biometric ERROR")
                }
            })

        biometric.setOnClickListener {
            if (isBiometricAvailable(applicationContext)) {
                val promptInfo = BiometricPrompt.PromptInfo.Builder()
                    .setTitle("Biometric Authentication")
                    .setSubtitle("")
                    .setDescription("Place your finger on the sensor")
                    .setNegativeButtonText("Cancel")
                    .build()
                biometricPrompt.authenticate(promptInfo)
            } else {
                setTextInfo(text = "Hardware not supported")
            }
        }

        proximity.setOnClickListener {
            var i = Intent(this, ProximityActivity::class.java)
            startActivity(i)
        }

        accelerometer.setOnClickListener {
            var i = Intent(this, AccelerometerActivity::class.java)
            startActivity(i)
        }

        light.setOnClickListener {
            var i = Intent(this, LightActivity::class.java)
            startActivity(i)
        }

        gyroscope.setOnClickListener {
            var i = Intent(this, GyroscopeActivity::class.java)
            startActivity(i)
        }

    }

    private fun setTextInfo(text: String) {
        runOnUiThread {

        }
    }

}
