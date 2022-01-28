package com.ojanbelajar.aronta.ui.signup

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ojanbelajar.aronta.data.source.Resource
import com.ojanbelajar.aronta.data.source.local.entity.FarmerEntity
import com.ojanbelajar.aronta.data.source.remote.body.Farmer
import com.ojanbelajar.aronta.data.source.remote.body.SignUpBody
import com.ojanbelajar.aronta.databinding.ActivitySignupBinding
import com.ojanbelajar.aronta.ui.home.HomeActivity
import com.ojanbelajar.aronta.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton

@AndroidEntryPoint
class SignUpActivity: AppCompatActivity() {

    lateinit var binding: ActivitySignupBinding
    private var signupReady = false
    lateinit var model: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model =  ViewModelProvider(this).get(SignUpViewModel::class.java)
        onClick()
    }

    fun onClick(){
        binding.btnDaftar.setOnClickListener {
            prepareSignup()
        }
        binding.silahkanMasuk.setOnClickListener {
            finish()
        }
    }
    fun prepareSignup() {
        if(binding.edtName.text.isNullOrEmpty()){
          binding.lnName.error = "Isikan Nama terlebih dahulu"
        } else if (binding.edtEmail.text.isNullOrEmpty()) {
            binding.lnEmail.error = "Isikan email terlebih dahulu"
            signupReady = false
        } else if (binding.edtPassword.text.isNullOrEmpty()) {
            binding.lnPassword.error = "Isikan password terlebih dahulu"
            signupReady = false
        } else if (binding.edtPassword.text.toString().length < 7) {
            binding.lnPassword.error = "Password harus lebih dari 7 karakter"
            signupReady = false
        } else if (binding.edtPasswordConfirmation.text.isNullOrEmpty()) {
            binding.lnPasswordConfirmation.error = "Isikan password terlebih dahulu"
            signupReady = false
        } else if (binding.edtPasswordConfirmation.text.toString().length < 7) {
            binding.lnPasswordConfirmation.error = "Password harus lebih dari 7 karakter"
            signupReady = false
        } else if(binding.edtPassword.text.toString() != binding.edtPasswordConfirmation.text.toString()){

            binding.lnPasswordConfirmation.error = "Password tidak sama"
            signupReady = false
        }else if (binding.edtPhone.text.isNullOrEmpty()) {
            binding.lnEmail.error = "Isikan telepon terlebih dahulu"
            signupReady = false
        }else if (binding.edtAddress.text.isNullOrEmpty()) {
            binding.lnEmail.error = "Isikan alamat terlebih dahulu"
            signupReady = false
        } else {
            signupReady = true
        }

        if (signupReady) {

            val body = SignUpBody(binding.edtEmail.text.toString(),binding.edtPassword.text.toString(),"farmer",
                Farmer(binding.edtName.text.toString(),binding.edtPhone.text.toString(),binding.edtAddress.text.toString())
            )
            model.signUp(body).observe(this,{ result ->
                if (result != null){
                    when(result){
                        is Resource.Loading ->{
                        }
                        is Resource.Success -> {
                            toast("Berhasil membuat akun")
                            finish()

                        }
                        is Resource.Error -> {
                            alert(result.message.toString()) {
                                yesButton {}
                            }.show()
                        }
                    }
                }
            })
        }
    }
}