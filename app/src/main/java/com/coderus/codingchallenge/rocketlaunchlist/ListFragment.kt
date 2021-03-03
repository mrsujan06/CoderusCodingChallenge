package com.coderus.codingchallenge.rocketlaunchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.coderus.codingchallenge.R
import com.coderus.codingchallenge.database.RocketEntities
import com.coderus.codingchallenge.database.asDomainModel
import com.coderus.codingchallenge.databinding.FragmentListBinding
import com.coderus.codingchallenge.domain.RocketLaunch
import com.coderus.codingchallenge.repository.RocketLaunchRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Fragment to display the list of RocketLaunchJson Launches.
 */

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list), ItemClickListener {

    @Inject
    lateinit var repository: RocketLaunchRepository

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
        setupViewModel()
    }

    private fun setupList() {
        adapter = RocketLaunchListAdapter(requireContext(), this).apply {
            binding.rocketLaunchList.adapter = this
            binding.rocketLaunchList.addItemDecoration(ListItemDecoration(25))
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this, ViewModelFactory(repository)
        ).get(ListViewModel::class.java)

        viewModel.state.observe(viewLifecycleOwner) { state ->

            when (state) {
                is ListViewModel.State.Loading -> displayProgressbar()
                is ListViewModel.State.Success -> {
                    displayRocketLunchesList(state.rocketLaunchList)
                }
                is ListViewModel.State.Error -> displayConnectionError(
                    state.error,
                    state.rocketLaunchList
                )
            }
        }
    }

    private fun displayProgressbar() {
        binding.progressBar.visibility = View.VISIBLE
        binding.titleText.visibility = View.GONE
        binding.subheading.visibility = View.GONE
        binding.rocketLaunchList.visibility = View.GONE
    }

    private fun displayRocketLunchesList(rocketLaunchList: List<RocketEntities>) {
        adapter.submitList(rocketLaunchList.asDomainModel())
        binding.titleText.visibility = View.VISIBLE
        binding.subheading.visibility = View.VISIBLE
        binding.rocketLaunchList.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
    }

    private fun displayConnectionError(error: Exception, rocketLaunchList: List<RocketEntities>) {
        adapter.submitList(rocketLaunchList.asDomainModel())
        binding.progressBar.visibility = View.GONE
        binding.titleText.visibility = View.VISIBLE
        binding.subheading.visibility = View.VISIBLE
        binding.rocketLaunchList.visibility = View.VISIBLE
        Toast.makeText(context, error.localizedMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onRocketClick(rocketLaunch: RocketLaunch) {
        val action = ListFragmentDirections.actionListFragmentToDetailFragment(rocketLaunch)
        findNavController().navigate(action)
    }
}
