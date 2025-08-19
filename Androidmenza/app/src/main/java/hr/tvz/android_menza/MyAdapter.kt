package hr.tvz.android_menza

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView

class MyAdapter(private val menzaList : MutableList<Menza>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private lateinit var mListener : onItemClickListener

    interface onItemClickListener{

        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){

        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView, mListener)
    }

    override fun getItemCount(): Int {
        return menzaList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = menzaList[position]

        //holder.titleImage.setImageResource(currentItem.title_image)

        //Glide.with(holder.itemView.context).load(currentItem.naziv).into(holder.nazivMenze)
        holder.naziv.text = currentItem.naziv
        holder.adresa.text = currentItem.adresa
    }

    class MyViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){

        val naziv : TextView = itemView.findViewById(R.id.nazivMenzeTV)
        val adresa : TextView = itemView.findViewById(R.id.adresaMenzeTV)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}