package com.example.rockscrappinchileanpolitics.ui.fragments.comunal.consejales

import android.R.attr.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rockscrappinchileanpolitics.R
import com.example.rockscrappinchileanpolitics.databinding.FragmentComunasConsejalesActualesBinding
import com.example.rockscrappinchileanpolitics.ui.adapters.comunal.consejales.ComunasConsejalesActualesAdapter
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ComunaConsejalActualEntity
import com.example.rockscrappinchileanpolitics.utilities.services.interfaces_listeners.ListenerConsejalComunas
import com.example.rockscrappinchileanpolitics.viewmodel.comunal.consejal.ComunasConsejalesActualesViewModel

class ComunasConsejalesActualesFragment:Fragment(), ListenerConsejalComunas {
	
	private var _binding:FragmentComunasConsejalesActualesBinding? = null
	private val binding get() = _binding !!
	private lateinit var navController:NavController
	private lateinit var model:ComunasConsejalesActualesViewModel
	private lateinit var adapter:ComunasConsejalesActualesAdapter
	
	override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?,
		savedInstanceState:Bundle?):View {
		_binding = FragmentComunasConsejalesActualesBinding.inflate(layoutInflater)
		model = ViewModelProvider(this).get(ComunasConsejalesActualesViewModel::class.java)
		adapter = ComunasConsejalesActualesAdapter(mutableListOf(), requireContext(), this, this)
		
		initRecyclerView()
		
		model.comunasConsejalesActualesList.observe(viewLifecycleOwner, {
			adapter.setItemInTheView(it)
		})
		return binding.root
	}
	
	private fun initRecyclerView() = with(binding) {
		recyclerViewConsejalesActuales.hasFixedSize()
		recyclerViewConsejalesActuales.layoutManager = LinearLayoutManager(requireContext())
		recyclerViewConsejalesActuales.adapter = adapter
	}
	
	override fun onViewCreated(view:View, savedInstanceState:Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		navController = Navigation.findNavController(view)
	}
	
	override fun viewTouchedShort(
		position:Int, comunaObjeto:ComunaConsejalActualEntity,
	) {
	}
	
	override fun viewTouchedLong(position:Int, comunaObjeto:ComunaConsejalActualEntity) {
		navController.navigate(
			R.id.action_consejalesActualesFragment_to_consejalesActualesDetalleFragment)
		Toast.makeText(requireContext(), comunaObjeto.nombre, Toast.LENGTH_LONG).show()
		val args =Bundle()
		args.putString("comuna", comunaObjeto.nombre)
		setFragmentResult("key", args)
	}
}