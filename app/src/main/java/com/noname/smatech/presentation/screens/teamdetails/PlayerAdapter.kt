package com.noname.smatech.presentation.screens.teamdetails

import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noname.smatech.R
import com.noname.smatech.model.entities.Squad
import kotlinx.android.synthetic.main.player_item.view.*

class PlayerAdapter(
    activity: FragmentActivity,
    players: List<Squad>
) :
    RecyclerView.Adapter<PlayerAdapter.CustomView>() {

    private val context: Context = activity.baseContext
    private val playersData: List<Squad> = players
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomView {

        return CustomView(LayoutInflater.from(context).inflate(R.layout.player_item, parent, false))
    }

    override fun onBindViewHolder(holder: CustomView, position: Int) {

        holder.name.text = playersData.get(position).name
        holder.nationality.text = playersData.get(position).nationality
        holder.birthdat.text = playersData.get(position).dateOfBirth?.subSequence(0, 10)
        holder.position.text = playersData.get(position).position


    }

    override fun getItemCount(): Int {
        return playersData.size
    }

    inner class CustomView(val mView: View) : RecyclerView.ViewHolder(mView) {

        val itemVieww: View = mView
        val name = mView.name
        val birthdat = mView.player_birthdaye
        val position = mView.player_position
        val nationality = mView.player_Nationality
    }

}
