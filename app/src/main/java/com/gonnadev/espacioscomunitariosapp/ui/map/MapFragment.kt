package com.gonnadev.espacioscomunitariosapp.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.gonnadev.espacioscomunitariosapp.R
import com.gonnadev.espacioscomunitariosapp.databinding.FragmentMapBinding
import com.gonnadev.espacioscomunitariosapp.domain.model.EspacioModel
import com.gonnadev.espacioscomunitariosapp.ui.list.ListFragmentDirections
import com.gonnadev.espacioscomunitariosapp.ui.list.ListState
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private val mapViewModel: MapViewModel by viewModels<MapViewModel>()
    private lateinit var list: List<EspacioModel>

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        mapViewModel.getList()
    }

    private fun initUI() {
        initUIState()
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mapViewModel.state.collect {
                    when (it) {
                        ListState.Loading -> loadingState()
                        is ListState.Error -> errorState()
                        is ListState.Success -> succesState(it)
                    }
                }
            }
        }
    }

    fun loadingState(){}

    fun errorState(){}

    fun succesState(it: ListState.Success){
        list = it.list
        initMap()
    }

    private fun initMap() {
        val map = childFragmentManager.findFragmentById(R.id.fragmentMap) as SupportMapFragment
        map.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        for (item in list) {
            val coordinates = item.coordenadas
            val (latitude, longitude) = coordinates.split(",").map { it.toDouble() }
            val location = LatLng(latitude, longitude)
            val marker = googleMap.addMarker(MarkerOptions().position(location).title(item.nombre))
            marker?.tag = item.id
            val berisso = LatLng(-34.866666666667, -57.866666666667)

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(berisso, 12f))
        }

        googleMap.setOnMarkerClickListener {
            it.showInfoWindow()
            true
        }

        googleMap.setOnInfoWindowClickListener {
            findNavController().navigate(
                MapFragmentDirections.actionMapFragmentToDetailActivity(it.tag as Int)
            )
        }
    }
}