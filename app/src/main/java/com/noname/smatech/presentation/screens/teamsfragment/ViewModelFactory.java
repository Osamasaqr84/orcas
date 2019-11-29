package com.noname.smatech.presentation.screens.teamsfragment;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import com.noname.smatech.datalayer.apidata.ApiClient;
import com.noname.smatech.datalayer.apidata.ServerGateway;
import com.noname.smatech.datalayer.localdata.LocalDatabase;
import com.noname.smatech.datalayer.localdata.deo.FavTeamsDao;
import com.noname.smatech.datalayer.localdata.deo.TeamDao;

public class ViewModelFactory implements ViewModelProvider.Factory {


    private Application application;

    public ViewModelFactory(Application application1) {
        application = application1;
    }

    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
         if (modelClass == TeamsViewModel.class)
        {
            return (T) new TeamsViewModel(getApiService(), getTeamsDao(),getFavDao(),application.getBaseContext());
        }

        throw new IllegalArgumentException("Invalid view model class type");
    }

    private TeamDao getTeamsDao() {
        return LocalDatabase.getInstance(application).teamsDeo();
    }
    private FavTeamsDao getFavDao() {
        return LocalDatabase.getInstance(application).favTeamsDeo();
    }

    private ServerGateway getApiService() {
        return ApiClient.getClient().create(ServerGateway.class);

    }

}
