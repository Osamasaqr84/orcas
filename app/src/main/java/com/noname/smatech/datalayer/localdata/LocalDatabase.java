package com.noname.smatech.datalayer.localdata;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.noname.smatech.datalayer.localdata.deo.FavTeamsDao;
import com.noname.smatech.datalayer.localdata.deo.TeamDao;
import com.noname.smatech.model.entities.FavTeamsIds;
import com.noname.smatech.model.entities.TeamDetails;

@Database(entities = {TeamDetails.class, FavTeamsIds.class}
        ,version = 4,exportSchema = false)
public abstract class LocalDatabase extends RoomDatabase {

    public abstract TeamDao teamsDeo();
    public abstract FavTeamsDao favTeamsDeo();

    public static LocalDatabase instance;

    public static LocalDatabase getInstance (Context context)
    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),LocalDatabase.class,
                    "teams_database").addCallback(roomcallback)
            .fallbackToDestructiveMigration().build();
        }

        return instance;
    }

    public static RoomDatabase.Callback roomcallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };


}
