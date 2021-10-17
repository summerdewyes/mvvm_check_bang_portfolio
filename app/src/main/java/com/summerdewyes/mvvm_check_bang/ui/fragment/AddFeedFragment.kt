package com.summerdewyes.mvvm_check_bang.ui.fragment


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.summerdewyes.mvvm_check_bang.R
import com.summerdewyes.mvvm_check_bang.databinding.FragmentAddFeedBinding
import android.os.Environment
import java.io.File
import androidx.core.content.FileProvider
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.summerdewyes.mvvm_check_bang.models.Feed
import com.summerdewyes.mvvm_check_bang.ui.viewModel.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddFeedFragment : Fragment(R.layout.fragment_add_feed) {

    private var _binding: FragmentAddFeedBinding? = null
    private val binding get() = _binding!!

    lateinit var filePath: String
    private val viewModel: FeedViewModel by viewModels()

    val TAG = "AddFeedFragment"

    var bitmap: Bitmap? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivGallery.setOnClickListener {
            galleryLauncher()
        }

        binding.ivCamera.setOnClickListener {
            cameraLauncher()
        }

        binding.tvNext.setOnClickListener {
            val save = saveToFeedDb()
            if (save){
                findNavController().navigate(R.id.action_addFeedFragment_to_mainFeedFragment)
            } else {
                Snackbar.make(requireView(), "페이지와 문구를 모두 입력해주세요 :)", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveToFeedDb() : Boolean {
        val name = "test"
        val timestamp = Calendar.getInstance().timeInMillis
        val page = binding.etPage.text.toString()
        val content = binding.etContent.text.toString()
        val feed = Feed(name, timestamp, page, content, bitmap!!)

        if (page.isNotEmpty() && content.isNotEmpty() && bitmap != null){
            viewModel.upsertFeed(feed)

            return true
        }

        return false
    }

    private fun cameraLauncher() {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val file = File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        )
        filePath = file.absolutePath
        val photoURI: Uri = FileProvider.getUriForFile(
            activity!!,
            "com.summerdewyes.mvvm_check_bang", file
        )
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        cameraResultLauncher.launch(intent)
    }

    var cameraResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val calRatio = calculateInSampleSize(
                    Uri.fromFile(File(filePath)),
                    resources.getDimensionPixelSize(R.dimen.photo_image_height),
                    resources.getDimensionPixelSize(R.dimen.photo_image_height)
                )
                val option = BitmapFactory.Options()
                option.inSampleSize = calRatio
                bitmap = BitmapFactory.decodeFile(filePath, option)
                bitmap?.let {
                    binding.ivPhotoImage.setImageBitmap(bitmap)
                }
            }
        }

    private fun galleryLauncher() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        galleryResultLauncher.launch(intent)
    }

    var galleryResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                try {
                    val calRatio = calculateInSampleSize(
                        data!!.data!!,
                        resources.getDimensionPixelSize(R.dimen.photo_image_height),
                        resources.getDimensionPixelSize(R.dimen.photo_image_height)
                    )
                    val option = BitmapFactory.Options()
                    option.inSampleSize = calRatio
                    var inputStream = activity?.contentResolver?.openInputStream(data!!.data!!)
                    bitmap = BitmapFactory.decodeStream(inputStream, null, option)
                    inputStream!!.close()
                    bitmap?.let {
                        binding.ivPhotoImage.setImageBitmap(bitmap)

                    } ?: let {
                        Log.d(TAG, "bitmap null.............")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }


    private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int): Int {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        try {
            var inputStream = activity?.contentResolver?.openInputStream(fileUri)
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream!!.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1
        if (height > reqHeight || width > reqWidth) {
            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2
            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }

}