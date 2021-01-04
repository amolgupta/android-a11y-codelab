package com.github.a11y_codelab.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.a11y_codelab.R
import com.github.a11y_codelab.data.getPosts
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainFragment : Fragment() {

    private val postsAdapter = PostsAdapter(getPosts())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.post_list).apply {
            layoutManager =
                LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            this.adapter = postsAdapter
        }

        view.findViewById<FloatingActionButton>(R.id.add_post).setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, NewPostFragment.newInstance())
                .commitNow()
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}

