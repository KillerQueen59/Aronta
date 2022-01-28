package com.ojanbelajar.aronta.ui.profile


import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ojanbelajar.aronta.data.source.Resource
import com.ojanbelajar.aronta.databinding.FragmentHomeBinding
import com.ojanbelajar.aronta.databinding.FragmentProfileBinding
import com.ojanbelajar.aronta.ui.home.HomeActivity
import com.ojanbelajar.aronta.ui.order.OrderDetailActivity
import com.ojanbelajar.aronta.ui.order.OrderDetailViewModel
import com.ojanbelajar.aronta.ui.order.OrderListActivity
import com.ojanbelajar.aronta.utils.SessionManagement
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.yesButton
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@AndroidEntryPoint
class ProfileFragment: Fragment() {
    private var picture: File = File("")

    lateinit var binding: FragmentProfileBinding
    lateinit var session: SessionManagement
    lateinit var model: ProfileViewModel

    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_SELECT_IMAGE_IN_ALBUM = 2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        model =  ViewModelProvider(this).get(ProfileViewModel::class.java)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        session = SessionManagement(requireActivity().applicationContext)
        populateData()
        onClick()
    }

    fun populateData(){
        binding.txtNameFarmer.text = session.user[SessionManagement.KEY_NAME].toString()
        binding.txtEmail.text = session.user[SessionManagement.KEY_EMAIL].toString()
        binding.txtLokasi.text = session.user[SessionManagement.KEY_ALAMAT].toString()
        binding.txtPhone.text = session.user[SessionManagement.KEY_TELEPON].toString()
        Glide.with(requireActivity()).load(session.user[SessionManagement.KEY_GAMBAR].toString()).into(binding.imgDetail)
    }

    fun onClick(){
        binding.btnLogout.setOnClickListener {
            (activity as HomeActivity).logout()
        }
        binding.btnList.setOnClickListener {
            startActivity<OrderListActivity>()
        }
        binding.btnUbahProfile.setOnClickListener {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                takePictureIntent.resolveActivity(activity!!.packageManager).also {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == AppCompatActivity.RESULT_OK) {
            val imageBitmap = data!!.extras!!.get("data") as Bitmap
            picture = createTempFile(imageBitmap)
            upload(picture,imageBitmap)
        } else if (requestCode == REQUEST_SELECT_IMAGE_IN_ALBUM && resultCode == AppCompatActivity.RESULT_OK) {
            val contentURI = data!!.data
            try {
                val imageBitmap = MediaStore.Images.Media.getBitmap(
                    requireActivity().contentResolver,
                    contentURI
                )
                picture = createTempFile(imageBitmap)
                upload(picture,imageBitmap)
            } catch (e: IOException) {
                e.printStackTrace()
                println(e.toString())
            }
        }
    }

    private fun upload(picture: File,image: Bitmap){
        val reqFile = RequestBody.create(MediaType.parse("image/jpeg"), picture)
        val body = MultipartBody.Part.createFormData("picture", picture.name, reqFile)
        model.picture(session.token,body).observe(viewLifecycleOwner, { result ->
            if (result != null){
                when(result){
                    is Resource.Loading ->{

                    }
                    is Resource.Success -> {
                        alert("Berhasil Mengubah Profil") {
                            yesButton {
                                setImageNew(image)
                            }
                        }.show()

                    }
                    is Resource.Error -> {
                        alert(result.message.toString()) {
                            yesButton {
                            }
                        }.show()
                    }
                }
            }
        })

    }

    private fun createTempFile(bitmap: Bitmap): File {
        val file = File(
            requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            System.currentTimeMillis().toString() + "_image.JPEG"
        )

        var compressionConstant = 100
        var bitmapData = getByteArray(bitmap, compressionConstant)

        while ((bitmapData.size) > 500000) {
            when {
                compressionConstant > 50 -> compressionConstant -= 15
                compressionConstant > 25 -> compressionConstant -= 10
                compressionConstant > 10 -> compressionConstant -= 5
                else -> compressionConstant--
            }
            bitmapData = getByteArray(bitmap, compressionConstant)
        }

        try {
            val fos = FileOutputStream(file)
            fos.write(bitmapData)
            fos.flush()
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return file
    }

    private fun getByteArray(bitmap: Bitmap, compressConstant: Int): ByteArray {
        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, compressConstant, bos)
        return bos.toByteArray()
    }

    private fun setImageNew(photo: Bitmap) {
        Glide.with(this)
            .asBitmap()
            .load(photo)
            .into(binding.imgDetail)
    }
}