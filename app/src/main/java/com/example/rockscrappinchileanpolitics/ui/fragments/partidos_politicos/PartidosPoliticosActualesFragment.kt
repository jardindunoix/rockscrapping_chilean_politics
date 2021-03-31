package com.example.rockscrappinchileanpolitics.ui.fragments.partidos_politicos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rockscrappinchileanpolitics.R
import com.example.rockscrappinchileanpolitics.databinding.FragmentPartidosPoliticosActualesBinding
import com.example.rockscrappinchileanpolitics.ui.adapters.partidos_politicos.PartidosPoliticosAdapter
import com.example.rockscrappinchileanpolitics.utilities.services.extension_functions.ExtensionFunctions.Companion.initRecyclerView
import com.example.rockscrappinchileanpolitics.viewmodel.partidos_politicos.PartidosPoliticosViewModel

class PartidosPoliticosActualesFragment : Fragment() {

    private var _binding: FragmentPartidosPoliticosActualesBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var model: PartidosPoliticosViewModel
    private lateinit var adapter: PartidosPoliticosAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentPartidosPoliticosActualesBinding.inflate(layoutInflater)
        adapter = PartidosPoliticosAdapter(mutableListOf(), requireContext())
        binding.recyclerViewPartidosPoliticos.initRecyclerView(recycler = binding.recyclerViewPartidosPoliticos,
            requireContext(), adapter)
        model = ViewModelProvider(this).get(PartidosPoliticosViewModel::class.java)

        model.partidosActualesList.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                adapter.setItemInTheView(it)
            } else {
                binding.textView.text = getString(R.string.text_for_header_without_connection)
            }
        })
        return binding.root
    }

    private fun initRecyclerView() = with(binding) {
        recyclerViewPartidosPoliticos.hasFixedSize()
        recyclerViewPartidosPoliticos.layoutManager =
            LinearLayoutManager(requireContext())
        recyclerViewPartidosPoliticos.adapter = adapter
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