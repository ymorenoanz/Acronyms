package com.yaritzama.acronyms.presentation.acronymmeaninglist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.yaritzama.acronyms.databinding.FragmentMeaningListBinding
import com.yaritzama.acronyms.presentation.acronymmeaninglist.adapters.AcronymsListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentAcronym : Fragment() {
    private lateinit var binding: FragmentMeaningListBinding
    private val adapter by lazy {AcronymsListAdapter()}
    private val args by navArgs<FragmentAcronymArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMeaningListBinding.inflate(inflater, container, false)
        setUpRecyclerView()
        return binding.root
    }

    private fun setUpRecyclerView(){
        binding.rvMeaningList.adapter = adapter
        adapter.submitList(args.acronymMeaning.toList())

       binding.rvMeaningList.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(this.context,
                DividerItemDecoration.VERTICAL))
        }

        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            args.acronym
    }
}