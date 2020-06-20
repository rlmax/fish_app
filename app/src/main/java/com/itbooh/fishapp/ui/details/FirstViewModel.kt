package com.itbooh.fishapp.ui.details

import com.itbooh.fishapp.data.db.AppDatabase
import com.itbooh.fishapp.data.model.Fish
import com.itbooh.fishapp.ui.base.BaseViewModel

class FirstViewModel( val appDatabase: AppDatabase = AppDatabase.getInstance() ) : BaseViewModel() {


    fun  getFishDetails(id: Int?): MutableList<Fish> {
        val fish :MutableList<Fish> = appDatabase.fishDao().loadSingle(id)
        return fish
    }
}