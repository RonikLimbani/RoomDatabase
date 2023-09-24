package com.ronik.roomdatabasewithmvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import com.ronik.roomdatabasewithmvvm.UserViewModel
import com.ronik.roomdatabasewithmvvm.R
import com.ronik.roomdatabasewithmvvm.databinding.ActivityMainBinding
import com.ronik.roomdatabasewithmvvm.ui.fragment.Add_Edit_Fragment
import com.ronik.roomdatabasewithmvvm.ui.fragment.ListFragment
import com.ronik.roomdatabasewithmvvm.ui.fragment.ListFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: UserViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    // Declare a lazy property for the Data Binding
//    private val binding: ActivityMainBinding by lazy {
//        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
//    }
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding.root.visibility=View.VISIBLE
//        setContentView(R.layout.activity_main)
        binding=DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        setSupportActionBar(binding.toolbar)
//        binding.toolbar.setupWithNavController(navController,
//            configuration = AppBarConfiguration(
//                setOf(R.id.listFragment,R.id.item_add_edit)
//            )
//        )
        setupActionBarWithNavController(navController,
            configuration = AppBarConfiguration(
                setOf(R.id.listFragment,R.id.item_add_edit)
            ))





    }

//    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
//        val currentFragment = navHostFragment.childFragmentManager.fragments.firstOrNull()
//        menu?.findItem(R.id.item_add_edit)?.isVisible = currentFragment is ListFragment
//        return super.onPrepareOptionsMenu(menu)
//    }


    override fun onSupportNavigateUp(): Boolean {
        return  navController.navigateUp() ||  super.onSupportNavigateUp()
    }

//    override fun onSupportNavigateUp(): Boolean {
//        return NavigationUI.navigateUp( navController, null )
//    }


//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
////        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
//
//        return when(item.itemId){
//            R.id.item_add_edit ->{
//                val action = ListFragmentDirections.actionListFragmentToAddEditFragment()
//               navController.navigate(action)
//
////                navController.navigate(R.id.action_listFragment_to_add_Edit_Fragment)
//
////               findNavController(R.id.fragment_container).navigate(R.id.action_listFragment_to_add_Edit_Fragment)
//
//
//                return true
//            }
//            else -> item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
//        }
//    }



//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.main_menu,menu)
//        return super.onCreateOptionsMenu(menu)
//    }


//    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
//        val currentFragment = navHostFragment.childFragmentManager.fragments.firstOrNull()
//
////        val currentFragment = navHostFragment.childFragmentManager.fragments.firstOrNull()
////        menu?.findItem(R.id.item_add_edit)?.isVisible = currentFragment is ListFragment
//        when (currentFragment) {
//            is ListFragment -> {
//                menuInflater.inflate(R.menu.main_menu, menu)
//            }
////            is Add_Edit_Fragment -> {
//////                menuInflater.inflate(R.menu.main_menu_addedit, menu)
////            }
//            else -> {
//                menu?.clear() // Clear the menu if it's not handled by a specific fragment
//            }
//        }
//        return super.onPrepareOptionsMenu(menu)
//    }


}