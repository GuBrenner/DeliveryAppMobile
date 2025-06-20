package br.upf.deliveryapp.Domain

data class UserModel(
    val name: String,
    val email: String,
    val password: String,
    val phone: String,
    val userType: String
)