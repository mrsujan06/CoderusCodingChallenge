package com.coderus.codingchallenge.rocketlaunchdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.coderus.codingchallenge.R
import com.coderus.codingchallenge.databinding.FragmentDetailBinding
import com.coderus.codingchallenge.domain.RocketLaunch
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment to display the list of RocketLaunchJson Launches.
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

        val args = DetailFragmentArgs.fromBundle(requireArguments())
        val rocketLauncher: RocketLaunch? = args.rocketLauncher

        binding.flightNumber.text = rocketLauncher?.flightNumber.toString()
        binding.details.text = rocketLauncher?.details
        binding.launchDateUTC.text = rocketLauncher?.dateUTC

        when {
            rocketLauncher?.success == true -> {
                binding.launchSuccess.text = context?.getString(R.string.successful)
            }
            rocketLauncher?.upcoming == true -> {
                binding.launchSuccess.text = context?.getString(R.string.upcoming)
            }
            else -> {
                binding.launchSuccess.text = context?.getString(R.string.unsuccessful)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
