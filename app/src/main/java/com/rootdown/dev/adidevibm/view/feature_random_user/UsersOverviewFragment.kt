package com.rootdown.dev.adidevibm.view.feature_random_user

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.rootdown.dev.adidevibm.Injection
import com.rootdown.dev.adidevibm.R
import com.rootdown.dev.adidevibm.databinding.FragmentUsersOverviewBinding
import com.rootdown.dev.adidevibm.model.feature_random_user.db.Users
import com.rootdown.dev.adidevibm.viewmodel.feature_random_user.UserViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class UsersOverviewFragment : Fragment() {
    private lateinit var binding: FragmentUsersOverviewBinding
    private lateinit var state: String

    private val vm: UserViewModel by lazy {
        val activity = requireNotNull(this.activity){
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, Injection.provideViewModelFactory(activity.application))[UserViewModel::class.java]
    }

    private val adapter = UserAdapter()
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
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = vm
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.users.observe(viewLifecycleOwner) {
            ls = it
            adapter.users = it
        }
        Toast.makeText(this.activity, ls.toString(), Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu_1, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.add_random_user -> {
            //Toast.makeText(this.activity, "$$$$$$$$$$$$$", Toast.LENGTH_LONG).show()
            getUser()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
    private fun getUser(){
        userJob?.cancel()
        userJob = lifecycleScope.launch {
            vm.connect()
        }
    }
    private fun onListItemClick(position: Int) {

    }
}