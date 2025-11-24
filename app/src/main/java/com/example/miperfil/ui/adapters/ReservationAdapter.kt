package com.example.miperfil.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.miperfil.R
import com.example.miperfil.data.model.Reservation
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ReservationAdapter : ListAdapter<Reservation, ReservationAdapter.ReservationViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reservation_card, parent, false)
        return ReservationViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ReservationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvReservationTitle: TextView = itemView.findViewById(R.id.tvReservationTitle)
        private val tvReservationDate: TextView = itemView.findViewById(R.id.tvReservationDate)
        private val tvReservationStatus: TextView = itemView.findViewById(R.id.tvReservationStatus)

        private val formatter = SimpleDateFormat("dd MMM yyyy · HH:mm", Locale.getDefault())

        fun bind(reservation: Reservation) {
            tvReservationTitle.text = "${reservation.petName} - ${reservation.serviceName}"
            tvReservationDate.text = formatter.format(Date(reservation.appointmentDate))
            tvReservationStatus.text = reservation.status
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Reservation>() {
            override fun areItemsTheSame(oldItem: Reservation, newItem: Reservation): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Reservation, newItem: Reservation): Boolean = oldItem == newItem
        }
    }
}







