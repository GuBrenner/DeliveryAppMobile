package br.upf.deliveryapp.data.user



class UserRepositorySQLiteImpl (private val dbHelper: UserDatabaseHelper) : UserRepository {

    override fun insertUser(user: User): Long {
        return dbHelper.insertUser(user)
    }

    override fun updateUser(user: User): Int {
        return dbHelper.updateUser(user)
    }

    override fun deleteUser(id: Int): Int {
        return dbHelper.deleteUser(id)
    }

    override fun getAllUsers(): List<User> {
        return dbHelper.getAllUsers()
    }
}