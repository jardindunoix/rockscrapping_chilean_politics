package com.example.rockscrappinchileanpolitics.a_ui.fragments.a__home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.rockscrappinchileanpolitics.R
import com.example.rockscrappinchileanpolitics.d_utilities.ConnectionLiveData
import com.example.rockscrappinchileanpolitics.databinding.FragmentHomeBinding

class HomeFragment:Fragment() {
	
	private var _binding:FragmentHomeBinding? = null
	private val binding get() = _binding !!
	private lateinit var navController:NavController
	private lateinit var connectionLiveData:ConnectionLiveData
	
	override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?,
		savedInstanceState:Bundle?):View {
		_binding = FragmentHomeBinding.inflate(layoutInflater)
		return binding.root
	}
	
	override fun onViewCreated(view:View, savedInstanceState:Bundle?) = with(binding) {
		super.onViewCreated(view, savedInstanceState)
		navController = Navigation.findNavController(view)
		connectionLiveData = ConnectionLiveData(requireContext())
		buttonIrADipActuales.setOnClickListener {
			navController.navigate(R.id.action_homeFragment_to_diputadosActualesFragment)
		}
		buttonIrASenActuales.setOnClickListener {
			navController.navigate(R.id.action_homeFragment_to_senadoresActualesFragment)
		}
		buttonPartPoliticos.setOnClickListener {
			navController.navigate(R.id.action_homeFragment_to_partidosActualesFragment)
		}
		buttonIrAConsejActuales.setOnClickListener {
			navController.navigate(R.id.action_homeFragment_to_consejalesActualesFragment)
		}
		buttonIrAGallery.setOnClickListener {
			navController.navigate(R.id.action_homeFragment_to_galleryFragment)
		}
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
	
	// private fun detectNetwork() {
	// 	connectionLiveData.observe(viewLifecycleOwner, { isNetAvailable ->
	// 		when (isNetAvailable) {
	// 			true -> navController.navigate(R.id.action_homeFragment_to_galleryFragment)
	// 			false -> Toast.makeText(requireContext(), getString(R.string.message_sin_conexion),
	// 				Toast.LENGTH_SHORT).show()
	// 		}
	// 	})
	// }
}