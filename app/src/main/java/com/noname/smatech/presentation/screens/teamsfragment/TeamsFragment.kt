package com.noname.smatech.presentation.screens.teamsfragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noname.smatech.R
import com.noname.smatech.model.entities.TeamDetails
import com.noname.smatech.model.helper.OnItemClick
import com.noname.smatech.model.usecases.showToastBasedOnThrowable
import com.noname.smatech.presentation.screens.teamdetails.TeamDetailsFragment
import kotlinx.android.synthetic.main.teams_fragment.*
import kotlinx.android.synthetic.main.teams_fragment.view.*
import java.io.Serializable
import java.util.*

class TeamsFragment : Fragment(), OnItemClick {

    val viewModel: TeamsViewModel by lazy {
        ViewModelProviders.of(this,getViewModelFactory()).get(TeamsViewModel::class.java)
    }

    lateinit var teamsAdapter: TeamAdapter
    internal lateinit var ListteamsDetails: ArrayList<TeamDetails>
    var againdata = true


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.teams_fragment, container, false)

        viewModel.loadTeamsData()
        viewModel.teamsdata.observe(this, Observer {
            viewModel.loadnow = false
            if (viewModel.page == 1) {
                ListteamsDetails = ArrayList(it)
                if (ListteamsDetails.size>0) {
                    teamsAdapter = TeamAdapter(activity!!, ListteamsDetails,this )
                    view.teamslist.adapter = teamsAdapter
                }
                else
                    notfound.visibility=View.VISIBLE

            } else {
                if (it!!.size>0) {
                    ListteamsDetails.addAll(it)
                    teamsAdapter.notifyDataSetChanged()
                 //   view.repositries.scrollToPosition(TeamAdapter.getItemCount() - 14)
                }else
                    againdata = false
            }
        })

        //// handel pagination
        view.teamslist.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItem =
                    (Objects.requireNonNull(recyclerView.layoutManager) as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                if (lastVisibleItem == teamsAdapter.getItemCount() - 1&& dy>0) {
                   // if (viewModel.page) {
                        if (againdata)
                            viewModel.page++;
                        viewModel.getTeams()
                 //   }
                }
            }
        })

        viewModel.errorLivedat.observe(this, Observer {
            if (viewModel.page==1)
            showToastBasedOnThrowable(context,it)
            viewModel.loadnow = false
           // Toast.makeText(context,it.toString(),Toast.LENGTH_LONG).show()
        })

        viewModel.loadingLivedat.observe(this,
            Observer { loading ->
                view.progress.setVisibility(if (loading!!) View.VISIBLE else View.GONE) })

        return view
    }

    // go to details
    override fun onItemClick(team: TeamDetails) {
        val fragment = TeamDetailsFragment()
        val args = Bundle()
        args.putSerializable("team", team as Serializable)
        fragment.setArguments(args)
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fram, fragment)?.addToBackStack(null)?.commit()
    }

    override fun onItemClickForFav(teamid: Int, currentFav: Boolean) {
        if (currentFav)//if is fav remove
            viewModel.removeFromFav(teamid)
        else // else add to fav
            viewModel.addToFav(teamid)

    }

    ///// initialize viewmode with factory
    fun getViewModelFactory(): ViewModelFactory {
        return ViewModelFactory(activity?.application)
    }

}
