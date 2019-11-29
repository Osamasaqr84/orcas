package com.noname.smatech.presentation.screens.teamdetails

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noname.smatech.R
import com.noname.smatech.model.entities.TeamDetails
import kotlinx.android.synthetic.main.team_details_fragment.view.*

class TeamDetailsFragment : Fragment() {

//    val viewModel: TeamsViewModel by lazy {
//        ViewModelProviders.of(this,getViewModelFactory()).get(TeamsViewModel::class.java)
//    }

    lateinit var team :TeamDetails
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {

        val view= inflater.inflate(R.layout.team_details_fragment, container, false)

        team = arguments?.getSerializable("team") as TeamDetails
        //viewModel.getDetails(team)
        view.clubcolor.text = team.clubColors
        view.name.text = team.name
        view.team_website.text = team.website
        view.clubphone.text = team.phone
        view.clubmail.text = team.email
        view.clubvenu.text = team.venue
        view.players.adapter = PlayerAdapter(activity!!,team.squad!!)

//        viewModel.teamDetails.observe(this, Observer {
//            view.clubcolor.text = it?.clubColors
//            view.name.text = it?.name
//            view.team_website.text = it?.website
//            view.clubphone.text = it?.phone
//            view.clubmail.text = it?.email
//            view.clubvenu.text = it?.venue
//            view.squad.adapter = PlayerAdapter(activity!!,it?.squad!!)
//        })
//
//        viewModel.errorLivedat.observe(this, Observer {
//                showToastBasedOnThrowable(context,it)
//        })
//
//        viewModel.loadingLivedat.observe(this,
//            Observer { loading ->
//                view.progress.setVisibility(if (loading!!) View.VISIBLE else View.GONE) })

        return view
    }

//    fun getViewModelFactory(): ViewModelFactory {
//        return ViewModelFactory(  activity?.application  )
//    }

}
