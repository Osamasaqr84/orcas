package com.noname.smatech.model.entities

import java.io.Serializable

data class Teams(
    val competition: Competition,
    val count: Int,
    val filters: Filters,
    val season: Season,
    val teams: List<TeamDetails>
)

data class Competition(
    val area: Area,
    val code: String,
    val id: Int,
    val lastUpdated: String,
    val name: String,
    val plan: String
)

data class Area(
    val id: Int,
    val name: String
)

class Filters(
)

data class Season(
    val currentMatchday: Int,
    val endDate: String,
    val id: Int,
    val startDate: String,
    val winner: Any
)


data class AreaX(
    val id: Int,
    val name: String
)


//data class TeamDetails(
//    val address: String,
//    val area: AreaX,
//    val clubColors: String,
//    val crestUrl: String,
//    val email: String,
//    val founded: Int,
//    val id: Int,
//    val lastUpdated: String,
//    val name: String,
//    val phone: String,
//    val shortName: String,
//    val squad: List<Squad>,
//    val tla: String,
//    val venue: String,
//    val website: String
//):Serializable



//data class Squad(
//    val countryOfBirth: String,
//    val dateOfBirth: String,
//    val id: Int,
//    val name: String,
//    val nationality: String,
//    val position: String,
//    val role: String,
//    val shirtNumber: Int
//)