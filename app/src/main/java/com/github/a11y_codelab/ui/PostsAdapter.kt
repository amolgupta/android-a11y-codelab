package com.github.a11y_codelab.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.a11y_codelab.R
import com.github.a11y_codelab.model.Post

class PostsAdapter(private val posts: List<Post>) : ListAdapter<Post, PostsAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = posts.size

    override fun getItem(position: Int) = posts[position]

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position));
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Post) {
            view.findViewById<TextView>(R.id.user).text = item.username
            view.findViewById<TextView>(R.id.content).text = item.content
            item.image?.let {
                view.findViewById<ImageView>(R.id.image).apply {
                    setImageDrawable(ResourcesCompat.getDrawable(view.context.resources, it, null))
                    contentDescription = item.contentDescription
                }
            }
            view.findViewById<Button>(R.id.btn_comment).contentDescription = "Comment on post by ${item.username}"
            view.findViewById<Button>(R.id.btn_like).contentDescription = "Like this post by ${item.username}"
        }
    }
}

val DIFF_CALLBACK: DiffUtil.ItemCallback<Post> = object : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(
        @NonNull oldPost: Post, @NonNull newPost: Post
    ): Boolean {
        return oldPost.id === newPost.id
    }

    override fun areContentsTheSame(
        @NonNull oldPost: Post, @NonNull newPost: Post
    ): Boolean {
        return oldPost == newPost
    }
}

