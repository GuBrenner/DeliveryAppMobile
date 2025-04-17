package br.upf.deliveryapp.data.user

data class User(
    val id : Int = 0 ,
    val email: String,
    val password: String,
    val name: String,
    val phone: String,
    val userType : Int = 0  // usuario padrao será 0 e o administrador será 1

)