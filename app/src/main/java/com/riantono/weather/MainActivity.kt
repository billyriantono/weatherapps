package com.riantono.weather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.navigation.Navigation

class MainActivity : AppCompatActivity() {

    lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mToolbar = findViewById(R.id.header)
        setSupportActionBar(mToolbar)

        supportActionBar?.title = "Today in Jakarta"

        var mButtonAddCity: Button = findViewById(R.id.btn_add_search_city)
        mButtonAddCity.setOnClickListener { Navigation.createNavigateOnClickListener(R.id.goToSettingAction) }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu_app, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_settings -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }
}
