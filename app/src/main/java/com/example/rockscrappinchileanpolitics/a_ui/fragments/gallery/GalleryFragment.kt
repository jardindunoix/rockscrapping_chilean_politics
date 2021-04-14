package com.example.rockscrappinchileanpolitics.a_ui.fragments.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.rockscrappinchileanpolitics.b_viewmodel.GalleryViewModel
import com.example.rockscrappinchileanpolitics.databinding.FragmentGalleryBinding
import org.imaginativeworld.whynotimagecarousel.CarouselItem

class GalleryFragment:Fragment() {
	
	private var _binding:FragmentGalleryBinding? = null
	private val binding get() = _binding !!
	private lateinit var model:GalleryViewModel
	private lateinit var navController:NavController
	private val list = mutableListOf<CarouselItem>()
	override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?,
		savedInstanceState:Bundle?):View {
		_binding = FragmentGalleryBinding.inflate(layoutInflater)
		model = ViewModelProvider(this).get(GalleryViewModel::class.java)
		model.galleryList.observe(viewLifecycleOwner, {
			for ((i, _) in it.withIndex()) {
				list.add(CarouselItem(it[i].webPictureSite))
			}
			binding.carousel.addData(list)
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