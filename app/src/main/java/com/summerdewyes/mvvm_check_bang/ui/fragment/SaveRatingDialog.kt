package com.summerdewyes.mvvm_check_bang.ui.fragment

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.summerdewyes.mvvm_check_bang.R
import com.summerdewyes.mvvm_check_bang.databinding.FragmentBookViewBinding
import com.summerdewyes.mvvm_check_bang.databinding.FragmentSaveRatingBinding
import com.summerdewyes.mvvm_check_bang.models.Item
import com.summerdewyes.mvvm_check_bang.ui.viewModel.BookViewModel
import com.summerdewyes.mvvm_check_bang.ui.viewModel.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SaveRatingDialog : DialogFragment(R.layout.fragment_save_rating) {

    private var _binding: FragmentSaveRatingBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BookViewModel by viewModels()
    val args: BookViewFragmentArgs by navArgs()

    private var yesListener: (() -> Unit)? = null
    fun setYesListener(listener: () -> Unit) {
        yesListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSaveRatingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bookItem = args.bookItem
        var item: Item

        binding.rbBook.setOnRatingBarChangeListener { ratingBar, fl, b ->
            item = Item(
                bookItem.id,
                bookItem.author,
                bookItem.description,
                bookItem.discount,
                bookItem.image,
                bookItem.isbn,
                bookItem.link,
                bookItem.price,
                bookItem.pubdate,
                bookItem.publisher,
                bookItem.title,
                fl
            )

            binding.tvSuccess.setOnClickListener {
                viewModel.saveBook(item)
                findNavController().navigate(R.id.action_saveRatingDialog_to_bookmarkFragment)
            }
        }

        binding.tvCancle.setOnClickListener {
            dismiss()
        }

    }

}