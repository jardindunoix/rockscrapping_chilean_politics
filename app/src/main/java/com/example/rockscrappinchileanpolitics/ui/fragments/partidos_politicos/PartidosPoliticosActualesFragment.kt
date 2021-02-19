package com.example.rockscrappinchileanpolitics.ui.fragments.partidos_politicos

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
import com.example.rockscrappinchileanpolitics.databinding.FragmentDiputadosActualesBinding
import com.example.rockscrappinchileanpolitics.databinding.FragmentPartidosPoliticosActualesBinding
import com.example.rockscrappinchileanpolitics.ui.adapters.diputados.DiputadosActualesAdapter
import com.example.rockscrappinchileanpolitics.ui.adapters.partidos_politicos.PartidosPoliticosAdapter
import com.example.rockscrappinchileanpolitics.viewmodel.PoliticsViewModel

class PartidosPoliticosActualesFragment : Fragment() {

    private var _binding: FragmentPartidosPoliticosActualesBinding? =
        null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var model: PoliticsViewModel
    private lateinit var adapter: PartidosPoliticosAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentPartidosPoliticosActualesBinding.inflate(layoutInflater)
        adapter =
            PartidosPoliticosAdapter(
                mutableListOf(),
                requireContext()
            )

        initRecyclerView()
        model =
            ViewModelProvider(this).get(PoliticsViewModel::class.java)

        model.partidosActualesList!!.observe(viewLifecycleOwner,
            {
                adapter.setItemInTheView(it)
            })
        return binding.root

    }

    private fun initRecyclerView() =
        with(binding) {
            recyclerViewPartidosActuales.hasFixedSize()
            recyclerViewPartidosActuales.layoutManager =
                LinearLayoutManager(requireContext())
            recyclerViewPartidosActuales.adapter = adapter
        }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(
            view,
            savedInstanceState
        )
        navController = Navigation.findNavController(view)
    }

}