package com.xische.details.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.xische.details.contract.UniversityPresentationContract
import com.xische.details.databinding.FragmentUniversityBinding
import com.xische.details.presentation.viewmodel.UniversityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class UniversityFragment : Fragment() {

    @Inject
    lateinit var universityContract: UniversityPresentationContract

    private lateinit var binding: FragmentUniversityBinding

    private val viewModel by viewModels<UniversityViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentUniversityBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        initObservations()
    }


    private fun initListener() {
        binding.btnRefresh.setOnClickListener {
            universityContract.back()
            setFragmentResult("refresh", Bundle())
        }
    }

    private fun initObservations() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiModel.collectLatest {
                    it?.run {
                        with(binding)
                        {
                            tvName.text = name
                            tvCountry.text = country
                            tvState.text = state
                            tvCountryCode.text = code
                            tvWebPage.text = webPage
                        }
                    }
                }
            }
        }

    }
}