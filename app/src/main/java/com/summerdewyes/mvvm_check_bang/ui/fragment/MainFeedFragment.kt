package com.summerdewyes.mvvm_check_bang.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
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

        binding.ivAddFeed.setOnClickListener {
            findNavController().navigate(R.id.bookSearchFragment)
        }
    }

    private val itemTouchHelperCallback = object  : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.layoutPosition
            val feed = feedAdapter.currentList[position]
            viewModel.deleteFeed(feed)
            Snackbar.make(requireView(), "삭제했습니다 :)", Snackbar.LENGTH_LONG).apply {
                setAction("Undo") {
                    viewModel.upsertFeed(feed)
                }
                show()
            }
        }
    }

    private fun setupRecyclerView() {
        feedAdapter = FeedAdapter()
        binding.rvMainFeed.apply {
            adapter = feedAdapter
            layoutManager = LinearLayoutManager(activity)
            ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(this)
        }
    }
}