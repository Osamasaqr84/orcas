package com.noname.smatech.model.entities

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

public class SquadTypeConverters {
    @TypeConverter
    fun stringToSquads(json: String): List<Squad> {
        val gson = Gson()
        val type = object : TypeToken<List<Squad>>() {

        }.type
        return gson.fromJson<List<Squad>>(json, type)
    }

    @TypeConverter
    fun SquadsToString(list: List<Squad>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Squad>>() {

        }.type
        return gson.toJson(list, type)
    }
}