package com.vesko.secondproject.view.jobs


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.card.MaterialCardView
import com.vesko.secondproject.R
import com.vesko.secondproject.model.JobPojo

class JobsAdapter( private val context: JobsFragment) : RecyclerView.Adapter<JobsAdapter.ViewHolder>() {

    private val clickHandler: ClickEventHandler = context
    private val jobList = ArrayList<JobPojo>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val companyTextView : TextView = view.findViewById(R.id.company_text_view)
        val titleTextView : TextView = view.findViewById(R.id.title_text_view)
        val companyImageView: ImageView = view.findViewById(R.id.company_image_view)
        val jobCardView : MaterialCardView = view.findViewById(R.id.job_card_view)
    }

    fun setJobList(list : ArrayList<JobPojo>){
        this.jobList.clear()
        this.jobList.addAll(list)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.companyTextView.text = jobList[position].company
        holder.titleTextView.text = jobList[position].title

        holder.jobCardView.setOnClickListener {
            clickHandler.clickView(jobList[position].id)
        }

        Glide.with(holder.companyImageView.context)
            .load(jobList[position].company_logo)
            .apply(RequestOptions().circleCrop())
            .into(holder.companyImageView)
    }

    override fun getItemCount(): Int = jobList.size


    interface ClickEventHandler {
        fun clickView(jobId: String)
    }

}