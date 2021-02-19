package com.coderus.codingchallenge.rocketlaunchdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.Person.fromBundle
import androidx.fragment.app.Fragment
import com.coderus.codingchallenge.R
import com.coderus.codingchallenge.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment to display the list of Rocket Launches.
 */
@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val flightNumber = arguments?.getInt("flightNumber")
        val details = arguments?.getString("details")
        val dateUtc = arguments?.getString("dateUtc")
        val rocketLaunch = arguments?.getBoolean("launchSuccess")

        binding.flightNumber.text = flightNumber.toString()
        binding.details.text = details
        binding.launchDateUTC.text = dateUtc
        binding.launchSuccess.text = rocketLaunch.toString()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
