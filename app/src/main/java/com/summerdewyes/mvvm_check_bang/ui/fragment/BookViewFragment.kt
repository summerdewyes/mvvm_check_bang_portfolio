package com.summerdewyes.mvvm_check_bang.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.summerdewyes.mvvm_check_bang.R
import com.summerdewyes.mvvm_check_bang.databinding.FragmentBookViewBinding
import com.summerdewyes.mvvm_check_bang.databinding.FragmentMainFeedBinding

class BookViewFragment : Fragment(R.layout.fragment_book_view) {

    private var _binding: FragmentBookViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookViewBinding.inflate(inflater, container, false)
        return binding.root
    }

}