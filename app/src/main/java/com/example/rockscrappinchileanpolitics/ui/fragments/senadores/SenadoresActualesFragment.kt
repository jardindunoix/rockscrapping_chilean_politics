package com.example.rockscrappinchileanpolitics.ui.fragments.senadores

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rockscrappinchileanpolitics.databinding.FragmentSenadoresActualesBinding
import com.example.rockscrappinchileanpolitics.ui.adapters.senadores.SenadoresActualesAdapter
import com.example.rockscrappinchileanpolitics.viewmodel.diputados.DiputadosActualesViewModel
import com.example.rockscrappinchileanpolitics.viewmodel.senadores.SenadoresActualesViewModel

class SenadoresActualesFragment:Fragment() {
	
	private var _binding:FragmentSenadoresActualesBinding? = null
	private val binding get() = _binding !!
	private lateinit var navController:NavController
	private lateinit var model:SenadoresActualesViewModel
	private lateinit var adapter:SenadoresActualesAdapter
	override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?,
		savedInstanceState:Bundle?):View {
		_binding = FragmentSenadoresActualesBinding.inflate(layoutInflater)
		model = ViewModelProvider(this).get(SenadoresActualesViewModel::class.java)
		adapter = SenadoresActualesAdapter(mutableListOf(), requireContext())
		
		initRecyclerView()
		
		model.senadoresActualesList.observe(viewLifecycleOwner, {
			adapter.setItemInTheView(it)
		})
		return binding.root
	}
	
	private fun initRecyclerView() = with(binding) {
		recyclerViewSenadoresActuales.hasFixedSize()
		recyclerViewSenadoresActuales.layoutManager = LinearLayoutManager(requireContext())
		recyclerViewSenadoresActuales.adapter = adapter
	}
	
	override fun onViewCreated(view:View, savedInstanceState:Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		navController = Navigation.findNavController(view)
	}
}