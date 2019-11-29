package com.noname.smatech.datalayer.apidata;

import com.noname.smatech.model.entities.TeamDetails;
import com.noname.smatech.model.entities.Teams;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.*;

public interface ServerGateway {

    @GET("competitions/PL/teams")
    Observable<Teams> retrieveTeams2(@Query("season") int page);


    @GET("competitions/PL/teams")
    Observable<Teams> retrieveTeams(@Query("season") int page);

    @GET("teams/{teamid}")
    Observable<TeamDetails> retrieveTemDetails(@Path("teamid") int teamid);

}
