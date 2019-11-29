package com.noname.smatech.presentation.screens.teamsfragment

import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noname.smatech.R
import com.noname.smatech.model.entities.TeamDetails
import kotlinx.android.synthetic.main.team_item.view.*

class TeamAdapter(
    activity: FragmentActivity,
    teams: ArrayList<TeamDetails>,
    teamsFragment1: TeamsFragment
) : RecyclerView.Adapter<TeamAdapter.CustomView>() {

    private val context: Context = activity
    private var teamsData: ArrayList<TeamDetails> = teams
    private val teamsFragment: TeamsFragment = teamsFragment1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomView {

        return CustomView(LayoutInflater.from(context).inflate(R.layout.team_item, parent, false))
    }

    override fun onBindViewHolder(holder: CustomView, position: Int) {

        holder.colors.text = teamsData.get(position).clubColors
        holder.website.text = teamsData.get(position).website
        holder.name.text = teamsData.get(position).name
        holder.venue.text = teamsData.get(position).venue
        var listString = ""
        teamsData.get(position).squad?.map {
            squad ->  listString += squad.name + "\t\t"+","+ "\t\t"
        }

       holder.plaersnames.text = listString
        if (teamsData.get(position).fav!!)
            holder.fav.setImageResource(R.drawable.ic_fav)

        holder.mView.setOnClickListener {
            teamsFragment.onItemClick(teamsData.get(position))
        }

        holder.fav.setOnClickListener {

            if (teamsData.get(position).fav!!)
                holder.fav.setImageResource(R.drawable.ic_unfav)
            else
                holder.fav.setImageResource(R.drawable.ic_fav)
            teamsFragment.onItemClickForFav(
                teamsData.get(position).id,
                teamsData.get(position).fav!!
            )
        }
    }

    override fun getItemCount(): Int {
        return teamsData.size
    }

    class CustomView(val mView: View) : RecyclerView.ViewHolder(mView) {
        val name = mView.name
        val website = mView.team_website
        val venue = mView.clubvenu
        val plaersnames = mView.team_names
        val colors = mView.clubcolor
        val fav = mView.fav
    }

}
