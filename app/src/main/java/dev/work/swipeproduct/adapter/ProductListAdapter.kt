package dev.work.swipeproduct.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.work.swipeproduct.R
import dev.work.swipeproduct.databinding.ItemProductBinding
import dev.work.swipeproduct.models.ProductDataItem

class ProductListAdapter(
    val context:Context,
    var productList:MutableList<ProductDataItem>
):
    RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>()
{

    class ProductViewHolder(val binding:ItemProductBinding,context: Context):
            RecyclerView.ViewHolder(binding.root){

            }

    override fun onCreateViewHolder(parent:ViewGroup,viewType:Int):ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(layoutInflater, parent, false)

        return ProductViewHolder(binding,parent.context)
    }

    override fun onBindViewHolder(holder:ProductViewHolder,position:Int){

        val product = productList[position]

        holder.binding.tvName.text = "Name: "+product.product_name
        if(product.image.isEmpty()) {
           Picasso.get().load(R.drawable.product).into(holder.binding.ivProduct)
            }
        else{
            Picasso.get().load(product.image).into(holder.binding.ivProduct)
        }
        holder.binding.ivProduct.setImageResource(R.drawable.product)
        holder.binding.tvPrice.text = "Price: "+product.price.toString()
        holder.binding.tvProductType.text = "Category: "+product.product_type
        holder.binding.tvTax.text = "Tax: "+product.tax.toString()

        holder.binding.cvProduct.animation = android.view.animation.AnimationUtils.loadAnimation(
            holder.itemView.context,
            R.anim.setting_anim
        )
    }

    override fun getItemCount():Int{
        return productList.size
    }

    fun setFilteredList(productList: MutableList<ProductDataItem>){
        this.productList = productList
        notifyDataSetChanged()
    }
}