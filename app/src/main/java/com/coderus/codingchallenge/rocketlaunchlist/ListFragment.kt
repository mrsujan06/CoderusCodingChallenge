package com.coderus.codingchallenge.rocketlaunchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.coderus.codingchallenge.R
import com.coderus.codingchallenge.ViewModelFactory
import com.coderus.codingchallenge.databinding.FragmentListBinding
import com.coderus.codingchallenge.domain.RocketLaunch
import com.coderus.codingchallenge.listener.ItemClickListener
import com.coderus.codingchallenge.repository.RocketLaunchRepository
import com.coderus.codingchallenge.utils.ConnectionChecker
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Fragment to display the list of RocketLaunchNetwork Launches.
 */
@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {

    @Inject
    lateinit var launchRepository: RocketLaunchRepository

    @Inject
    lateinit var connectionChecker: ConnectionChecker

    private lateinit var viewModel: ListViewModel
    private lateinit var adapter: RocketLaunchListAdapter

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding
        get() = _binding!!

    private val itemClickListener: ItemClickListener = object : ItemClickListener {
        override fun onRocketClick(rocketLaunch: RocketLaunch) {

            val action = ListFragmentDirections.actionListFragmentToDetailFragment(rocketLaunch)

//            Toast.makeText(requireContext(), rocketLaunch.name, Toast.LENGTH_SHORT).show()
//            val bundle = Bundle()
//            bundle.putInt("flightNumber", rocketLaunch.flightNumber)
//            bundle.putString("details", rocketLaunch.details)
//            bundle.putString("dateUtc", rocketLaunch.dateUTC)
//            rocketLaunch.upcoming?.let { bundle.putBoolean("upcoming", it) }
//            rocketLaunch.success?.let { bundle.putBoolean("launchSuccess", it) }

            findNavController().navigate(action)
      }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
        setupViewModel()
    }

    private fun setupList() {
        adapter = RocketLaunchListAdapter(requireContext(), itemClickListener).apply {
            binding.rocketLaunchList.adapter = this
            binding.rocketLaunchList.addItemDecoration(ListItemDecoration(20))
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this, ViewModelFactory(launchRepository, connectionChecker)
        ).get(ListViewModel::class.java)

        viewModel.refreshDataFromRepository()

        viewModel.rocketLaunches.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.loadingState.observe(viewLifecycleOwner) {
            when (it) {
                ListViewModel.LoadingState.LOADING -> displayProgressbar()
                ListViewModel.LoadingState.DONE -> displayRocketLunchesList()
                ListViewModel.LoadingState.ERROR -> displayConnectionError()
            }
        }

    }

    private fun displayProgressbar() {
        binding.progressBar.visibility = View.VISIBLE
        binding.rocketLaunchList.visibility = View.GONE
    }

    private fun displayRocketLunchesList() {
        binding.progressBar.visibility = View.GONE
        binding.rocketLaunchList.visibility = View.VISIBLE
    }


    private fun displayConnectionError() {
        binding.progressBar.visibility = View.GONE
        Toast.makeText(context, R.string.network_error_message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
