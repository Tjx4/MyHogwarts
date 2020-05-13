package net.sure.myhogwarts.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.sure.myhogwarts.R
import net.sure.myhogwarts.models.Student

class CharactersAdapter(context: Context,  private val layout: Int, private val students: List<Student?>?) : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private var characterClickListener: CharacterClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = students?.get(position)

        holder.characterNameTv.text = character?.name
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        internal var characterNameTv: TextView = itemView.findViewById(R.id.tvCharacterHouse)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            if (characterClickListener != null)
                characterClickListener?.onHouseMemberClick(view, adapterPosition)
        }
    }

    internal fun getItem(id: Int): Student? {
        return students?.get(id)
    }

    fun setClickListener(itemClickListener: CharacterClickListener) {
        this.characterClickListener = itemClickListener
    }

    interface CharacterClickListener {
        fun onHouseMemberClick(view: View, position: Int)
    }

    override fun getItemCount(): Int {
        return students?.size ?: 0
    }
}