package com.example.cardvisit.ui.activity.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.cardvisit.R
import com.example.cardvisit.data.entity.Card
import com.example.cardvisit.databinding.ActivityDetailBinding
import com.example.cardvisit.ui.fragment.detail.DetailFrg
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    companion object {
        fun newIntent(ctx: Context, card: Card) = Intent(ctx, DetailActivity::class.java).apply {
            putExtra(DetailFrg.ARG_CARD, card)
        }
    }

    private val _detailVM: DetailVM by viewModels()
    private val _card by lazy { intent.getParcelableExtra<Card>(DetailFrg.ARG_CARD) }
    private lateinit var _binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _binding.toolbar.title = "CARD DETAILS"
        setSupportActionBar(_binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            _card?.let {
                supportFragmentManager.beginTransaction()
                    .add(R.id.item_detail_container, DetailFrg.newInstance(it))
                    .commit()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}