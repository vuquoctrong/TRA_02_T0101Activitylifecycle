package com.rikkei.tra_02_t0101activitylifecycle

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_a.*

class ActivityA : AppCompatActivity(), View.OnClickListener {


    private val TAG = ActivityA::class.java.toString()
    private var mediaPlayer: MediaPlayer? = null
    private var currentPosition: Int = 0
    private val keyPosition = "KEY_POSITION"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate A")
        setContentView(R.layout.activity_a)
        btnOpenB.setOnClickListener(this)
        mediaPlayer = MediaPlayer.create(this, R.raw.haychaochoanh)

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart A")
        mediaPlay()
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, " onResume A")
    }

    override fun onPause() {
        super.onPause()

        Log.d(TAG, "onPause A")

    }

    override fun onStop() {
        super.onStop()
        mediaStop()
        Log.d(TAG, "onStop A")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy A")
        mediaPlayer?.apply {
            stop()
            release()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSavaInstanceState A")
        outState?.putInt(keyPosition, currentPosition)
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "onRestoreInstanceState A")
        currentPosition = savedInstanceState?.getInt(keyPosition)!!
        if (currentPosition != 0) {
            mediaPlayer?.seekTo(currentPosition)
        }

    }

    override fun onClick(v: View?) {
        val intent = Intent(this, ActivityB::class.java)
        startActivity(intent)
    }

    private fun mediaPlay() {
        mediaPlayer?.apply {
            start()
        }
    }

    private fun mediaStop() {
        mediaPlayer?.apply {
            this@ActivityA.currentPosition = currentPosition
            pause()
        }
    }

}
