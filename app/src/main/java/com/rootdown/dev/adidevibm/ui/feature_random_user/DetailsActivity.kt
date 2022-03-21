package com.rootdown.dev.adidevibm.ui.feature_random_user

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.rootdown.dev.adidevibm.R
import com.rootdown.dev.adidevibm.data.feature_random_user.models.Coordinates
import com.rootdown.dev.adidevibm.data.feature_random_user.models.UserX
import com.rootdown.dev.adidevibm.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val user: UserX? = intent.getParcelableExtra("saved-user-key")

        // TODO - DONE (1 point): Use Glide to load images
//    binding.detailsImage = user.getPicture().getLarge();
        Glide.with(this).load(user!!.picture!!.large)
            .centerCrop()
            .placeholder(R.drawable.ic_baseline_autorenew_24)
            .into(binding.detailsImage)
        val name = user.name
        binding.detailsName.text = getString(R.string.details_name, name!!.first, name.last)
        binding.detailsEmail.text = getString(R.string.details_email, user!!.gender)
        binding.detailsAge.text = getString(R.string.details_age, user.dob!!.age.toString())
        val coordinates = user.location!!.coordinates
        binding.detailsLocation.text =
            getString(R.string.details_location, coordinates!!.latitude, coordinates.longitude)
        binding.detailsLocationButton.setOnClickListener { x: View? ->
            navigateLocation(
                coordinates
            )
        }
    }

    private fun navigateLocation(coordinates: Coordinates) {
        val intent = Intent(this, LocationActivity::class.java)
            .putExtra("user-latitude-key", coordinates.latitude)
            .putExtra("user-longitude-key", coordinates.longitude)
        startActivity(intent)
    }
}