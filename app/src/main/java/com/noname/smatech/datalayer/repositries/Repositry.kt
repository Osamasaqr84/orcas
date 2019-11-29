package com.noname.smatech.datalayer.repositries

import android.annotation.SuppressLint
import com.noname.smatech.datalayer.apidata.ServerGateway
import com.noname.smatech.datalayer.localdata.deo.FavTeamsDao
import com.noname.smatech.datalayer.localdata.deo.SquadDao
import com.noname.smatech.datalayer.localdata.deo.TeamDao
import com.noname.smatech.model.entities.FavTeamsIds
import com.noname.smatech.model.entities.Squad
import com.noname.smatech.model.entities.TeamDetails
import com.noname.smatech.model.entities.Teams
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


fun retrieveteams(server: ServerGateway, page: Int): Observable<Teams> {
    return server.retrieveTeams2(2019).map { t -> t }
}

fun retrieveTemDetails(server: ServerGateway,teamid: Int): Observable<TeamDetails> {
    return server.retrieveTemDetails(teamid)
}


fun retrieveLocalData(teamdeo : TeamDao): Single<List<TeamDetails>>?
{
    return teamdeo.selectAll()
}

fun retrieveFavTeamsIds(favTeamsDao: FavTeamsDao): Single<List<Int>>?
{
    return  favTeamsDao.selectAll()
}

fun retrieveFavTeams(teamdeo : TeamDao,favIds: List<Int>): Single<List<TeamDetails>>?
{
    return teamdeo.retrieveFavs(favIds)
}

fun addToFav(teamdeo : FavTeamsDao, teamid :Int)
{
    Observable.fromCallable { teamdeo.insertTeam(FavTeamsIds(teamid)) }.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe()
}


fun removeFromFav(teamdeo : FavTeamsDao, teamid :Int)
{
    Observable.fromCallable { teamdeo.deleteFav(teamid) }.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe()
}

@SuppressLint("CheckResult")
fun AddTeam(teamdeo: TeamDao, team: TeamDetails) {
    Observable.fromCallable { teamdeo.insertTeam(team) }.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe()
}


