package com.example.everis_cartoes.ui.cartoes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.everis_cartoes.R
import com.example.everis_cartoes.data.model.CartoesModel

class CartoesAdapter(
    private val list: List<CartoesModel>
) : RecyclerView.Adapter<CartoesAdapter.CartoesViewHolder>() {


    inner class CartoesViewHolder(
        itemView: View
    ):RecyclerView.ViewHolder(itemView){
        private val nameUser: AppCompatTextView = itemView.findViewById(R.id.name_user)
        private val cardNumber: AppCompatTextView = itemView.findViewById(R.id.card_number)
        private val dateCard: AppCompatTextView = itemView.findViewById(R.id.date_card)
        private val codeCard: AppCompatTextView = itemView.findViewById(R.id.code_card)

        fun bind(cartoes: CartoesModel) {
            nameUser.text = cartoes.name
            cardNumber.text = cartoes.CardNumber
            dateCard.text = cartoes.date
            codeCard.text = cartoes.code
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartoesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cartoes_item, parent, false)
        return CartoesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartoesViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
