package com.biachacon.sensorsdemo

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.hardware.SensorManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_proximity.*

class ProximityActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var mProximity: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proximity)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mProximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

         Log.i("TEST", "test")


    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // Do something here if sensor accuracy changes.
    }

    override fun onSensorChanged(event: SensorEvent) {
        val distance = event.values[0]
        distanceText.text = distance.toString()
        // Do something with this sensor data.
        Log.i("TEST", "Success! There's a pressure sensor. $distance")
    }

    override fun onResume() {
        // Register a listener for the sensor.
        super.onResume()

        mProximity?.also { proximity ->
            sensorManager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause()
        sensorManager.unregisterListener(this)
    }

}