package br.upf.deliveryapp.data.user

interface UserRepository {
    fun insertUser(user: User): Long
    fun updateUser(user: User): Int
    fun deleteUser(id: Int): Int
    fun getAllUsers(): List<User>

}