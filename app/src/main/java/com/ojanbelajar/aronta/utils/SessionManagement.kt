package com.ojanbelajar.aronta.utils

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.ojanbelajar.aronta.data.source.local.entity.FarmerEntity
import com.ojanbelajar.aronta.ui.login.LoginActivity

class SessionManagement(var context: Context) {

    companion object{
        private val PREF_NAME = "arontaapps"
        private val IS_LOGIN = "IsLoggedIn"
        private val IS_FIRST = "isFirstOpen"

        //farmer
        val KEY_ID = "id"
        val KEY_NAME = "name"
        val KEY_EMAIL = "email"
        val KEY_TELEPON = "telepon"
        val KEY_ALAMAT = "alamat"
        val KEY_GAMBAR = "gambar"
        val KEY_TOKEN = "token"
    }

    var pref: SharedPreferences
    var editor: SharedPreferences.Editor
    var PRIVATE_MODE = 0

    init {
        pref = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE)
        editor = pref.edit()
    }

    val token: String
        get() {
            return pref.getString(KEY_TOKEN,"").toString()
        }

    val user: HashMap<String,String>
        get() {
            val  user = HashMap<String , String>()
            user[KEY_EMAIL] = pref.getString(KEY_EMAIL, "").toString()
            user[KEY_ID] = pref.getString(KEY_ID, "").toString()
            user[KEY_NAME] = pref.getString(KEY_NAME, "").toString()
            user[KEY_TELEPON] = pref.getString(KEY_TELEPON, "").toString()
            user[KEY_ALAMAT] = pref.getString(KEY_ALAMAT, "").toString()
            user[KEY_GAMBAR] = pref.getString(KEY_GAMBAR, "").toString()
            return user
        }

    val isLoggedIn: Boolean
        get() = pref.getBoolean(IS_LOGIN, false)
    val isFirstOpen: Boolean
        get() = pref.getBoolean(IS_FIRST, false)

    fun createLoginSession(result: FarmerEntity,token: String){
        editor.putBoolean(IS_LOGIN,true)
        editor.putString(KEY_ID, result.id)
        editor.putString(KEY_EMAIL, result.email)
        editor.putString(KEY_NAME, result.nama)
        editor.putString(KEY_TELEPON, result.telepon)
        editor.putString(KEY_ALAMAT, result.alamat)
        editor.putString(KEY_GAMBAR, result.image)
        editor.putString(KEY_TOKEN,token)
        editor.commit()
    }

    fun checkLogin(): Boolean = this.isLoggedIn
    fun checkFirst(): Boolean = this.isFirstOpen

    fun logoutUser() {
        editor.remove(IS_LOGIN)

        editor.remove(KEY_ID)
        editor.remove(KEY_EMAIL)
        editor.remove(KEY_NAME)
        editor.remove(KEY_TELEPON)
        editor.remove(KEY_ALAMAT)
        editor.remove(KEY_GAMBAR)
        editor.remove(KEY_TOKEN)
        editor.commit()

        val intent = Intent(context, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)

    }
}