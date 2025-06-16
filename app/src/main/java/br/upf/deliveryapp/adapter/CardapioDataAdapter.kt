package br.upf.deliveryapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import br.upf.deliveryapp.R
import br.upf.deliveryapp.data.cardapio.CardapioData

class CardapioDataAdapter(val context: Context, val cardapios:MutableList<CardapioData>) : RecyclerView.Adapter<CardapioDataAdapter.CardapioDataHolder>() {
    class CardapioDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt_titulo = itemView.findViewById<TextView>(R.id.txt_titulo)
        val txt_descricao = itemView.findViewById<TextView>(R.id.txt_descricao)
        val txt_preco = itemView.findViewById<TextView>(R.id.txt_preco)
        val img_cardapio = itemView.findViewById<ImageView>(R.id.img_cardapio_item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardapioDataHolder {
        return CardapioDataHolder(LayoutInflater.from(context).inflate(R.layout.item_cardapio, parent, false ))
    }

    override fun getItemCount(): Int {
        return cardapios.size
    }

    override fun onBindViewHolder(holder: CardapioDataHolder, position: Int) {
        holder.txt_titulo.text = cardapios[position].titulo
        holder.txt_descricao.text = cardapios[position].descricao
        holder.txt_preco.text = cardapios[position].preco


    }
}