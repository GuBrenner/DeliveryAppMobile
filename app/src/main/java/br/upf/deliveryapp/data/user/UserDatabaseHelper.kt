package br.upf.deliveryapp.data.user

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.upf.deliveryapp.data.user.User
import java.lang.reflect.Array.getBoolean

class UserDatabaseHelper(context: Context) : SQLiteOpenHelper(
    context, "deliveryAppDatabase", null, 1
) {
    companion object {
        private const val TABLE_NAME = "tbl_users"
        private const val COLUMN_ID = "id"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PASSWORD = "password"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_PHONE = "phone"
        private const val COLUMN_USER_TYPE = "user_type"

    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,                
                $COLUMN_EMAIL TEXT,
                $COLUMN_PASSWORD TEXT,
                $COLUMN_NAME TEXT,
                $COLUMN_PHONE TEXT,
                $COLUMN_USER_TYPE INTEGER
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertUser(user: User): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_EMAIL, user.email)
            put(COLUMN_PASSWORD, user.password)
            put(COLUMN_NAME, user.name)
            put(COLUMN_PHONE, user.phone)
            put(COLUMN_USER_TYPE, user.userType)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    fun updateUser(user: User): Int {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_EMAIL, user.email)
            put(COLUMN_PASSWORD, user.password)
            put(COLUMN_NAME, user.name)
            put(COLUMN_PHONE, user.phone)
            put(COLUMN_USER_TYPE, user.userType)
        }
        return db.update(TABLE_NAME, values, "$COLUMN_ID = ?", arrayOf(user.id.toString()))
    }

    fun deleteUser(id: Int): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(id.toString()))
        // nao tenho certeza se mantenho essa função de excluir usuario
    }

    fun getAllUsers(): List<User> {
        val users = mutableListOf<User>()
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            "$COLUMN_NAME ASC"
        )

        with(cursor) {
            while (moveToNext()) {
                val user = User(
                    id = getInt(getColumnIndexOrThrow(COLUMN_ID)),
                    email = getString(getColumnIndexOrThrow(COLUMN_EMAIL)),
                    password = getString(getColumnIndexOrThrow(COLUMN_PASSWORD)),
                    name = getString(getColumnIndexOrThrow(COLUMN_NAME)),
                    phone = getString(getColumnIndexOrThrow(COLUMN_PHONE)),
                    userType = getInt(getColumnIndexOrThrow(COLUMN_USER_TYPE)),

                    )
                users.add(user)
            }
        }
        cursor.close()
        return users
    }

    fun authenticate(email: String, password: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_NAME,
            null,
            "$COLUMN_EMAIL = ? AND $COLUMN_PASSWORD = ?",
            arrayOf(email, password),
            null,
            null,
            null
        )

        val result = cursor.moveToFirst()
        cursor.close()
        return result


    }
}

