package com.example.noticeboard

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.noticeboard.databinding.AddNoticeBinding
import com.example.noticeboard.CombineViewModel.viewModel

class AddNoticeActivity : AppCompatActivity() {

    private lateinit var binding: AddNoticeBinding
    private lateinit var notice: NoticeData
    private var pictureUri : Uri? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddNoticeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val addBtn = binding.noticeAddBtn
        val returnBtn = binding.returnBtn
        val addPicture = binding.pictureAddBtn
        val delPicture = binding.pictureDeleteBtn
        val inputTitle = binding.noticeTitle
        val inputContent = binding.noticeContent
        val inputName = binding.noticeWriter

        addPicture.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            activityResult.launch(intent)
        }

        delPicture.setOnClickListener{
            pictureUri = R.drawable.whihe_background.getResourceUri(this)
            Glide.with(this)
                .load(R.drawable.ic_launcher_background.getResourceUri(this))
                .override(5000,5000)
                .fitCenter()
                .into(binding.imageContent)
        }

        addBtn.setOnClickListener{
            if (pictureUri == null) {
               pictureUri = R.drawable.whihe_background.getResourceUri(this)
            }
            notice = NoticeData(inputTitle.text.toString(), inputContent.text.toString(), inputName.text.toString(), pictureUri)
            viewModel.addData(notice)
            finish()
        }

        returnBtn.setOnClickListener{
            finish()
        }
    }

    private val activityResult: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == RESULT_OK && it.data != null){
            var uri = it.data!!.data
            pictureUri = uri
            Glide.with(this)
                .load(uri)
                .override(5000,5000)
                .fitCenter()
                .into(binding.imageContent)
        }
    }

    private fun Int.getResourceUri(context: Context): Uri? {
        return context.resources.let {
            Uri.Builder()
                .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                .authority(it.getResourcePackageName(this))		// it : resources, this : ResId(Int)
                .appendPath(it.getResourceTypeName(this))		// it : resources, this : ResId(Int)
                .appendPath(it.getResourceEntryName(this))		// it : resources, this : ResId(Int)
                .build()
        }
    }
}
