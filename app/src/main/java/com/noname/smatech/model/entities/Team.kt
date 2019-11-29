package com.noname.smatech.model.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import java.io.Serializable


@Entity(tableName = "Team")
@TypeConverters(SquadTypeConverters::class)
class TeamDetails  : Serializable{

    @PrimaryKey
    var id: Int = 0

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "email")
    var email: String? = null

    @ColumnInfo(name = "phone")
    var phone: String? = null


    @ColumnInfo(name = "website")
    var website: String? = null

    @ColumnInfo(name = "clubColors")
    var clubColors: String? = null

    @ColumnInfo(name = "venue")
    var venue: String? = null

    @ColumnInfo(name = "isFav")
    var fav: Boolean? = false


    @ColumnInfo(name = "squad")
    var squad: List<Squad>? = null
}
