package br.upf.deliveryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.upf.deliveryapp.data.user.UserData
import br.upf.deliveryapp.databinding.ActivitySignupBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")

        binding.

        }

    private fun signupUser(name: String, email: String, phone: String, password: String){
        databaseReference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(!dataSnapshot.exists()) {
                    val id = databaseReference.push().key
                    val userData = UserData(id, name, email, phone, password)
                    databaseReference.child(id!!).setValue(userData);
                    Toast.makeText(this@SignupActivity, "Signup Successfully", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
                    finish()
                    } else {
                    Toast.makeText(this@SignupActivity, "User already exists", Toast.LENGTH_LONG).show()

                }
                }
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignupActivity, "Database Error: ${databaseError.message}", Toast.LENGTH_LONG).show()
            }
        })
    }


    }


