package com.kaedenoki.moviecorner.data.general

data class Home(
    val list: List<ItemHome>? = listOf()
)

data class ItemHome(
    // banner, item, title
    val type: Int,
    val data: Any
)
