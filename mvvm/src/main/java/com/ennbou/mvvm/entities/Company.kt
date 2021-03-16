package com.ennbou.mvvm.entities

import androidx.room.*

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
data class Company(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    var name: String,
    @ColumnInfo(name = "catch_phrase") var catchPhrase: String,
    var bs: String,
    var userId: Long
)
