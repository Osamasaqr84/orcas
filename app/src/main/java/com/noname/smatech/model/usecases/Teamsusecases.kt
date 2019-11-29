package com.noname.smatech.model.usecases

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.noname.smatech.datalayer.apidata.ServerGateway
import com.noname.smatech.datalayer.localdata.deo.FavTeamsDao
import com.noname.smatech.datalayer.localdata.deo.TeamDao
import com.noname.smatech.datalayer.repositries.*
import com.noname.smatech.model.entities.TeamDetails
import com.noname.smatech.model.helper.PreferenceHelper.checkData
import com.noname.smatech.model.helper.PreferenceHelper.setcheckData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers



@SuppressLint("CheckResult")
fun retrievTeamsData(
    page: Int,
    serverGateway: ServerGateway,
    repoDeo: TeamDao,
    teamsdata: MutableLiveData<ArrayList<TeamDetails>>,
    errorLivedat: MutableLiveData<Throwable>,
    loadingLivedat: MutableLiveData<Boolean>
) {
    retrieveteams(serverGateway, page).flatMapIterable{t ->
        t.teams.subList((page-1)*6,page*6)}.flatMap{ t ->
        retrieveTemDetails(serverGateway, t.id)

    }.toList().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            {
                Log.d("success1", it.toString())
                storAllInDB(it as ArrayList<TeamDetails>, repoDeo)
                teamsdata.postValue(it as ArrayList<TeamDetails>);
                loadingLivedat.postValue(false)
            },
            {
                Log.d("faild1", it.message)
                errorLivedat.postValue(it);
                loadingLivedat.postValue(false)
            }
        )
}


@SuppressLint("CheckResult")
fun retrieveLocal(
    repoDeo: TeamDao,
    repositriesliveData: MutableLiveData<ArrayList<TeamDetails>>,
    errorLiveData: MutableLiveData<Throwable>,
    loadingLivedata: MutableLiveData<Boolean>) {
    retrieveLocalData(repoDeo)?.subscribeOn(Schedulers.io())
        ?.observeOn(AndroidSchedulers.mainThread())?.subscribe({ myData ->
            repositriesliveData.postValue(myData as ArrayList<TeamDetails>);
            loadingLivedata.postValue(false)// handle data fetched successfully and API call completed
        }, { throwable ->
            errorLiveData.postValue(throwable);
            loadingLivedata.postValue(false)
        });
}


@SuppressLint("CheckResult")
fun retrieveFavs(
    teamsDeo: TeamDao,
    favTeamsDao: FavTeamsDao,
    repositriesliveData: MutableLiveData<ArrayList<TeamDetails>>,
    errorLiveData: MutableLiveData<Throwable>,
    loadingLivedata: MutableLiveData<Boolean>
) {
    retrieveFavTeamsIds(favTeamsDao)?.flatMap { t ->
        retrieveFavTeams(teamsDeo,t)
    }?.subscribeOn(Schedulers.io())
        ?.observeOn(AndroidSchedulers.mainThread())?.subscribe({ myData ->
            repositriesliveData.postValue(myData as ArrayList<TeamDetails>);
            loadingLivedata.postValue(false)// handle data fetched successfully and API call completed
        }, { throwable ->
            errorLiveData.postValue(throwable);
            loadingLivedata.postValue(false)
        });
}


fun addTeamToFav(favTeamsDao: FavTeamsDao, teamid: Int) {
    addToFav(favTeamsDao, teamid)
}

fun removeTeamFromFav(favTeamsDao: FavTeamsDao, teamid: Int) {
    removeFromFav(favTeamsDao, teamid)
}


fun storAllInDB(alldata: ArrayList<TeamDetails>, deo: TeamDao) {
    alldata.map { team -> AddTeam( deo,team )}
}
