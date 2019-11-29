package com.noname.smatech.presentation.screens.favorities

import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noname.smatech.R
import com.noname.smatech.model.entities.TeamDetails
import kotlinx.android.synthetic.main.team_item.view.*

class FavTeamAdapter(
    activity: FragmentActivity,
    repos: ArrayList<TeamDetails>,
    favTeamsFragment: FavTeamsFragment
) :RecyclerView.Adapter<FavTeamAdapter.CustomView>() {

    private val context: Context=activity
    private val favTeamsFragment1: FavTeamsFragment=favTeamsFragment
    private var teamsData: ArrayList<TeamDetails> = repos

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomView {

        return CustomView(LayoutInflater.from(context).inflate(R.layout.team_item, parent, false))
    }

    override fun onBindViewHolder(holder: CustomView, position: Int) {

        holder.colors.text = teamsData.get(position).clubColors
        holder.website.text = teamsData.get(position).website
        holder.name.text = teamsData.get(position).name
        holder.venue.text = teamsData.get(position).venue

        holder.fav.setImageResource(R.drawable.ic_close)

        holder.fav.setOnClickListener{
            favTeamsFragment1.onItemClickForFav(teamsData.get(position).id,true)
            teamsData.removeAt(position)
            notifyDataSetChanged()
        }

        holder.mView.setOnClickListener{
            favTeamsFragment1.onItemClick(teamsData.get(position))
        }
    }

    override fun getItemCount(): Int {
        return teamsData.size
    }

    class CustomView(val mView: View) : RecyclerView.ViewHolder(mView) {
        val name = mView.name
        val website = mView.team_website
        val venue = mView.clubvenu
        val colors = mView.clubcolor
        val fav = mView.fav
    }

}
