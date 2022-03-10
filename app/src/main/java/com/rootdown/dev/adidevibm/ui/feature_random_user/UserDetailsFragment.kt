package com.rootdown.dev.adidevibm.ui.feature_random_user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rootdown.dev.adidevibm.R
import com.rootdown.dev.adidevibm.databinding.FragmentUserDetailsBinding
import com.rootdown.dev.adidevibm.ui.viewmodel.feature_random_user.UserDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.Observer

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {
    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = _binding!!
    private val vm: UserDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        val img: ImageView = _binding!!.imgUserDetail
        vm.u.observe(viewLifecycleOwner, Observer {
            val url = it.picturemd
            Glide.with(this)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img)
            _binding!!.txtUserDetailName.text = it?.namefirst
            _binding!!.txtUserDetailNameLast.text = it?.namelast
            _binding!!.txtUserCity.text = "City: "+it?.city
            _binding!!.txtUserState.text = "State: "+it?.state
        })
        return view
    }
}