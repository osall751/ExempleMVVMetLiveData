package sn.set.exemples.exemplemvvm01.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import sn.set.exemples.exemplemvvm01.R
import sn.set.exemples.exemplemvvm01.api.RetrofitService

class MonAdaptateur(private var rvList: List<RendezVousItem>, var service: RetrofitService) :
    RecyclerView.Adapter<MonAdaptateur.ViewHolder>() {
    var listData: MutableList<RendezVousItem> = rvList as MutableList<RendezVousItem>

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_card_design, parent, false)
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = listData[position]
        holder.idtextView.text = "Identifiant: " + ItemsViewModel.id
        holder.lieutextView.text = "Lieu: " + ItemsViewModel.lieu
        holder.datetextView.text = "Date du Rendez-Vous: " + ItemsViewModel.date
        holder.descriptiontextView.text = ItemsViewModel.description

        holder.bouton.setOnClickListener {
            deleteRV(ItemsViewModel.id, position)
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return listData.size
    }

    fun deleteRV(id: Int, position: Int) {
        GlobalScope.launch {
            listData.removeAt(position)
            service.deleteRendezVousByID(id)
        }
        notifyDataSetChanged()
        notifyItemChanged(id)
        notifyItemRemoved(id)
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val idtextView: TextView = itemView.findViewById(R.id.id_rv)
        val lieutextView: TextView = itemView.findViewById(R.id.lieu_rv)
        val datetextView: TextView = itemView.findViewById(R.id.date_rv)
        val descriptiontextView: TextView = itemView.findViewById(R.id.descrption_rv)

        val bouton = ItemView.findViewById<Button>(R.id.btndelete)

    }
}