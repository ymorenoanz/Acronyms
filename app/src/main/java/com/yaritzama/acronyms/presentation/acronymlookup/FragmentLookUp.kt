package com.yaritzama.acronyms.presentation.acronymlookup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.yaritzama.acronyms.utils.ResponseStatus
import com.yaritzama.acronyms.databinding.FragmentLookupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentLookUp : Fragment() {
    private lateinit var binding: FragmentLookupBinding
    private val _binding get() = binding!!
    private val viewModel: LookUpViewModel by viewModels<LookUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLookupBinding.inflate(inflater, container, false)

        viewModel.data.observe(viewLifecycleOwner) { result ->
            result?.let {
                when(result){
                    is ResponseStatus.Success ->{
                        result.data?.let {
                            view?.findNavController()?.navigate(
                                FragmentLookUpDirections.
                                actionFragmentLookUpToFragmentAcronym(
                                    it.lfs.toTypedArray(), it.sf))
                        }
                    }

                    is ResponseStatus.Loading -> {
                        _binding.pbLoadingData.visibility = View.VISIBLE
                    }

                    is ResponseStatus.Error ->{
                        _binding.pbLoadingData.visibility = View.GONE
                        Snackbar.make(
                            _binding.root,
                            result.message.toString(),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }

                }
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            btnSearchMeaning.setOnClickListener{
                val word = etMeaning.text.toString()
                viewModel.onLookUpClick(word)
            }
        }
    }


}