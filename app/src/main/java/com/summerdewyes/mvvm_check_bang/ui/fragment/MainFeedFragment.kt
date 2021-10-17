package com.summerdewyes.mvvm_check_bang.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.summerdewyes.mvvm_check_bang.R
import com.summerdewyes.mvvm_check_bang.adapter.FeedAdapter
import com.summerdewyes.mvvm_check_bang.databinding.FragmentMainFeedBinding
import com.summerdewyes.mvvm_check_bang.ui.viewModel.BookViewModel
import com.summerdewyes.mvvm_check_bang.ui.viewModel.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFeedFragment : Fragment(R.layout.fragment_main_feed) {

    private var _binding: FragmentMainFeedBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FeedViewModel by viewModels()
    lateinit var feedAdapter: FeedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        viewModel.getSavedFeed().observe(viewLifecycleOwner, {
            feedAdapter.submitList(it)
        })
    }

    private fun setupRecyclerView() {
        feedAdapter = FeedAdapter()
        binding.rvMainFeed.apply {
            adapter = feedAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}