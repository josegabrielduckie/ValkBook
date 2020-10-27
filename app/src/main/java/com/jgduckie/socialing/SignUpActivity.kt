package com.jgduckie.socialing

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.jgduckie.socialing.model.user.Error.ErrorUser
import com.jgduckie.socialing.model.user.SingUserDto

import com.jgduckie.socialing.utils.ApiUtils

class SignUpActivity : AppCompatActivity() {
    private lateinit var imagem: ImageView
    private lateinit var nickTxt: EditText
    private lateinit var name: EditText
    private lateinit var apellidos: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var repPassword: EditText
    private lateinit var registerBtn: Button

    val REQUEST_IMAGE_CAPTURE = 1

    val apiUtils = ApiUtils()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        init()




        imagem.setOnClickListener {
            dispatchTakePictureIntent()


        }

        registerBtn.setOnClickListener {
            var singUser = SingUserDto(
                nickTxt.text.toString(),
                email.text.toString(),
                password.text.toString(),
                name.text.toString(),
                apellidos.text.toString(),
            )
            val userError = ErrorUser(singUser)
            var errores = userError.Check(repPassword.text.toString())

            if(errores ==null){
                apiUtils.signUpUser(singUser) {
                   if(it==null){
                       showToast("El email ya esta siendo usado por otro usuario")
                   }
                    else{
                       showToast("Usuario creado satisfactoriamente!! ")
                   }
                }
            }else{
                showToast(errores)
            }





        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imagem.setImageBitmap(imageBitmap)
        }
    }

    fun init() {
        imagem = findViewById(R.id.avatar)
        nickTxt = findViewById(R.id._nickName)
        name = findViewById(R.id._nameUser)
        apellidos = findViewById(R.id._apellidosUser)
        email = findViewById(R.id._email)
        password = findViewById(R.id._password)
        repPassword = findViewById(R.id._comfirmar_pass)
        registerBtn = findViewById(R.id.btnEnviarRegistro)

    }


    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    private fun showToast(menssge: String?) {
        Toast.makeText(this@SignUpActivity, menssge, Toast.LENGTH_LONG).show()
    }
}