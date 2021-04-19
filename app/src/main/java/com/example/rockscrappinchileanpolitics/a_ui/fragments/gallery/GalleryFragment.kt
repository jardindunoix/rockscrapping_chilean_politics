package com.example.rockscrappinchileanpolitics.a_ui.fragments.gallery

import android.annotation.SuppressLint
import android.app.Dialog
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
import com.example.rockscrappinchileanpolitics.b_viewmodel.GalleryViewModel
import com.example.rockscrappinchileanpolitics.d_utilities.ConnectionLiveData
import com.example.rockscrappinchileanpolitics.databinding.FragmentGalleryBinding
import org.imaginativeworld.whynotimagecarousel.CarouselItem

class GalleryFragment:Fragment() {
	
	private var _binding:FragmentGalleryBinding? = null
	private val binding get() = _binding !!
	private lateinit var model:GalleryViewModel
	private lateinit var navController:NavController
	private val list = mutableListOf<CarouselItem>()
	private lateinit var connectionLiveData:ConnectionLiveData
	
	@SuppressLint("InflateParams")
	override fun onCreateView(
		inflater:LayoutInflater, container:ViewGroup?, savedInstanceState:Bundle?
	):View {
		_binding = FragmentGalleryBinding.inflate(layoutInflater)
		model = ViewModelProvider(this).get(GalleryViewModel::class.java)
		val dialogo = Dialog(requireContext(), R.style.Theme_PlayCore_Transparent)
		val view = this.layoutInflater.inflate(R.layout.fullscreen_progress_bar, null)
		dialogo.setContentView(view)
		dialogo.setCancelable(false)
		dialogo.show()
		
		connectionLiveData = ConnectionLiveData(requireContext())
		connectionLiveData.observe(viewLifecycleOwner, { isNetAvailable ->
			when (isNetAvailable) {
				false -> {
					Toast.makeText(requireContext(), "FALSE", Toast.LENGTH_SHORT).show()
					binding.textView.text = getString(R.string.message_sin_conexion)
					binding.carousel.visibility = View.INVISIBLE
					dialogo.dismiss()
				}
				
				else -> {
					Toast.makeText(
						requireContext(), "TRUE", Toast.LENGTH_SHORT
					).show()
					model.galleryList.observe(viewLifecycleOwner, {
						for ((i, _) in it.withIndex()) {
							list.add(CarouselItem(it[i].webPictureSite))
						}
						binding.textView.text = getString(R.string.text_gallery)
						binding.carousel.visibility = View.VISIBLE
						binding.carousel.addData(list)
						if (it.isNotEmpty()) {
							dialogo.dismiss()
						}
					})
				}
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