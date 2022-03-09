package com.rootdown.dev.adidevibm.view.feature_random_user

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.airbnb.epoxy.EpoxyRecyclerView
import com.rootdown.dev.adidevibm.R
import com.rootdown.dev.adidevibm.databinding.FragmentUsersOverviewBinding
import com.rootdown.dev.adidevibm.model.feature_random_user.db.Users
import com.rootdown.dev.adidevibm.users
import com.rootdown.dev.adidevibm.viewmodel.feature_random_user.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UsersOverviewFragment : Fragment() {
    private lateinit var binding: FragmentUsersOverviewBinding
    private lateinit var state: String

    private val vm: UserViewModel by viewModels()

    private var userJob: Job? = null
    private var ls: List<Users> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersOverviewBinding.inflate(inflater)
        val epoxyView: EpoxyRecyclerView = binding.rvTask
        vm.users.observe(viewLifecycleOwner, Observer { users ->
            users?.let{
                setupRecyclerView(it, epoxyView)
            }
        })
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu_1, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.add_random_user -> {
            //Toast.makeText(this.activity, ls.toString(), Toast.LENGTH_LONG).show()
            getUser()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
    private fun setupRecyclerView(x: List<Users>, epoxy: EpoxyRecyclerView) {
        epoxy.withModels {
            x.forEach{ userCurr ->
                users {
                    id(userCurr.id)
                    users(userCurr)
                    clickListener { x ->
                        val uid: Int = userCurr.id
                        //Toast.makeText(activity, uid.toString(), Toast.LENGTH_LONG).show()
                        val action = UsersOverviewFragmentDirections.actionUsersOverviewFragmentToUserDetailsFragment(uid)
                        x.findNavController().navigate(action)
                    }
                }
            }
        }
    }
    private fun getUser(){
        userJob?.cancel()
        userJob = lifecycleScope.launch {
            vm.connect()
        }
    }
}