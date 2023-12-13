package com.example.devtestapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.devtestapp.databinding.FragmentContactDetailBinding
import com.example.devtestapp.databinding.FragmentContactListBinding


class ContactDetailFragment : Fragment() {
    private var _binding: FragmentContactDetailBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentContactDetailBinding.inflate(inflater, container, false)
        _binding = binding
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}