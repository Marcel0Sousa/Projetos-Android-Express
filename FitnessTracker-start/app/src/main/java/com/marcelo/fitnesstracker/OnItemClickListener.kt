package com.marcelo.fitnesstracker

import android.content.DialogInterface.OnClickListener
import com.marcelo.fitnesstracker.model.Calc

interface OnItemClickListener {
    fun onClick(id: Int)

    fun onLongClick(position: Int, calc: Calc)
}