package com.example.projeman.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projeman.adapters.MembersListItemsAdapter
import com.example.projeman.databinding.DialogListBinding
import com.example.projeman.models.User

abstract class MembersListDialog(
    context: Context,
    private var list: ArrayList<User>,
    private val title: String = ""
) : Dialog(context) {

    private var adapter: MembersListItemsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DialogListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCanceledOnTouchOutside(true)
        setCancelable(true)
        setUpRecyclerView(binding)
    }

    private fun setUpRecyclerView(binding: DialogListBinding) {
        binding.tvTitle.text = title

        if (list.size > 0) {

        }

        binding.rvList.layoutManager = LinearLayoutManager(context)
        adapter = MembersListItemsAdapter(context, list)
        binding.rvList.adapter = adapter

        adapter!!.setOnClickListener(object : MembersListItemsAdapter.OnClickListener {
            override fun onClick(position: Int, user: User, action: String) {
                dismiss()
                onItemSelected(user, action)
            }
        })
    }

    protected abstract fun onItemSelected(user: User, action: String)
}