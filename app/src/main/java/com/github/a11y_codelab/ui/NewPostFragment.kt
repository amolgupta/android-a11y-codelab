package com.github.a11y_codelab.ui

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.Toolbar
import com.github.a11y_codelab.R

class NewPostFragment : Fragment() {

    private val editText: AppCompatEditText by lazy { requireView().findViewById<AppCompatEditText>(R.id.post_content) }
    private val button : Button by lazy { requireView().findViewById<Button>(R.id.new_post_button) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Toolbar>(R.id.toolbar).apply {
            setNavigationIcon(R.drawable.ic_baseline_close_24)
        }
        button.setOnClickListener { requireActivity().onBackPressed() }
        editText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                button.requestFocus()
                return@setOnEditorActionListener true
            }
            false
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = NewPostFragment()
    }
}