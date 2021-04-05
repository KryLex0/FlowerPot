package com.example.flowerpot

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="Donnees")
data class Donnee(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    val temperature: Double,
    val humidite: Double,
    val luminosite: Int
){
    constructor(temperature: Double, humidite: Double, luminosite: Int) : this(0, temperature, humidite, luminosite)
}