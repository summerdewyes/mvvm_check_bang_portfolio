package com.summerdewyes.mvvm_check_bang.ui.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.summerdewyes.mvvm_check_bang.R
import com.summerdewyes.mvvm_check_bang.adapter.BookAdapter
import com.summerdewyes.mvvm_check_bang.databinding.FragmentBookSearchBinding
import com.summerdewyes.mvvm_check_bang.ui.viewModel.BookViewModel
import com.summerdewyes.mvvm_check_bang.util.Constants.Companion.SEARCH_NEWS_TIME_DELAY
import com.summerdewyes.mvvm_check_bang.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookSearchFragment : Fragment(R.layout.fragment_book_search) {

    private var _binding: FragmentBookSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BookViewModel by viewModels()
    lateinit var bookAdapter: BookAdapter

    val TAG = "BookSearchFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        bookAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("bookItem", it)
            }
            findNavController().navigate(
                R.id.action_bookSearchFragment_to_addFeedFragment,
                bundle
            )
        }

        var job: Job? = null
        binding.etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_NEWS_TIME_DELAY)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.searchBook(editable.toString())
                    }
                }
            }
        }

        viewModel.searchBook.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { bookResponse ->
                        bookAdapter.submitList(bookResponse.items)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        bookAdapter = BookAdapter()
        binding.rvSearchBooks.apply {
            adapter = bookAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}