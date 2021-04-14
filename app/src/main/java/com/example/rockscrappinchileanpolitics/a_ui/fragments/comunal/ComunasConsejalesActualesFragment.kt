package com.example.rockscrappinchileanpolitics.a_ui.fragments.comunal

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.rockscrappinchileanpolitics.R
import com.example.rockscrappinchileanpolitics.b_viewmodel.ComunasConsejalesActualesViewModel
import com.example.rockscrappinchileanpolitics.c_model.b_entities.ComunaConsejalActualEntity
import com.example.rockscrappinchileanpolitics.d_utilities.extension_functions.ExtensionFunctions.Companion.initRecyclerView
import com.example.rockscrappinchileanpolitics.d_utilities.interfaces_listeners.ListenerConsejalComunas
import com.example.rockscrappinchileanpolitics.d_utilities.static_strings.StaticUtils
import com.example.rockscrappinchileanpolitics.d_utilities.top_functions.convertComunaForWebPage
import com.example.rockscrappinchileanpolitics.databinding.DialogConsejalesDetailBinding
import com.example.rockscrappinchileanpolitics.databinding.FragmentComunasConsejalesActualesBinding

class ComunasConsejalesActualesFragment:Fragment(), ListenerConsejalComunas {
	
	private var _binding:FragmentComunasConsejalesActualesBinding? = null
	private val binding get() = _binding !!
	private lateinit var navController:NavController
	private lateinit var model:ComunasConsejalesActualesViewModel
	private lateinit var adapter:ComunasConsejalesActualesAdapter
	private var bindingDialog:DialogConsejalesDetailBinding? = null
	private lateinit var dialogo:Dialog
	override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?,
		savedInstanceState:Bundle?):View {
		_binding = FragmentComunasConsejalesActualesBinding.inflate(layoutInflater)
		model = ViewModelProvider(this).get(ComunasConsejalesActualesViewModel::class.java)
		adapter = ComunasConsejalesActualesAdapter(mutableListOf(), requireContext(), this, this)
		initRecyclerView(binding.recyclerViewConsejalesActuales, requireContext(), adapter)
		dialogo = Dialog(requireContext(), R.style.Theme_PlayCore_Transparent)
		val view = this.layoutInflater.inflate(R.layout.fullscreen_progress_bar, null)
		dialogo.setContentView(view)
		dialogo.setCancelable(false)
		dialogo.show()
		
		model.comunasConsejalesActualesList.observe(viewLifecycleOwner, {
			adapter.setItemInTheView(it)
			if (it.isNotEmpty()) {
				dialogo.dismiss()
			}
		})
		return binding.root
	}
	
	override fun onViewCreated(view:View, savedInstanceState:Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		YoYo.with(Techniques.Pulse).duration(StaticUtils.YOYO_DURATION).playOn(binding.textView)
		navController = Navigation.findNavController(view)
	}
	
	override fun viewTouchedShort(position:Int, comunaObjeto:ComunaConsejalActualEntity) {
		Toast.makeText(requireContext(), "Consejales de \n\r${comunaObjeto.nombre}",
			Toast.LENGTH_LONG).show()
		displayDetailDialog(comunaObjeto.nombre)
	}
	
	override fun viewTouchedLong(position:Int, comunaObjeto:ComunaConsejalActualEntity) {
		Toast.makeText(requireContext(), comunaObjeto.paginaWeb, Toast.LENGTH_SHORT).show()
	}
	
	private fun displayDetailDialog(comuna:String) {
		bindingDialog = DialogConsejalesDetailBinding.inflate(layoutInflater)
		val dialog = Dialog(bindingDialog !!.root.context)
		dialog.requestWindowFeature(Window.FEATURE_LEFT_ICON)
		dialog.setCancelable(true)
		dialog.setContentView(bindingDialog !!.root)
		val adapter = ConsejalesActualesDetalleAdapter(mutableListOf(), requireContext())
		bindingDialog !!.recyclerViewConsejalesActualesDetalle.hasFixedSize()
		bindingDialog !!.recyclerViewConsejalesActualesDetalle.layoutManager =
			LinearLayoutManager(requireContext())
		bindingDialog !!.recyclerViewConsejalesActualesDetalle.adapter = adapter
		val comunaM = convertComunaForWebPage(comuna)
		model.getConsejalesDetailUsingViewModel(comunaM)
		bindingDialog !!.textView.text = comuna
		model.detailConsejales.observe(this, {
			adapter.setItemInTheView(it)
		})
		
		bindingDialog !!.btnGoBackDetail.setOnClickListener {
			dialog.dismiss()
		}
		
		dialog.show()
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
	
}