package com.example.cardvisit.ui.activity.master

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.cardvisit.`object`.Command
import com.example.cardvisit.`object`.SingleLiveEvent
import com.example.cardvisit.data.entity.Card
import com.example.cardvisit.repo.CardRepo
import com.example.cardvisit.util.LogUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MasterVM @Inject constructor(private val _cardRepo: CardRepo, private val _logUtil: LogUtil)
    : ViewModel() {

    private val _command: SingleLiveEvent<Command> = SingleLiveEvent()
    val command = _command
    private fun setCommand(cmd: Command) = viewModelScope.launch(Dispatchers.Main) {
        command.value = cmd
    }

    private val _searchStrFlow = MutableStateFlow("")

    private val _lstCard = _cardRepo.getAllCardInDbAsFlow()
        .combine(_cardRepo.getRemoteCardAsFlow()) { lstLocal, lstRemote ->
            mutableListOf<Card>().apply {
                addAll(lstLocal)
                addAll(lstRemote)
            }
        }
        .combine(_searchStrFlow) { lst, search ->
            if (search.isNotEmpty())
                lst.filter {
                    it.name.contains(search, true) ||
                            (it.mobile?.contains(search, true) ?: false)
                }
            else
                lst
        }
        .distinctUntilChanged()
        .catch { e -> _logUtil.d(e.localizedMessage) }
        .flowOn(Dispatchers.IO)
        .asLiveData(viewModelScope.coroutineContext)
    val lstCard = _lstCard

    fun onSearchQueryChange(query: String) {
        _searchStrFlow.value = query
    }

    fun onClickMaster(v: View, item: Card, isTablet: Boolean) {
        setCommand(
            if (isTablet) Command.UpdateDetailFragment(item)
            else Command.OpenDetailActivity(item)
        )
    }

    fun onClickSave(name: String, mobile: String?, company: String?, pos: String?, addr: String?,
        gender: String?, dob: String?, about: String?) {
        viewModelScope.launch(Dispatchers.IO){
            _cardRepo.createNewCard(Card(name = name, mobile = mobile, company = company,
                position = pos, address = addr, gender = gender, dob = dob, about = about))
            delay(300)
            setCommand(Command.ScrollMasterToTop())
        }
    }
}