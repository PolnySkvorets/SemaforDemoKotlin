package com.denisskvorcov.semafordemo

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import java.util.*

class MainActivity : Activity() {
    var semafor: ImageView? = null
    var counter: Int = 0
    var timer: Timer? = null
    var isRun: Boolean = false
    var direct: Boolean = true
    var imageArray: IntArray = intArrayOf(
        R.drawable.semafor_red,
        R.drawable.semafor_yellow,
        R.drawable.semafor_green
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        semafor = findViewById(R.id.semafor)
    }

    fun onClickStartStop(view: View) {
        view as ImageButton
        if (!isRun) {
            startStop()
            view.setImageResource(R.drawable.button_stop)
            isRun = true
        } else {
            semafor?.setImageResource(R.drawable.semafor_grey)
            view.setImageResource(R.drawable.button_start)
            timer?.cancel()
            isRun = false
            counter = 0
            direct = true
        }
    }

    fun startStop() {
        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    semafor?.setImageResource(imageArray[counter])
                    if (counter == 2) {
                        direct = false
                    }
                    if (counter == 0) {
                        direct = true
                    }
                    if (direct) {
                        counter++
                    } else {
                        counter--
                    }
                }
            }
        }, 0, 1000)
    }
}