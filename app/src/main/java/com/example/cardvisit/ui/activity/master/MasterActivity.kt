package com.example.cardvisit.ui.activity.master

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.cardvisit.R
import com.example.cardvisit.`object`.Command
import com.example.cardvisit.databinding.ActivityMasterBinding
import com.example.cardvisit.ui.activity.detail.DetailActivity
import com.example.cardvisit.ui.fragment.detail.DetailFrg
import com.example.cardvisit.ui.fragment.new.NewFrg
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MasterActivity : AppCompatActivity() {

    private var _isTablet: Boolean = false
    private val _masterVM: MasterVM by viewModels()
    private lateinit var _binding: ActivityMasterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMasterBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        setSupportActionBar(_binding.toolbar)

        if (findViewById<FrameLayout>(R.id.item_detail_container) != null) {
            _isTablet = true
        }

        setupView()
        setupObserver()
    }

    private fun setupView() {
        _binding.btnAdd.setOnClickListener { view ->
            NewFrg().apply {
                setStyle(DialogFragment.STYLE_NORMAL,
                    android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen)
            }.show(supportFragmentManager, "NewFrg")
        }

        findViewById<RecyclerView>(R.id.rclv_card).adapter = MasterAdpt(_masterVM, _isTablet)

        _binding.schvCard.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    _masterVM.onSearchQueryChange(query?.trim() ?: "")
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        _binding.schvCard.setOnCloseListener {
            _masterVM.onSearchQueryChange("")
            return@setOnCloseListener false
        }
    }

    private fun setupObserver() {
        _masterVM.lstCard.observe(this, { lst ->
            (findViewById<RecyclerView>(R.id.rclv_card).adapter as MasterAdpt).submitList(lst)
        })
        _masterVM.command.observe(this, { command ->
            when (command) {
                is Command.OpenDetailActivity -> {
                    startActivity(DetailActivity.newIntent(this, command.card))
                }
                is Command.UpdateDetailFragment -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.item_detail_container, DetailFrg.newInstance(command.card))
                        .commit()
                }
                is Command.ScrollMasterToTop -> {
                    findViewById<RecyclerView>(R.id.rclv_card).smoothScrollToPosition(0)
                }
                else -> { }
            }
        })
    }
}