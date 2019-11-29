package com.noname.smatech.model.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

import android.arch.persistence.room.ForeignKey.CASCADE


@Entity(
    foreignKeys = [ForeignKey(
        entity = TeamDetails::class,
        parentColumns = ["id"],
        childColumns = ["teamId"],
        onDelete = CASCADE
    )]
)
class Squad {

    @PrimaryKey
    var squadId: Int = 0
    @ColumnInfo(name = "name")
    var name: String? = null
    @ColumnInfo(name = "teamId")
    var teamId: Int = 0
    @ColumnInfo(name = "position")
    var position: String? = null
    @ColumnInfo(name = "dateOfBirth")
    var dateOfBirth: String? = null
    @ColumnInfo(name = "countryOfBirth")
    var countryOfBirth: String? = null
    @ColumnInfo(name = "nationality")
    var nationality: String? = null
    @ColumnInfo(name = "shirtNumber")
    var shirtNumber: Int = 0
    @ColumnInfo(name = "role")
    var role: String? = null
}
