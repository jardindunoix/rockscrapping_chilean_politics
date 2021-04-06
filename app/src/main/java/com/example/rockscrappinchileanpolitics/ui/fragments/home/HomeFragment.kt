package com.example.rockscrappinchileanpolitics.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.rockscrappinchileanpolitics.R
import com.example.rockscrappinchileanpolitics.databinding.FragmentHomeBinding
import com.example.rockscrappinchileanpolitics.ui.adapters.heade_home.HeaderHomeAdapter
import com.example.rockscrappinchileanpolitics.utilities.services.extension_functions.ExtensionFunctions.Companion.initRecyclerView
import com.example.rockscrappinchileanpolitics.utilities.services.extension_functions.ExtensionFunctions.Companion.initRecyclerViewHorizontal
import com.example.rockscrappinchileanpolitics.viewmodel.header_home.HeaderHomeViewModel

class HomeFragment:Fragment() {
	
	private var _binding:FragmentHomeBinding? = null
	private val binding get() = _binding !!
	private lateinit var navController:NavController
	private lateinit var model:HeaderHomeViewModel
	private lateinit var adapter:HeaderHomeAdapter
	
	override fun onCreateView(
		inflater:LayoutInflater, container:ViewGroup?,
		savedInstanceState:Bundle?
	):View {
		_binding = FragmentHomeBinding.inflate(layoutInflater)
		model = ViewModelProvider(this).get(HeaderHomeViewModel::class.java)
		adapter = HeaderHomeAdapter(mutableListOf(), requireContext())
		
		binding.recyclerViewHeaderHome.initRecyclerViewHorizontal(
			binding.recyclerViewHeaderHome,
			requireContext(), adapter
		)
		model.headerHomeList.observe(viewLifecycleOwner, {
			if (it.isNotEmpty()) {
				adapter.setItemInTheView(it)
			} else {
				Toast.makeText(
					requireContext(), getString(R.string.text_for_header_without_connection), Toast
						.LENGTH_LONG
				).show()
				// binding.textView.text = getString(R.string.text_for_header_without_connection)
			}
		})
		
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
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}