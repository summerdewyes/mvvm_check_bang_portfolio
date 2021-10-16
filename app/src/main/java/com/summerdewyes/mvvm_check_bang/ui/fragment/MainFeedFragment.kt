package com.summerdewyes.mvvm_check_bang.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.summerdewyes.mvvm_check_bang.R
import com.summerdewyes.mvvm_check_bang.adapter.BookAdapter
import com.summerdewyes.mvvm_check_bang.adapter.FeedAdapter
import com.summerdewyes.mvvm_check_bang.databinding.FragmentMainFeedBinding


class MainFeedFragment : Fragment(R.layout.fragment_main_feed) {

    lateinit var feedAdapter: FeedAdapter

    private var _binding: FragmentMainFeedBinding? = null
    private val binding get() = _binding!!

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
    }

    private fun setupRecyclerView() {
        feedAdapter = FeedAdapter()
        binding.rvMainFeed.apply {
            adapter = feedAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}