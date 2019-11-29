package com.noname.smatech.model.helper;

import com.noname.smatech.model.entities.TeamDetails;

public interface OnItemClick {

   void onItemClick(TeamDetails team);
   void onItemClickForFav(int teamid,boolean currentFav);
}
