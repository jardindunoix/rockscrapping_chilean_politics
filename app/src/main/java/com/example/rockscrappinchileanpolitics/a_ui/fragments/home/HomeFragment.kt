package com.example.rockscrappinchileanpolitics.a_ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.rockscrappinchileanpolitics.R
import com.example.rockscrappinchileanpolitics.databinding.FragmentHomeBinding

class HomeFragment:Fragment() {
	
	private var _binding:FragmentHomeBinding? = null
	private val binding get() = _binding !!
	private lateinit var navController:NavController
	override fun onCreateView(
		inflater:LayoutInflater, container:ViewGroup?,
		savedInstanceState:Bundle?
	):View {
		_binding = FragmentHomeBinding.inflate(layoutInflater)
		return binding.root
	}
	
	override fun onViewCreated(view:View, savedInstanceState:Bundle?) = with(binding) {
		super.onViewCreated(view, savedInstanceState)
		navController = Navigation.findNavController(view)
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
}