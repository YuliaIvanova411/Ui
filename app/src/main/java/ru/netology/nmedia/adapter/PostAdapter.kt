package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post

typealias OnLikeListener = (Post) -> Unit
typealias OnShareListener = (Post) -> Unit

class PostAdapter(
    private val onLikeClicked: OnLikeListener,
    private val onShareClicked: OnShareListener,
) : ListAdapter<Post, PostViewHolder>(PostDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding =
            CardPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        return PostViewHolder(
            binding = binding,
            onLikeClicked = onLikeClicked,
            onShareClicked = onShareClicked)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}