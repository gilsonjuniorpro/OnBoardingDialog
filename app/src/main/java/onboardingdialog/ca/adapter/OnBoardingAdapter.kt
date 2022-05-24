package onboardingdialog.ca.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import onboardingdialog.ca.databinding.ItemOnboardingBinding
import onboardingdialog.ca.model.Content
import onboardingdialog.ca.ui.SecondActivity

class OnBoardingAdapter : ListAdapter<Content,
        OnBoardingAdapter.EmpowerViewHolder>(ContentCallBack()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpowerViewHolder {
        return EmpowerViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: EmpowerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class EmpowerViewHolder private constructor(private val binding: ItemOnboardingBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(content: Content){
            binding.tvText.text = content.text
            if(content.isLast){
                binding.btOpenActivity.visibility = View.VISIBLE
            }else{
                binding.btOpenActivity.visibility = View.GONE
            }
            binding.btOpenActivity.setOnClickListener {
                val intent = Intent(binding.cardBase.context, SecondActivity::class.java)
                binding.cardBase.context.startActivity(intent)
            }
        }

        companion object {
            fun from(parent: ViewGroup): EmpowerViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemOnboardingBinding.inflate(layoutInflater, parent, false)
                return EmpowerViewHolder(binding)
            }
        }
    }
}

class ContentCallBack : DiffUtil.ItemCallback<Content>(){
    override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean {
        return oldItem.text == newItem.text
    }

    override fun areContentsTheSame(oldItem: Content, newItem: Content): Boolean {
        return oldItem.text == newItem.text
    }
}
