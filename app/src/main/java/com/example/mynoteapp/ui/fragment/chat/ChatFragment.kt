package com.example.mynoteapp.ui.fragment.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.util.query
import com.example.mynoteapp.databinding.FragmentChatBinding
import com.example.mynoteapp.ui.adapter.ChatAdapter
import com.google.firebase.Firebase
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.firestore


class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private lateinit var listenerRegistration: ListenerRegistration
    private var chatAdapter = ChatAdapter()
    private val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
        val fireStore = db.collection("user")
        listenerRegistration = fireStore.addSnapshotListener { querySnapshot, exception ->
            val userList = mutableListOf<String>()
            querySnapshot?.documents?.forEach { document ->
                val name = document.getString("name")
                name?.let {
                    userList.add(it)
                }
            }
            chatAdapter.submitList(userList)
        }
    }

    private fun initialize() {
        binding.rvChat.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = chatAdapter
        }
    }

    private fun setupListener() {
        binding.btnSend.setOnClickListener {
            val user = hashMapOf(
                "name" to binding.etText.text.toString()
            )
            db.collection("user").add(user).addOnCanceledListener { }
            binding.etText.text.clear()
        }
    }
}