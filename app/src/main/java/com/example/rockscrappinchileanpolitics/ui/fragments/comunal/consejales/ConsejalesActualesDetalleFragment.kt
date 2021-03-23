package com.example.rockscrappinchileanpolitics.ui.fragments.comunal.consejales

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rockscrappinchileanpolitics.databinding.FragmentConsejalesActualesDetalleBinding
import com.example.rockscrappinchileanpolitics.ui.adapters.comunal.consejales.ConsejalesActualesDetalleAdapter
import com.example.rockscrappinchileanpolitics.viewmodel.comunal.consejal.ConsejalesActualesDetalleViewModel

class ConsejalesActualesDetalleFragment : Fragment() {

    private var _binding: FragmentConsejalesActualesDetalleBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var model: ConsejalesActualesDetalleViewModel
    private lateinit var adapter: ConsejalesActualesDetalleAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConsejalesActualesDetalleBinding.inflate(layoutInflater)
        model = ViewModelProvider(this).get(ConsejalesActualesDetalleViewModel::class.java)
        adapter = ConsejalesActualesDetalleAdapter(mutableListOf(), requireContext())

        initRecyclerView()

        model.consejalesActualesDetalleList.observe(viewLifecycleOwner, {
            adapter.setItemInTheView(it)
        })

        parentFragmentManager.setFragmentResultListener("key", this, { requestKey, result ->
            val comuna = result.getString("comuna")
            binding.textView.text = comuna
        })
        return binding.root
    }

    private fun initRecyclerView() = with(binding) {
        recyclerViewConsejalesActualesDetalle.hasFixedSize()
        recyclerViewConsejalesActualesDetalle.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewConsejalesActualesDetalle.adapter = adapter
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