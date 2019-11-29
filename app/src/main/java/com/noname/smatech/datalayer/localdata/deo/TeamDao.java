package com.noname.smatech.datalayer.localdata.deo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.noname.smatech.model.entities.TeamDetails;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface TeamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertTeam(TeamDetails team);

    @Query("select * from Team ")
    public Single<List<TeamDetails>> selectAll();


    @Query("select count(id) from Team")
    public Single<Integer> selectCountAll();

    @Query("delete  from Team")
    public void removeAll();


    @Query("select * from Team where id IN (:ids) ")
    public Single<List<TeamDetails>> retrieveFavs(List<Integer> ids);

    @Query("update Team set isFav =1 where id = :teamid")
    public int addtoFav(int teamid);

    @Query("update Team set isFav =0 where id = :teamid")
    public int removeFromFav(int teamid);

}
