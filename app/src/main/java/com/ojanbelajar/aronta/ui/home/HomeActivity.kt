package com.ojanbelajar.aronta.ui.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Html
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ojanbelajar.aronta.R
import com.ojanbelajar.aronta.databinding.ActivityHomeBinding
import com.ojanbelajar.aronta.ui.profile.ProfileFragment
import com.ojanbelajar.aronta.ui.search.SearchFragment
import com.ojanbelajar.aronta.utils.SessionManagement
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.textColor
import org.jetbrains.anko.toast
import android.widget.Toast

import android.content.Intent
import android.os.Handler


@AndroidEntryPoint
class HomeActivity: AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    private val homeFragment: Fragment = HomeFragment()
    private val searchFragment: Fragment = SearchFragment()
    private val profileFragment: Fragment = ProfileFragment()
    lateinit var session: SessionManagement
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        session = SessionManagement(applicationContext)

        commitFragment(homeFragment)
        val navigation = BottomNavigationView.OnNavigationItemSelectedListener {
            clearFragmentStack()
            when(it.itemId){
                R.id.navigation_home ->  {
                    commitFragment(homeFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_search -> {
                    commitFragment(searchFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    commitFragment(profileFragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
        bottomNavigation.setOnNavigationItemSelectedListener(navigation)

    }
    private fun commitFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_container, fragment)
            .commit()
    }

    private fun clearFragmentStack(){
        val fm = this.supportFragmentManager
        for ( i in 0..fm.backStackEntryCount){
            fm.popBackStack()
        }
    }

    fun logout() {
        session.logoutUser()
        finish()
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }
}