package com.coderus.codingchallenge.rocketlaunchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.coderus.codingchallenge.R
import com.coderus.codingchallenge.ViewModelFactory
import com.coderus.codingchallenge.databinding.FragmentListBinding
import com.coderus.codingchallenge.repository.RocketLaunchesRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Fragment to display the list of Rocket Launches.
 */
@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {

    @Inject
    lateinit var repository: RocketLaunchesRepository

    private lateinit var viewModel: ListViewModel
    private lateinit var adapter: RocketLaunchListAdapter

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding
        get() = _binding!!

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
        setupRecyclerview()
        setupViewModel()
    }

    private fun setupList() {
        RocketLaunchListAdapter(requireContext()).apply {
            binding.rocketLaunchList.adapter = this
            binding.rocketLaunchList.addItemDecoration(ListItemDecoration(20))
        }
    }

    private fun setupRecyclerview() {
        adapter = RocketLaunchListAdapter(this.requireContext())
        binding.rocketLaunchList.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this, ViewModelFactory(repository)
        ).get(ListViewModel::class.java)

        viewModel.retrieveData()

        viewModel.rocketLunches.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.loadingState.observe(viewLifecycleOwner) {
            when (it) {
                ListViewModel.LoadingState.LOADING -> displayProgressbar()
                ListViewModel.LoadingState.DONE -> displayRocketLunchesList()
                else -> displayConnectionError()
            }
        }

    }

    private fun displayRocketLunchesList() {
        binding.rocketLaunchList.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
    }

    private fun displayProgressbar() {
        binding.progressBar.visibility = View.VISIBLE
        binding.rocketLaunchList.visibility = View.GONE
    }

    private fun displayConnectionError() {
        Toast.makeText(context, R.string.network_error_message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
