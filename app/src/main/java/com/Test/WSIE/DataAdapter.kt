package com.Test.WSIE

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.data_list_item.*

class ExtensionDataAdapter(
    val items: ArrayList<Meal>,
    val context: Context,
    val itemSelect: (Meal) -> Unit // 클릭이벤트를 처리하기 위한 람다식
) : RecyclerView.Adapter<ExtensionDataAdapter.ExtensionViewHolder>() {
    //목록 개수를 반환
    override fun getItemCount(): Int {
        return items.size
    }

    //뷰를 띄움
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExtensionViewHolder {
        //마지막 false는 attachToRoot다. 거의 false라 생각하면 된다.
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.data_list_item,
            parent,
            false
        )
        return ExtensionViewHolder(view, itemSelect)
    }

    override fun onBindViewHolder(holder: ExtensionViewHolder, position: Int) {
        holder.bind(items[position], context)
    }

    //고차함수는 Meal형의 인자 한개를 가지고 있다. 선언
    inner class ExtensionViewHolder(override val containerView: View, itemSelect: (Meal) -> Unit) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(meal: Meal, context: Context) {
            //그리드 레이아웃 안의 텍스트 뷰 설정
            tv_data_type.text = meal.foodName.toString()

            //항목이 클릭되면 itemSelect 람다식을 처리
            itemView.setOnClickListener() { itemSelect(meal) }
        }
    }
}

