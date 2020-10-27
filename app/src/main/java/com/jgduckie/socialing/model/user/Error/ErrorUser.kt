package com.jgduckie.socialing.model.user.Error

import com.jgduckie.socialing.model.user.SingUserDto

class ErrorUser(val user : SingUserDto){

    fun Check(checkpass:String):String?{

        if(user.name?.length==0){
            return "El Nick de usuario no puede estar vacio"
        }
        if(user.nombre?.length==0){
            return "El nombre de usuario no puede estar vacio"
        }
        if(user.apellidos?.length==0){
            return "Los apellidos del usuario no puede estar vacio"
        }
        if(user.email?.length==0){
            return "El email del usuario no puede estar vacio"
        }
        if(!user.email.contains("@")){
            return "El email Introducido no es valido"
        }
        if(user.pass?.length==0){
            return "El password del usuario no puede estar vacio"
        }
        if(user.pass?.compareTo(checkpass)!=0){
            return "Las contraseñas introducidas no coinnciden"
        }
        if(user.pass?.length!! < 5 ){
            return "La contraseña introducida no es valida;\n" +
                    " debe tener una longitud de 5 caracteres o mas"
        }



       return null //Si llega aqui no hay errores
    }
}