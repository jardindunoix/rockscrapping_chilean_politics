package com.example.rockscrappinchileanpolitics.ui.fragments.legislativo.senadores

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rockscrappinchileanpolitics.R
import com.example.rockscrappinchileanpolitics.databinding.FragmentSenadoresActualesBinding
import com.example.rockscrappinchileanpolitics.ui.adapters.legislativo.senadores.SenadoresActualesAdapter
import com.example.rockscrappinchileanpolitics.utilities.services.extension_functions.ExtensionFunctions.Companion.initRecyclerView
import com.example.rockscrappinchileanpolitics.viewmodel.legislativo.senadores.SenadoresActualesViewModel

class SenadoresActualesFragment : Fragment() {

    private var _binding: FragmentSenadoresActualesBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var model: SenadoresActualesViewModel
    private lateinit var adapter: SenadoresActualesAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentSenadoresActualesBinding.inflate(layoutInflater)
        model = ViewModelProvider(this).get(SenadoresActualesViewModel::class.java)
        adapter = SenadoresActualesAdapter(mutableListOf(), requireContext())
        binding.recyclerViewSenadoresActuales.initRecyclerView(recycler = binding.recyclerViewSenadoresActuales,
            requireContext(), adapter)
        model.senadoresActualesList.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                adapter.setItemInTheView(it)
            } else {
                binding.textView.text = getString(R.string.text_for_header_without_connection)
            }
        })
        return binding.root
    }

    private fun initRecyclerView() = with(binding) {
        recyclerViewSenadoresActuales.hasFixedSize()
        recyclerViewSenadoresActuales.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewSenadoresActuales.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}