package com.example.rockscrappinchileanpolitics.a_ui.fragments.gallery

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.rockscrappinchileanpolitics.R
import com.example.rockscrappinchileanpolitics.b_viewmodel.GalleryViewModel
import com.example.rockscrappinchileanpolitics.b_viewmodel.NetworkViewModel
import com.example.rockscrappinchileanpolitics.databinding.FragmentGalleryBinding
import org.imaginativeworld.whynotimagecarousel.CarouselItem

class GalleryFragment:Fragment() {
	
	private var _binding:FragmentGalleryBinding? = null
	private val binding get() = _binding !!
	private lateinit var model:GalleryViewModel
	private lateinit var modelNetwork:NetworkViewModel
	private lateinit var navController:NavController
	private val list = mutableListOf<CarouselItem>()
	
	@SuppressLint("InflateParams")
	override fun onCreateView(
		inflater:LayoutInflater, container:ViewGroup?, savedInstanceState:Bundle?
	):View {
		_binding = FragmentGalleryBinding.inflate(layoutInflater)
		val dialogo = Dialog(requireContext(), R.style.Theme_PlayCore_Transparent)
		val view = this.layoutInflater.inflate(R.layout.fullscreen_progress_bar, null)
		dialogo.setContentView(view)
		dialogo.setCancelable(false)
		dialogo.show()
		model = ViewModelProvider(this).get(GalleryViewModel::class.java)
		modelNetwork = ViewModelProvider(this).get(NetworkViewModel::class.java)
		modelNetwork.networkStatus.observe(viewLifecycleOwner, { net ->
			if (net == true) {
				model.galleryList.observe(viewLifecycleOwner, {
					for ((i, _) in it.withIndex()) {
						list.add(CarouselItem(it[i].webPictureSite))
					}
					binding.carousel.addData(list)
					if (it.isNotEmpty()) {
						dialogo.dismiss()
					}
				})
			} else {
				binding.textView.text = getString(R.string.message_sin_conexion)
				binding.carousel.visibility = View.INVISIBLE
				dialogo.dismiss()
			}
		})
		return binding.root
	}
	
	override fun onViewCreated(view:View, savedInstanceState:Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		navController = Navigation.findNavController(view)
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		list.clear()
		_binding = null
	}
}