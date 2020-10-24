package com.samir.foodvloggers

import androidx.lifecycle.ViewModelProviders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.samir.foodvloggers.databinding.SignUpFragmentBinding

class SignUp : Fragment() {

    companion object {
        fun newInstance() = SignUp()
    }

    private lateinit var viewModel: SignUpViewModel
    private lateinit var binding: SignUpFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.sign_up_fragment, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SignUpViewModel::class.java)

    }


    private  var asVlogger: Boolean = false
    private var notAsVlogger: Boolean = false

    override fun onStart() {
        super.onStart()

        binding.imVloggerButton?.setOnClickListener {
            it.setBackgroundResource(R.drawable.vlogger_clicked)
            asVlogger = true

            binding.notVloggerButton?.setBackgroundResource(R.drawable.im_not_vlogger_button)
            notAsVlogger = false
        }

        binding.notVloggerButton?.setOnClickListener {
            it.setBackgroundResource(R.drawable.not_vlogger_clicked)
            notAsVlogger = true

            binding.imVloggerButton?.setBackgroundResource(R.drawable.im_a_vlogger_button)
            asVlogger = false
        }
    }

}