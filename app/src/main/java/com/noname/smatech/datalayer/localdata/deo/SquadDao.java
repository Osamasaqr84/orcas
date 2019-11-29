package com.noname.smatech.datalayer.localdata.deo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.noname.smatech.model.entities.Squad;
import com.noname.smatech.model.entities.TeamDetails;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface SquadDao {

    @Insert
    public void insertTeam(Squad squad);


    @Query("select * from Squad where teamId = :teamid")
    public Single<List<Squad>> selectAllSquadByTeam(int teamid);


    @Query("select count(squadId) from Squad")
    public Single<Integer> selectCountAllSquad();

    @Query("delete  from Squad")
    public void removeAll();

    @Update()
    public int updateTeam(Squad squad);

    @Query("select * from Team where isFav = 1 ")
    public Single<List<TeamDetails>> retrieveFavs();

    @Query("update Team set isFav =1 where id = :teamid")
    public int addtoFav(int teamid);

    @Query("update Team set isFav =0 where id = :teamid")
    public int removeFromFav(int teamid);

}
