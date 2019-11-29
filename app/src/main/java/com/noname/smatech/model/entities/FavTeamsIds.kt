package com.noname.smatech.model.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import java.io.Serializable


@Entity(tableName = "FavTeamsIds")
@TypeConverters(SquadTypeConverters::class)
class FavTeamsIds(@ColumnInfo(name = "teamId") var teamId: Int?) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}
