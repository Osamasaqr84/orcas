package com.noname.smatech.presentation.screens.mainactivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.noname.smatech.R
import com.noname.smatech.presentation.screens.teamsfragment.TeamsFragment
import android.R.menu
import android.view.MenuInflater
import android.widget.Switch
import com.noname.smatech.presentation.screens.favorities.FavTeamsFragment


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.fram,TeamsFragment()).addToBackStack("main").commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId==R.id.favorits)
            supportFragmentManager.beginTransaction().replace(R.id.fram,FavTeamsFragment()).addToBackStack(null).commit()

        return true
    }

}
