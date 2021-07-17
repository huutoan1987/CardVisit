package com.example.cardvisit.ui.fragment.new

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.cardvisit.databinding.FrgNewBinding
import com.example.cardvisit.ui.activity.master.MasterVM

class NewFrg : DialogFragment() {
    private val _masterVM: MasterVM by activityViewModels()
    private var _binding: FrgNewBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FrgNewBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.toolbar?.setNavigationOnClickListener { dismiss() }

        _binding?.btnSave?.setOnClickListener {
            val name = _binding?.edtName?.text?.toString()?.trim()
            if (name.isNullOrEmpty()) {
                Toast.makeText(activity, "Name is required!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            // Validate fields if need before save
            _masterVM.onClickSave(name,
                _binding?.edtMobile?.text.toString().trim(),
                _binding?.edtCompany?.text.toString().trim(),
                _binding?.edtPosition?.text.toString().trim(),
                _binding?.edtAddr?.text.toString().trim(),
                _binding?.edtGender?.text.toString().trim(),
                _binding?.edtDob?.text.toString().trim(),
                _binding?.edtAbout?.text.toString().trim()
            )
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}