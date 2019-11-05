package com.biachacon.sensorsdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

}
