package com.summerdewyes.mvvm_check_bang.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.summerdewyes.mvvm_check_bang.R
import com.summerdewyes.mvvm_check_bang.databinding.FragmentBookViewBinding
import com.summerdewyes.mvvm_check_bang.ui.viewModel.BookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookViewFragment : Fragment(R.layout.fragment_book_view) {

    private var _binding: FragmentBookViewBinding? = null
    private val binding get() = _binding!!

    val args: BookViewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bookItem = args.bookItem

        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(bookItem.link)
        }

    }


}