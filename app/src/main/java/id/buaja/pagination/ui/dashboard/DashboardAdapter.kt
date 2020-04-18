package id.buaja.pagination.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.buaja.pagination.R
import id.buaja.pagination.network.model.ResultsItem
import kotlinx.android.synthetic.main.item_dashboard_layout.view.*

/**
 * Created By Julsapargi Nursam 4/18/20
 */


class DashboardAdapter(
    private val data: List<ResultsItem>,
    private val listener: (ResultsItem) -> Unit
) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_dashboard_layout, parent, false)
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(data[position], listener)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ResultsItem, listener: (ResultsItem) -> Unit) {
            with(itemView) {
                tvTitle.text = item.title

                setOnClickListener {
                    listener(item)
                }
            }
        }
    }
}