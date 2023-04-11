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
    val productList:MutableList<ProductDataItem>
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
        val imageUrl:String = Picasso.get().load(product.image).toString()

        holder.binding.tvName.text = product.product_name
        if (imageUrl.isEmpty()) {
            holder.binding.ivProduct.setImageResource(R.drawable.product)
            }

        holder.binding.ivProduct.setImageResource(R.drawable.product)

        holder.binding.tvPrice.text = product.price.toString()
        holder.binding.tvProductType.text = product.product_type
        holder.binding.tvTax.text = product.tax.toString()
    }

    override fun getItemCount():Int{
        return productList.size
    }
}