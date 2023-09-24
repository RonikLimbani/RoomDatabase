package com.ronik.roomdatabasewithmvvm.ui.fragment


import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ronik.roomdatabasewithmvvm.R
import com.ronik.roomdatabasewithmvvm.UserViewModel
import com.ronik.roomdatabasewithmvvm.adapter.UserAdapter
import com.ronik.roomdatabasewithmvvm.databinding.FragmentListBinding
import com.ronik.roomdatabasewithmvvm.utils.BaseFragment
import com.ronik.roomdatabasewithmvvm.utils.ClickStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding>() {

    private val userViewModel: UserViewModel by viewModels()
    lateinit var userAdapter: UserAdapter


    override val layoutResId: Int
        get() = R.layout.fragment_list

    override fun clickEvent() {

        userAdapter.setOnItemClickListener { user, clickStatus ->

            val bundle = Bundle().apply {
                    putParcelable("user", user)
                    putString("title","Update User")
                }
            when(clickStatus){
                ClickStatus.EDIT->{
                    findNavController().navigate(
                        R.id.action_listFragment_to_add_Edit_Fragment,
                        bundle
                    )
                }
                ClickStatus.DELETE->{
                    lifecycleScope.launch {
                        userViewModel.deleteUser(user)
//                        userViewModel.deleteUserWithId(user.id)
                    }


                }
                else->{

                }
            }



        }


    }

    override fun initView() {
        setMenu()

        userAdapter = UserAdapter(requireContext())
        binding.recyUserlist.layoutManager = LinearLayoutManager(context)
        binding.recyUserlist.adapter = userAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            userViewModel.getAllUsersFlow().collect { users ->
                  if (users.isNotEmpty()){
                      binding.recyUserlist.visibility= View.VISIBLE
                      binding.noDataTextView.visibility= View.GONE
                      userAdapter.submitList(users)

                  } else{
                      binding.recyUserlist.visibility= View.GONE
                      binding.noDataTextView.visibility= View.VISIBLE
                  }
                // Update your UI with the list of users
            }
        }

    }

    private fun setMenu() {
        // Get a reference to the activity as a MenuHost
        val menuHost: MenuHost = requireActivity()
        // Add menu items without using the Fragment Menu APIs
        // Note how we can tie the MenuProvider to the viewLifecycleOwner
        // and an optional Lifecycle.State (here, RESUMED) to indicate when
        // the menu should be visible
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Inflate the menu resource
                menuInflater.inflate(R.menu.main_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu item selection here
                when (menuItem.itemId) {
                    R.id.item_add_edit -> {
                        val action = ListFragmentDirections.actionListFragmentToAddEditFragment()
                        findNavController().navigate(action)

                        // Handle the specific menu item's action
                        // Return true to indicate that the item is handled
                        // You can replace this with your own logic
                        // ...
                        return true
                    }
                    // Add more cases for other menu items if needed
                    else -> return false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }


}