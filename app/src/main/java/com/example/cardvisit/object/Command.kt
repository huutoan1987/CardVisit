package com.example.cardvisit.`object`

import com.example.cardvisit.data.entity.Card

sealed class Command {
    class OpenDetailActivity(val card: Card) : Command()
    class UpdateDetailFragment(val card: Card) : Command()
    class ScrollMasterToTop : Command()
}