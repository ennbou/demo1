package com.ennbou.mvvm.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Address::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("addressId"),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [
        Index("addressId")
    ]
)
data class Geo(@PrimaryKey(autoGenerate = true) var id: Long?, var lat: Float, var lng: Float, var addressId: Long)
