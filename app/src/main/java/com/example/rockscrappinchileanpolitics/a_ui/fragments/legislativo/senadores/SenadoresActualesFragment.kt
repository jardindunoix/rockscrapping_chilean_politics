package com.example.rockscrappinchileanpolitics.a_ui.fragments.legislativo.senadores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.rockscrappinchileanpolitics.R
import com.example.rockscrappinchileanpolitics.b_viewmodel.SenadoresActualesViewModel
import com.example.rockscrappinchileanpolitics.d_utilities.extension_functions.ExtensionFunctions.Companion.initRecyclerView
import com.example.rockscrappinchileanpolitics.d_utilities.static_strings.StaticUtils
import com.example.rockscrappinchileanpolitics.databinding.FragmentSenadoresActualesBinding

class SenadoresActualesFragment:Fragment() {
	
	private var _binding:FragmentSenadoresActualesBinding? = null
	private val binding get() = _binding !!
	private lateinit var navController:NavController
	private lateinit var model:SenadoresActualesViewModel
	private lateinit var adapter:SenadoresActualesAdapter
	private lateinit var dialog:AlertDialog
	override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?,
		savedInstanceState:Bundle?):View {
		_binding = FragmentSenadoresActualesBinding.inflate(layoutInflater)
		model = ViewModelProvider(this).get(SenadoresActualesViewModel::class.java)
		adapter = SenadoresActualesAdapter(mutableListOf(), requireContext())
		initRecyclerView(binding.recyclerViewSenadoresActuales, requireContext(), adapter)
		val builder = AlertDialog.Builder(requireContext())
		val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
		val message = dialogView.findViewById<TextView>(R.id.text_view_)
		message.text = getText(R.string.progress_cargando)
		builder.setView(dialogView)
		builder.setCancelable(false)
		dialog = builder.create()
		dialog.show()
		model.senadoresActualesList.observe(viewLifecycleOwner, {
			adapter.setItemInTheView(it)
			dialog.dismiss()
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