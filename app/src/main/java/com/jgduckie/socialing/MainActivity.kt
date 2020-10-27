package com.jgduckie.socialing

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.*
import com.jgduckie.socialing.model.UserModel
import com.jgduckie.socialing.model.user.UserAuthDto
import com.jgduckie.socialing.model.user.UserDataResponse
import com.jgduckie.socialing.model.user.UserDto

import com.jgduckie.socialing.utils.ApiUtils
import com.jgduckie.socialing.utils.HOST
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var bntRegisterUser: Button
    private lateinit var nameUser: EditText
    private lateinit var passUser: EditText
    private lateinit var btnLogin: Button
    private lateinit var progress: ProgressBar
    private lateinit var avatar: ImageView
    private lateinit var urlAvatar: String
    private lateinit var recordCheck: CheckBox

    val apiUtils = ApiUtils()

    companion object {

        val token = "token"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        init()

        loading(nameUser, passUser, avatar)



        btnLogin.setOnClickListener {
        var callBack :UserDataResponse
        apiUtils.loginUser(UserAuthDto(nameUser.text.toString(), passUser.text.toString()),{


     val user = it?.user
            if(it?.jwt==null){
                showToast("El usuario no es correcto")
            }
            else{
                if (recordCheck.isChecked) {
                    savePreferences(user, token)

                }
                var intent = Intent(this, Home::class.java)
                startActivity(intent)
            }
            if(it==null){
                showToast("Los campos no pueden estar vacios")
            }

        })
        }


        bntRegisterUser.setOnClickListener {
            var intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)

        }


    }




    /**
     * Se encarga del Binding de los componentes del layout
     */
    fun init() {
        btnLogin = findViewById<Button>(R.id._btnEnter)
        bntRegisterUser = findViewById<Button>(R.id._btnLoginOlvidado)
        nameUser = findViewById<EditText>(R.id.__txtNameUser)
        passUser = findViewById<EditText>(R.id._txtPassUser)
        avatar = findViewById<ImageView>(R.id.avatar)
        recordCheck = findViewById<CheckBox>(R.id._recordarCheck)
    }

    /**
     * Se encarga de guardar las preferencias de login
     * @param necesita como parametro de entrada un objeto Usermodel
     */
    fun savePreferences(user: UserDto?,token:String?) {
        val prefes = getSharedPreferences("MisPreferenciasApp", AppCompatActivity.MODE_PRIVATE)
        val editor = prefes.edit()
        editor.apply {
            putString("TOKEN", token)
            putString("_IDUSER", user?._id)
            putString("NAMEUSER", user?.username)
            putString("PASS", user?.pass)
            putString("AVATAR", HOST().endPoint+user?.avatar?.url)
            putBoolean("RECORD", true)
        }.apply()
    }

    /**
     * Se encarga de cargar las preferencias de loguin guardadas y retornar un objeto UserModel
     */
    fun loadPreferences(): UserModel {
        val prefes = getSharedPreferences("MisPreferenciasApp", AppCompatActivity.MODE_PRIVATE)
        recordCheck.isChecked = prefes.getBoolean("RECORD", false)
        return UserModel(
            prefes.getString("_IDUSER", "").toString(),
            prefes.getString("NAMEUSER", "").toString(),
            prefes.getString("PASS", "").toString(),
            prefes.getString("TOKEN", "").toString(),
            prefes.getString("AVATAR", "").toString()
        )
    }

    /**
     * Verifica si existen preferencias guardadas para mostrarlas en el login
     */
    fun checkPreferences(usertxt: EditText, passtxt: EditText, image: ImageView) {
        if (getSharedPreferences(
                "MisPreferenciasApp",
                AppCompatActivity.MODE_PRIVATE
            ).contains("TOKEN")
        ) {//Si no existen las preferencias
            val user = loadPreferences()
            usertxt
                .setText(user.name)
            passtxt
                .setText(user.pass)
            Picasso.get().load(user.avatar).into(avatar)
        }
    }

    fun loading(usertxt: EditText, passtxt: EditText, image: ImageView) {
        GlobalScope.launch(Dispatchers.Main) {
            // progress.visibility = View.VISIBLE
            Toast.makeText(
                this@MainActivity,
                "Descargando datos.. \n Porfavor espere ",
                Toast.LENGTH_LONG
            ).show()
            checkPreferences(usertxt, passtxt, image)
        }
    }

    fun console(mensaje: String) {
        Log.i("Mensaje Consola", mensaje)
    }


    fun showToast(mensaje: String?){
        Toast.makeText(this@MainActivity,mensaje,Toast.LENGTH_LONG).show()
    }

}




