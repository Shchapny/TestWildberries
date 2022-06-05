package com.example.testwildberries.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.testwildberries.R
import com.example.testwildberries.databinding.FragmentDetailsAboutTravelBinding
import com.example.testwildberries.util.dateFormatDetails
import com.example.testwildberries.viewmodel.TravelListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsAboutTravelFragment : Fragment(R.layout.fragment_details_about_travel) {

    private val tourArgs by navArgs<DetailsAboutTravelFragmentArgs>()
    private var _binding: FragmentDetailsAboutTravelBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<TravelListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)?.also {
            _binding = FragmentDetailsAboutTravelBinding.bind(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarDetails.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.apply {
            tourArgs.detailsTravel.apply {
                textPrice.text = root.context.getString(R.string.price, price.toString())
                textStartLocation.text =
                    root.context.getString(
                        R.string.details_city,
                        startCity,
                        startCityCode.uppercase()
                    )
                textEndLocation.text =
                    root.context.getString(R.string.details_city, endCity, endCityCode.uppercase())
                textDateDeparture.text = startDate.dateFormatDetails()
                textDateArrival.text = endDate.dateFormatDetails()
                buttonLike.isChecked = liked
                buttonLike.setOnClickListener {
                    viewModel.liked(this)
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}