package com.example.ecomadmin.models

import com.example.ecomadmin.R

data class DashboardItem(
    val icon:Int,
    val title:String
)

val dashboardItemList = listOf<DashboardItem>(
    DashboardItem(icon = R.drawable.ic_baseline_add_24, title = "Add Product"),
    DashboardItem(icon = R.drawable.ic_baseline_list_24, title = "All Products"),
    DashboardItem(icon = R.drawable.ic_baseline_monetization_on_24, title = "Order"),
    DashboardItem(icon = R.drawable.ic_baseline_category_24, title = "Category"),
    DashboardItem(icon = R.drawable.ic_baseline_insert_chart_24, title = "Report"),
    DashboardItem(icon = R.drawable.ic_baseline_supervised_user_circle_24, title = "Manage Users"),
    DashboardItem(icon = R.drawable.ic_baseline_settings_24, title = "Settings")
)
