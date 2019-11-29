package com.noname.smatech.presentation.screens.teamsfragment

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.noname.smatech.datalayer.apidata.ServerGateway
import com.noname.smatech.datalayer.localdata.deo.FavTeamsDao
import com.noname.smatech.datalayer.localdata.deo.TeamDao
import com.noname.smatech.model.entities.TeamDetails
import com.noname.smatech.model.usecases.*

class TeamsViewModel(apiService: ServerGateway, val teamsDeo1: TeamDao
                     ,favTeamsDao1: FavTeamsDao, val con: Context) : ViewModel() {
    val teamsdata: MutableLiveData<ArrayList<TeamDetails>> = MutableLiveData()
    val favTeamsdata: MutableLiveData<ArrayList<TeamDetails>> = MutableLiveData()
    val errorLivedat: MutableLiveData<Throwable> = MutableLiveData()
    val loadingLivedat: MutableLiveData<Boolean> = MutableLiveData()
    val server:ServerGateway =apiService
    val repoDeo: TeamDao =teamsDeo1
    val favTeamsDao: FavTeamsDao =favTeamsDao1
    var page = 1
    var loadnow = false

    fun loadTeamsData() {
        if (con.isConnected&&!loadnow)
        getTeams()
        else
            getLocalData()
    }

    fun getTeams()
    {
        loadingLivedat.postValue(true)
        loadnow = true
        retrievTeamsData(page,server,repoDeo,teamsdata,errorLivedat,loadingLivedat)
    }

    fun getLocalData()
    {
        loadingLivedat.postValue(true)
        retrieveLocal(repoDeo,teamsdata,errorLivedat,loadingLivedat)
    }

    fun getFavData()
    {
        loadingLivedat.postValue(true)
        retrieveFavs(repoDeo,favTeamsDao,favTeamsdata,errorLivedat,loadingLivedat)
    }


    fun addToFav(teamid: Int)
    {
        addTeamToFav(favTeamsDao,teamid)
    }

    fun removeFromFav(teamid: Int)
    {
        removeTeamFromFav(favTeamsDao,teamid)
    }
}
