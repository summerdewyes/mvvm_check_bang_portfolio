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
import com.summerdewyes.mvvm_check_bang.adapter.BookAdapter
import com.summerdewyes.mvvm_check_bang.databinding.FragmentBookmarkBinding
import com.summerdewyes.mvvm_check_bang.ui.viewModel.BookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

        viewModel.getSavedBook().observe(viewLifecycleOwner, { bookItems ->
            bookAdapter.submitList(bookItems)
        })

        bookAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("bookItem", it)
            }
            findNavController().navigate(
                R.id.action_bookmarkFragment_to_bookViewFragment,
                bundle
            )
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
            val book = bookAdapter.currentList[position]
            viewModel.deleteBook(book)
            Snackbar.make(requireView(), "?????????????????? :)", Snackbar.LENGTH_LONG).apply {
                setAction("Undo") {
                    viewModel.saveBook(book)
                }
                show()
            }
        }
    }

    private fun setupRecyclerView() {
        bookAdapter = BookAdapter()
        binding.rvBookmark.apply {
            adapter = bookAdapter
            layoutManager = LinearLayoutManager(activity)
            ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(this)
        }
    }

}