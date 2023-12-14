package com.example.devtestapp.presentation.contactDetail


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.devtestapp.R
import com.example.devtestapp.databinding.FragmentContactDetailBinding
import com.example.devtestapp.domain.model.ContactsModel
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.appbar.AppBarLayout


class ContactDetailFragment : Fragment() {
    private var _binding: FragmentContactDetailBinding? = null
    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap

    private lateinit var mContact: ContactsModel
    private val nArgs: ContactDetailFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentContactDetailBinding.inflate(inflater, container, false)
        _binding = binding
        mapView = _binding!!.mapView
        mapView.onCreate(savedInstanceState)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mContact = nArgs.contact
        paintUI(mContact)
    }


    private fun setupToolbar(name: String) {

        val appBarLayout = requireActivity().findViewById<AppBarLayout>(R.id.appBarLayout)
        appBarLayout.visibility = View.GONE

        _binding!!.mtToolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        _binding!!.tvToolbarTitle.text = name
        _binding!!.mtToolbar.setOnClickListener { findNavController().navigateUp() }

    }

    private fun paintUI(mContact: ContactsModel) {
        _binding!!.tvNameValue.text = mContact.name
        _binding!!.tvEmailValue.text = mContact.email
        _binding!!.tvGenderValue.text = mContact.gender
        _binding!!.tvDateValue.text = mContact.registeredDate
        _binding!!.tvPhoneValue.text = mContact.phone

        setupToolbar(mContact.name)



        if (!mContact.latitude.isNullOrBlank() && !mContact.longitude.isNullOrBlank()) {
            paintGoogleMapAddress(mContact.latitude, mContact.longitude)
        }
        if (!mContact.pictureUrl.isNullOrBlank()) {
            paintProfilePic(mContact.pictureUrl)
        }


    }

    private fun paintProfilePic(pictureUrl: String) {
        val circularProgress = CircularProgressDrawable(requireContext())
        circularProgress.strokeWidth = 5f
        circularProgress.centerRadius = 30f
        circularProgress.start()

        Glide.with(this)
            .load(pictureUrl)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    circularProgress.stop()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    circularProgress.stop()
                    return false
                }

            })
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .error(R.drawable.ic_error_load)
            .into(_binding!!.profilePic)
    }

    private fun paintGoogleMapAddress(latitude: String, longitude: String) {

        mapView.getMapAsync { googleMap ->
            this.googleMap = googleMap
            try {
                MapsInitializer.initialize(requireActivity())
            } catch (e: GooglePlayServicesNotAvailableException) {
                e.printStackTrace()
            }


            val latLng = LatLng(latitude.toDouble(), longitude.toDouble())
            // val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 100f)
            val markerOptions =
                MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker2))

            val cameraPosition = CameraPosition.Builder().target(latLng).zoom(14f).tilt(70f).build()
            markerOptions.position(latLng)

            googleMap.addMarker(markerOptions)
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 7f))
        }
    }

    override fun onResume() {
        mapView.onResume()
        super.onResume()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}