package br.upf.deliveryapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.upf.deliveryapp.adapter.CardapioDataAdapter
import br.upf.deliveryapp.data.cardapio.CardapioData
import br.upf.deliveryapp.databinding.ActivityCardapioBinding
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class CardapioActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCardapioBinding.inflate(layoutInflater)
    }

    companion object {
        fun launch(activity: Activity) {
            activity.startActivity(Intent(activity, CardapioActivity::class.java))
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        fetchCardapio()

    }

    private fun fetchCardapio() {
        val cardapioList = mutableListOf<CardapioData>()
        Firebase.database.getReference("cardapio").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children) {
                    val cardapio = item.getValue(CardapioData::class.java)
                    if (cardapio != null) {
                        cardapioList.add(cardapio)
                    }
                    val adapter = CardapioDataAdapter(this@CardapioActivity, cardapioList)
                    binding.recyclerView.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@CardapioActivity, error.message, Toast.LENGTH_SHORT).show()
            }
        })

    }
}