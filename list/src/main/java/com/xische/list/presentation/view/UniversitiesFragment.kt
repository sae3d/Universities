package com.xische.list.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.xische.common.misc.setVisibility
import com.xische.list.contract.UniversitiesPresentationContract

import com.xische.list.databinding.FragmentUniversitiesBinding
import com.xische.list.presentation.viewmodel.UniversitiesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class UniversitiesFragment : Fragment() {

    @Inject
    lateinit var contract: UniversitiesPresentationContract

    private lateinit var binding: FragmentUniversitiesBinding

    private val viewModel by viewModels<UniversitiesViewModel>()

    private val adapter by lazy {
        UniversityListAdapter { contract.onUniversityClick(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentUniversitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListener()
        initObservations()
        setFragmentResultListener("refresh") { _, _ ->
            viewModel.getUniversities()
        }
    }


    private fun initViews() = with(binding) {
        rvUniversities.addItemDecoration(
            DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        )
        rvUniversities.adapter = adapter
    }

    private fun initListener() {
        binding.btnRetry.setOnClickListener {
            viewModel.getUniversities()
        }
    }

    private fun initObservations() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiModel.collectLatest {
                    with(it)
                    {

                        binding.pbLoading.setVisibility(loading)
                        binding.rvUniversities.setVisibility(!loading && !error)
                        binding.layoutErrorView.setVisibility(error)
                        adapter.submitList(data)
                    }
                }
            }
        }

    }
}

