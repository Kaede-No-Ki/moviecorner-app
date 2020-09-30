package com.kaedenoki.moviecorner.data.general

data class Home(
    val list: List<ItemHome>? = mutableListOf()
)

data class ItemHome(
    // banner, item, title
    val type: Int,
    val data: Any
)
