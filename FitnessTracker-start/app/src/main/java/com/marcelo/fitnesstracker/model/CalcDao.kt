package com.marcelo.fitnesstracker.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update

@Dao
interface CalcDao {

    @Insert
    fun insert(calc: Calc)

}