package com.example.mynoteapp.ui.fragment.note

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mynoteapp.App
import com.example.mynoteapp.R
import com.example.mynoteapp.data.model.NoteModel
import com.example.mynoteapp.databinding.FragmentNoteDetailBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class NoteDetailFragment : Fragment() {
    private lateinit var binding: FragmentNoteDetailBinding
    lateinit var currentNote: NoteModel
    var color: Int = 0
    var time = ""
    var date = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bntSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val text = binding.etText.text.toString()
            binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
                val radioButton: RadioButton = when (checkedId) {
                    binding.radio1.id -> binding.radio1
                    binding.radio2.id -> binding.radio2
                    binding.radio3.id -> binding.radio3
                    else -> binding.radio1
                }
                radioButton.let {
                    when (it.tag) {
                        "red" -> color = R.color.red
                        "black" -> color = R.color.black
                        "white" -> color = R.color.white
                    }
                }
            }

            val currentDate = Calendar.getInstance().time
            val dateFormat: DateFormat = SimpleDateFormat("dd MMMM", Locale.getDefault())
            date = dateFormat.format(currentDate)
            val timeFormat: DateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            time = timeFormat.format(currentDate)
            binding.tvTime.text = time
            binding.tvDate.text = date

            setupTextChangedListener()
            checkButtonVisibility()
            initListener()
        }

    }

    private fun initListener() {
        binding.bntSave.setOnClickListener {
            val noteModel = NoteModel(
                id = currentNote.id,
                title = binding.etTitle.text.toString(),
                text = binding.etText.text.toString(),
                color = color,
                time = time,
                date = date
            )
            App.appDatabase?.noteDao()?.update(noteModel)
            findNavController().navigate(
                R.id.noteFragment,
            )
        }
    }

    private fun checkButtonVisibility() {
        val titleText = binding.etTitle.text.toString().trim()
        val text = binding.etText.text.toString().trim()

        binding.etText.visibility = if (titleText.isNotEmpty() && text.isNotEmpty()) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun setupTextChangedListener() {
        binding.etTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?, start: Int, count: Int, after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                checkButtonVisibility()
            }
        })

        binding.etText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?, start: Int, count: Int, after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                checkButtonVisibility()
            }
        })
    }
}