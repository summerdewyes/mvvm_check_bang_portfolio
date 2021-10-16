package com.summerdewyes.mvvm_check_bang.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.summerdewyes.mvvm_check_bang.R
import com.summerdewyes.mvvm_check_bang.databinding.ActivityHomeBinding
import com.summerdewyes.mvvm_check_bang.db.BookDao
import com.summerdewyes.mvvm_check_bang.db.FeedDao
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var bookDAO: BookDao

    @Inject
    lateinit var feedDao: FeedDao

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        Log.d("dao", "FEEDDAO : ${feedDao.hashCode()}")

        val navHostFragment= supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController= navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)


        navHostFragment.findNavController()
            .addOnDestinationChangedListener { controller, destination, arguments ->
                when(destination.id){
                    R.id.mainFeedFragment, R.id.bookMarkFragment, R.id.searchFragment, R.id.profileFragment ->
                        binding.bottomNavigationView.visibility = View.VISIBLE
                    else -> binding.bottomNavigationView.visibility = View.GONE
                }
            }


    }
}