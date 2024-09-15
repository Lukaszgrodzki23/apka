package com.example.myapplication

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.R

class StepCounterFragment : Fragment(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var stepSensor: Sensor? = null
    private var isRunning = false
    private var stepCount = 0
    private var initialStepCount = -1
    private lateinit var stepCountView: TextView
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var abortButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_step_counter, container, false)
        stepCountView = view.findViewById(R.id.step_count_view)
        startButton = view.findViewById(R.id.start_button)
        stopButton = view.findViewById(R.id.stop_button)
        abortButton = view.findViewById(R.id.abort_button)

        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if (stepSensor == null) {
            Toast.makeText(requireContext(), "No step sensor available!", Toast.LENGTH_SHORT).show()
        }

        startButton.setOnClickListener {
            startStepCounter()
        }

        stopButton.setOnClickListener {
            stopStepCounter()
        }

        abortButton.setOnClickListener {
            abortStepCounter()
        }

        return view
    }

    private fun startStepCounter() {
        if (stepSensor != null && !isRunning) {
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI)
            isRunning = true
            initialStepCount = -1
            Toast.makeText(requireContext(), "Step counter started", Toast.LENGTH_SHORT).show()
        }
    }

    private fun stopStepCounter() {
        if (isRunning) {
            sensorManager.unregisterListener(this, stepSensor)
            isRunning = false
            saveStepCount()
            Toast.makeText(requireContext(), "Step counter stopped", Toast.LENGTH_SHORT).show()
        }
    }

    private fun abortStepCounter() {
        if (isRunning) {
            sensorManager.unregisterListener(this, stepSensor)
            isRunning = false
            stepCount = 0
            stepCountView.text = stepCount.toString()
            Toast.makeText(requireContext(), "Step counter aborted", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveStepCount() {
        val sharedPreferences = requireActivity().getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("step_count", stepCount)
        editor.apply()
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (isRunning && event?.sensor?.type == Sensor.TYPE_STEP_COUNTER) {
            if (initialStepCount == -1) {
                initialStepCount = event.values[0].toInt()
            }
            stepCount = event.values[0].toInt() - initialStepCount
            stepCountView.text = stepCount.toString()
            Log.d("StepCounterFragment", "Steps: $stepCount")
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onPause() {
        super.onPause()
        if (isRunning) {
            sensorManager.unregisterListener(this, stepSensor)
        }
    }

    override fun onResume() {
        super.onResume()
        if (isRunning) {
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI)
        } else {
            loadStepCount()
        }
    }

    private fun loadStepCount() {
        val sharedPreferences = requireActivity().getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)
        stepCount = sharedPreferences.getInt("step_count", 0)
        stepCountView.text = stepCount.toString()
    }
}
