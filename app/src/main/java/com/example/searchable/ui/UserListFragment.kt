package com.example.searchable.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchable.R
import com.example.searchable.adapter.UserAdapter
import com.example.searchable.data.User
import com.example.searchable.databinding.UserListBinding
import com.example.searchable.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class UserListFragment : Fragment(){

    private val userViewModel : UserViewModel by viewModels()

    private val TAG = UserListFragment::class.java.simpleName

    @Inject
    lateinit var mAdapter : UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = UserListBinding.inflate(inflater)
        var mLayoutManager = LinearLayoutManager(activity)
        binding?.rvUsers?.apply {
            layoutManager = mLayoutManager
            itemAnimator = DefaultItemAnimator()

            adapter = mAdapter
        }





        binding.addFab.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.secondFragment)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.Main).launch{
            userViewModel.allUsers.collect { data->
                mAdapter.userList.clear();
                mAdapter.updateList(data as ArrayList<User>)
                mAdapter.notifyDataSetChanged()
            }
        }
    }


}