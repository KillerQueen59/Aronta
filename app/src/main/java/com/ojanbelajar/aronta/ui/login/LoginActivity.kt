package com.ojanbelajar.aronta.ui.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.ojanbelajar.aronta.data.source.Resource
import com.ojanbelajar.aronta.data.source.local.entity.FarmerEntity
import com.ojanbelajar.aronta.databinding.ActivityLoginBinding
import com.ojanbelajar.aronta.ui.home.HomeActivity
import com.ojanbelajar.aronta.ui.signup.SignUpActivity
import com.ojanbelajar.aronta.utils.SessionManagement
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.RequestBody
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton
import org.json.JSONObject


@AndroidEntryPoint
class LoginActivity: AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private var loginReady = true
    lateinit var session: SessionManagement
    lateinit var model : LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        session = SessionManagement(applicationContext)
        if(session.checkLogin()) toHome()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model =  ViewModelProvider(this).get(LoginViewModel::class.java)
        onClick()
    }

    private fun toHome() {
        startActivity<HomeActivity>()
        finish()
    }

    fun onClick(){
        binding.btnLogin.setOnClickListener {
            prepareLogin()
        }
        binding.buatAkun.setOnClickListener {
            startActivity<SignUpActivity>()
        }

    }

    fun prepareLogin(){
        if (binding.edtEmail.text.isNullOrEmpty()){
            binding.lnEmail.error = "Isikan email terlebih dahulu"
            loginReady = false
        } else if (binding.edtPassword.text.isNullOrEmpty()){
            binding.lnPassword.error = "Isikan password terlebih dahulu"
            loginReady = false
        } else if (binding.edtPassword.text.toString().length < 7) {
            binding.lnPassword.error = "Password harus lebih dari 7 karakter"
            loginReady = false
        } else {
            loginReady = true
        }

        if (loginReady){
            val body = createJsonRequestBody("email" to binding.edtEmail.text.toString(),"password" to binding.edtPassword.text.toString())
            model.login(body).observe(this,{ result ->
                if (result != null){
                    when(result){
                        is Resource.Loading ->{
                            binding.progressBar.visibility = View.VISIBLE
                            binding.btnLogin.visibility = View.GONE

                        }
                        is Resource.Success -> {
                            session.createLoginSession(FarmerEntity(result.data!!.user.farmer._id,result.data!!.user.farmer.name,result.data!!.user.email,result.data!!.user.farmer.phoneNumber,result.data!!.user.farmer.address),result.data.token)
                            startActivity<HomeActivity>()
                            binding.progressBar.visibility = View.GONE
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.btnLogin.visibility = View.VISIBLE
                            alert(result.message.toString()) {
                                yesButton {}
                            }.show()
                        }
                    }
                }

            })
        }
    }

    private fun createJsonRequestBody(vararg params: Pair<String, String>) =
        RequestBody.create(
            okhttp3.MediaType.parse("application/json"),
            JSONObject(mapOf(*params)).toString()
        )
}