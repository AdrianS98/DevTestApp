package com.example.devtestapp.presentation.contactsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.devtestapp.R
import com.example.devtestapp.databinding.FragmentContactListBinding
import com.example.devtestapp.utils.ConnectionLiveData
import com.example.devtestapp.utils.Resource
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactsFragment : Fragment() {

    private var _binding: FragmentContactListBinding? = null
    private val contactsViewModel: ContactsViewModel by viewModels()
    private lateinit var contactsAdapter: ContactsAdapter
    private lateinit var connectionLiveData: ConnectionLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentContactListBinding.inflate(inflater, container, false)
        _binding = binding

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        paintUI()
        fetchContacts()
        contactsViewModel.loadContacts()
        checkNetConnection()
        setupToolbar()
    }

    private fun setupToolbar() {
        val toolbar = requireActivity().findViewById<AppBarLayout>(R.id.appBarLayout)
        toolbar.visibility = View.VISIBLE
    }

    private fun checkNetConnection() {
        connectionLiveData = ConnectionLiveData(requireContext())
        connectionLiveData.observe(viewLifecycleOwner) { isNetworkAvailable ->
            if (isNetworkAvailable){
                contactsViewModel.loadContacts()
            } else {
                observeContacts()
            }
        }

    }

    private fun fetchContacts() {

        contactsViewModel.contacts.observe(viewLifecycleOwner, Observer {
            _binding!!.progress.isVisible = false
            when (it) {

                is Resource.Succes -> {
                    _binding!!.recyclerView.visibility = View.VISIBLE
                    _binding!!.llErrorData.visibility = View.GONE
                    it.data?.let { contacts -> contactsAdapter.refreshList(contacts) }
                }

                is Resource.Error -> {
                    observeContacts()
                    it.message?.let { message -> showError(message) }
                }

                is Resource.Loading -> {
                    _binding!!.recyclerView.visibility = View.VISIBLE
                    _binding!!.llErrorData.visibility = View.GONE
                    _binding!!.progress.isVisible = true
                }

            }
        })
    }

    private fun showError(@StringRes stringRes: Int) {
        Toast.makeText(context, stringRes, Toast.LENGTH_LONG).show()
    }
    private fun observeContacts() {
        contactsViewModel.contacts.observe(viewLifecycleOwner, Observer { contact ->
            if (!contact.data.isNullOrEmpty()) {
                _binding!!.recyclerView.visibility = View.VISIBLE
                _binding!!.llErrorData.visibility = View.GONE

            } else {
                _binding!!.recyclerView.visibility = View.GONE
                _binding!!.llErrorData.visibility = View.VISIBLE
            }
        })
    }

    private fun paintUI() {
        contactsAdapter = ContactsAdapter()
        _binding!!.recyclerView.layoutManager = GridLayoutManager(context,1)
        _binding!!.recyclerView.setHasFixedSize(true)
        _binding!!.recyclerView.adapter = contactsAdapter
        contactsAdapter.onItemClicked = {
            val bundle = Bundle()
            bundle.putParcelable("contact", it)
            findNavController().navigate(
                ContactsFragmentDirections.actionContactsFragmentToContactDetailFragment(
                    it
                )
            )
        }
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}