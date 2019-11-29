package com.noname.smatech.presentation.screens.favorities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noname.smatech.R
import com.noname.smatech.model.entities.TeamDetails
import com.noname.smatech.model.helper.OnItemClick
import com.noname.smatech.model.usecases.showToastBasedOnThrowable
import com.noname.smatech.presentation.screens.teamdetails.TeamDetailsFragment
import com.noname.smatech.presentation.screens.teamsfragment.TeamsViewModel
import com.noname.smatech.presentation.screens.teamsfragment.ViewModelFactory
import kotlinx.android.synthetic.main.teams_fragment.*
import kotlinx.android.synthetic.main.teams_fragment.view.*
import java.io.Serializable
import java.util.*

class FavTeamsFragment : Fragment(), OnItemClick {

    val viewModel: TeamsViewModel by lazy {
        ViewModelProviders.of(this, getViewModelFactory()).get(TeamsViewModel::class.java)
    }

    lateinit var FavAdapter: FavTeamAdapter
    internal lateinit var reposList: ArrayList<TeamDetails>

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?):View?{

        val view = inflater.inflate(R.layout.teams_fragment, container, false)
        viewModel.getFavData()
        viewModel.favTeamsdata.observe(this, Observer {
            viewModel.loadnow = false
            reposList = ArrayList(it)
            if (reposList.size > 0) {
                FavAdapter = FavTeamAdapter(activity!!, reposList,this)
                view.teamslist.adapter = FavAdapter
            } else
                notfound.visibility = View.VISIBLE
        })

        viewModel.errorLivedat.observe(this, Observer {
            showToastBasedOnThrowable(context, it)
        })

        viewModel.loadingLivedat.observe(this,
            Observer { loading ->
                view.progress.setVisibility(if (loading!!) View.VISIBLE else View.GONE)
            })

        return view
    }

    fun getViewModelFactory(): ViewModelFactory {
        return ViewModelFactory(activity?.application)
    }

    override fun onItemClickForFav(teamid: Int, currentFav: Boolean) {
        viewModel.removeFromFav(teamid)
    }

    override fun onItemClick(team: TeamDetails?) {
        val fragment = TeamDetailsFragment()
        val args = Bundle()
        args.putSerializable("team", team as Serializable)
        fragment.setArguments(args)
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fram, fragment)?.addToBackStack(null)?.commit()
    }

}
