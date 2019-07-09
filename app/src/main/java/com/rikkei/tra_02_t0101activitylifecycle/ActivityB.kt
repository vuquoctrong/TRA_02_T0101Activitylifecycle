package com.rikkei.tra_02_t0101activitylifecycle

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_b.*

class ActivityB : AppCompatActivity(), View.OnClickListener {

    private val TAG = ActivityB::class.java.toString()
    private var mediaPlayer: MediaPlayer? = null
    private var currentPosition = 0
    private val keyPosition = "KEY_POSITION"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate B")
        setContentView(R.layout.activity_b)
        mediaPlayer = MediaPlayer.create(this, R.raw.belong)
        btnOpenA.setOnClickListener(this)

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart B")
        mediaPlay()

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, " onResume B")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause B")
        mediaStop()
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop B")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy B")
        mediaPlayer?.apply {
            stop()
        }
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSavaInstanceState B")
        outState?.putInt(keyPosition, currentPosition)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "onRestoreInstanceState B")
        currentPosition = savedInstanceState?.getInt(keyPosition)!!
        if (currentPosition != 0) {
            mediaPlayer?.seekTo(currentPosition)
        }
    }

    override fun onClick(v: View?) {
        val intent = Intent(this, ActivityA::class.java)
        startActivity(intent)
    }

    private fun mediaPlay() {
        mediaPlayer?.apply {
            seekTo(currentPosition)
            start()
        }
    }

    private fun mediaStop() {
        mediaPlayer?.apply {
            this@ActivityB.currentPosition = currentPosition
            pause()
        }
    }

}