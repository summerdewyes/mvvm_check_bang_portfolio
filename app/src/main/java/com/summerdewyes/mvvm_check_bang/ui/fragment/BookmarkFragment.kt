package com.summerdewyes.mvvm_check_bang.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.summerdewyes.mvvm_check_bang.R
import com.summerdewyes.mvvm_check_bang.adapter.BookAdapter
import com.summerdewyes.mvvm_check_bang.databinding.FragmentBookmarkBinding
import com.summerdewyes.mvvm_check_bang.ui.viewModel.BookViewModel

class BookmarkFragment : Fragment(R.layout.fragment_bookmark) {

    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BookViewModel by viewModels()
    lateinit var bookAdapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        bookAdapter = BookAdapter()
        binding.rvBookmark.apply {
            adapter = bookAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}