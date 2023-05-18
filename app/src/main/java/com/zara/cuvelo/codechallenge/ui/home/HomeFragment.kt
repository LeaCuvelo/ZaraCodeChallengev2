package com.zara.cuvelo.codechallenge.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.zara.cuvelo.codechallenge.R
import com.zara.cuvelo.codechallenge.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeFragmentViewModel: HomeViewModel by viewModels()
    private lateinit var charactersAdapter: CharactersAdapter
    private var searchJob: Job? = null

    //region Lifecycle Methods

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_home,
            container,
            false)

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCharacters.layoutManager = LinearLayoutManager(requireContext())

        charactersAdapter = CharactersAdapter()
        binding.rvCharacters.adapter = charactersAdapter

        //Added State Listener to adapter
        charactersAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading)
                binding.pbLoading.isVisible = true
            else {
                binding.pbLoading.isVisible = false
                binding.llErrorLoadingState.isVisible = false
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error ->  loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    binding.llErrorLoadingState.isVisible = true
                    Toast.makeText(context, getString(R.string.home_error_loading_characters), Toast.LENGTH_LONG).show()
                }

            }
        }

        startSearchJob()
    }

    //endregion Lifecycle Methods

    //region Private Methods

    private fun startSearchJob() {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            homeFragmentViewModel.getCharacters()
                .collectLatest {
                    charactersAdapter.submitData(it)
                }
        }
    }

    //endregion Private Methods


}