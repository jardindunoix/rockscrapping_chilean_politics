package com.example.rockscrappinchileanpolitics.ui.fragments.legislativo.diputados

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rockscrappinchileanpolitics.R
import com.example.rockscrappinchileanpolitics.databinding.FragmentDiputadosActualesBinding
import com.example.rockscrappinchileanpolitics.ui.adapters.legislativo.diputados.DiputadosActualesAdapter
import com.example.rockscrappinchileanpolitics.utilities.services.extension_functions.ExtensionFunctions.Companion.initRecyclerView
import com.example.rockscrappinchileanpolitics.viewmodel.legislativo.diputados.DiputadosActualesViewModel

class DiputadosActualesFragment:Fragment() {
	
	private var _binding:FragmentDiputadosActualesBinding? = null
	private val binding get() = _binding !!
	private lateinit var navController:NavController
	private lateinit var model:DiputadosActualesViewModel
	private lateinit var adapter:DiputadosActualesAdapter
	override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?,
		savedInstanceState:Bundle?):View {
		_binding = FragmentDiputadosActualesBinding.inflate(layoutInflater)
		model = ViewModelProvider(this).get(DiputadosActualesViewModel::class.java)
		adapter = DiputadosActualesAdapter(mutableListOf(), requireContext())
		binding.recyclerViewDiputadosActuales.initRecyclerView(recycler = binding.recyclerViewDiputadosActuales,
			requireContext(), adapter)
		model.diputadosActualesList.observe(viewLifecycleOwner, {
			if (it.isNotEmpty()) {
				adapter.setItemInTheView(it)
			} else {
				binding.textView.text = getString(R.string.text_for_header_without_connection)
			}
		})
		return binding.root
	}
	
	private fun initRecyclerView() = with(binding) {
		recyclerViewDiputadosActuales.hasFixedSize()
		recyclerViewDiputadosActuales.layoutManager = LinearLayoutManager(requireContext())
		recyclerViewDiputadosActuales.adapter = adapter
	}
	
	override fun onViewCreated(view:View, savedInstanceState:Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		navController = Navigation.findNavController(view)
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}