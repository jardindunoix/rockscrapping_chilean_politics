package com.example.rockscrappinchileanpolitics.ui.fragments.partidos_politicos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.rockscrappinchileanpolitics.R
import com.example.rockscrappinchileanpolitics.databinding.FragmentPartidosPoliticosActualesBinding
import com.example.rockscrappinchileanpolitics.ui.adapters.partidos_politicos.PartidosPoliticosAdapter
import com.example.rockscrappinchileanpolitics.utilities.services.extension_functions.ExtensionFunctions.Companion.initRecyclerView
import com.example.rockscrappinchileanpolitics.utilities.services.static_strings.StaticUtils
import com.example.rockscrappinchileanpolitics.viewmodel.partidos_politicos.PartidosPoliticosViewModel

class PartidosPoliticosActualesFragment:Fragment() {
	
	private var _binding:FragmentPartidosPoliticosActualesBinding? = null
	private val binding get() = _binding !!
	private lateinit var navController:NavController
	private lateinit var model:PartidosPoliticosViewModel
	private lateinit var adapter:PartidosPoliticosAdapter
	
	override fun onCreateView(
		inflater:LayoutInflater, container:ViewGroup?,
		savedInstanceState:Bundle?
	):View {
		_binding = FragmentPartidosPoliticosActualesBinding.inflate(layoutInflater)
		adapter = PartidosPoliticosAdapter(mutableListOf(), requireContext())
		binding.recyclerViewPartidosPoliticos.initRecyclerView(
			binding.recyclerViewPartidosPoliticos,
			requireContext(), adapter
		)
		model = ViewModelProvider(this).get(PartidosPoliticosViewModel::class.java)
		
		model.partidosActualesList.observe(viewLifecycleOwner, {
			adapter.setItemInTheView(it)
		})
		return binding.root
	}
	
	override fun onViewCreated(view:View, savedInstanceState:Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		YoYo.with(Techniques.Tada).duration(StaticUtils.YOYO_DURATION).playOn(binding.textView)
		navController = Navigation.findNavController(view)
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}