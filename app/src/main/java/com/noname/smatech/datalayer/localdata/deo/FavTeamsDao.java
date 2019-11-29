package com.noname.smatech.datalayer.localdata.deo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.noname.smatech.model.entities.FavTeamsIds;
import com.noname.smatech.model.entities.TeamDetails;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface FavTeamsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertTeam(FavTeamsIds favTeamsIds);

    @Query("select teamId from FavTeamsIds ")
    public Single<List<Integer>> selectAll();


    @Query("delete from FavTeamsIds where teamId =:id")
    public void deleteFav(int id);

}
