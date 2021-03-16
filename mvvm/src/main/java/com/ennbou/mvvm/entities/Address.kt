package com.ennbou.mvvm.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("userId"),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [
        Index("userId")
    ]
)
data class Address(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,
    var street: String,
    var suite: String,
    var city: String,
    var zipcode: String,
    var userId: Long
)
