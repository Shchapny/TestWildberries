package com.example.testwildberries.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.testwildberries.R
import com.example.testwildberries.adapter.Listener
import com.example.testwildberries.adapter.TravelAdapter
import com.example.testwildberries.databinding.FragmentTravelListBinding
import com.example.testwildberries.model.Travel
import com.example.testwildberries.viewmodel.TravelListViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TravelListFragment : Fragment(R.layout.fragment_travel_list) {

    private var _binding: FragmentTravelListBinding? = null
    private val binding get() = _binding!!
    private var adapter: TravelAdapter? = null
    private val viewModel by viewModels<TravelListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)?.also {
            _binding = FragmentTravelListBinding.bind(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner) { state ->
            binding.apply {
                progressCircular.isVisible = state.loading
                if (state.error) {
                    Snackbar.make(binding.root, R.string.error_loading, Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.retry) { viewModel.showTours() }
                        .show()
                }
            }
        }

        viewModel.tours.observe(viewLifecycleOwner) { tours ->
            adapter = TravelAdapter(tours, object : Listener {
                override fun onClick(travel: Travel) {
                    val action =
                        TravelListFragmentDirections.actionTravelListFragmentToDetailsAboutTravelFragment(
                            travel
                        )
                    findNavController().navigate(action)
                }

                override fun onLike(travel: Travel) {
                    viewModel.liked(travel)
                }
            })
            binding.rvItem.adapter = adapter
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}