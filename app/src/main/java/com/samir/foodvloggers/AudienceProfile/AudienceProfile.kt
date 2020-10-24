package com.samir.foodvloggers.Profile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.samir.foodvloggers.R
import com.samir.foodvloggers.databinding.AudienceProfileFragmentBinding

class AudienceProfile : Fragment() {

    companion object {
        fun newInstance() = AudienceProfile()
    }

    private lateinit var viewModel: AudienceProfileViewModel
    private lateinit var binding: AudienceProfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.audience_profile_fragment, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AudienceProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

}