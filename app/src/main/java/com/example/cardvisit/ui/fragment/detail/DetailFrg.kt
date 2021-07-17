package com.example.cardvisit.ui.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cardvisit.data.entity.Card
import com.example.cardvisit.databinding.FrgDetailBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * A fragment representing a single detail screen.
 * This fragment is either contained in a [MasterActivity]
 * in two-pane mode (on tablets) or a [DetailActivity]
 * on handsets.
 */
@AndroidEntryPoint
class DetailFrg : Fragment() {

    companion object {
        const val ARG_CARD  = "ARG_CARD"

        fun newInstance(card: Card) = DetailFrg().apply {
            arguments = Bundle().apply { putParcelable(ARG_CARD, card) }
        }
    }

    private val _detailFrgVM: DetailFrgVM by viewModels()
    private val _card by lazy { arguments?.getParcelable<Card>(ARG_CARD) }
    private var _binding: FrgDetailBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FrgDetailBinding.inflate(inflater, container, false)
        _binding!!.card = _card
        return _binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}