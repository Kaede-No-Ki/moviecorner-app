package com.kaedenoki.moviecorner.repository.local

import android.content.Context
import com.kaedenoki.moviecorner.helper.Const.HAWK_MODE
import com.orhanobut.hawk.Hawk

object HawkStore {

    private fun init(context: Context) {
        Hawk.init(context).build()
    }

    fun saveMode(context: Context, mode: String){
        init(context)
        Hawk.put(HAWK_MODE, mode)
    }
    fun storeData(
        context: Context,
        key: String,
        data: Any
    ) {
        init(context)
        Hawk.put(key, data)
    }

    fun getData(
        context: Context,
        key: String
    ): String? {
        init(context)
        return Hawk.get(key, null)
    }

    fun deleteData(context: Context, key: String) {
        init(context)
        Hawk.delete(key)
    }

    fun getMode(context: Context): String {
        init(context)
        return Hawk.get(HAWK_MODE, "")
    }
}