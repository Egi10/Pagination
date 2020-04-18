package id.buaja.pagination.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.buaja.pagination.ui.dashboard.DashboardFragment
import id.buaja.pagination.ui.home.HomeFragment
import id.buaja.pagination.ui.notifications.NotificationsFragment

private val FRAGMENT = arrayOf(
    HomeFragment(),
    DashboardFragment(),
    NotificationsFragment()
)

class SectionsPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return FRAGMENT[position]
    }

    override fun getCount(): Int {
        return FRAGMENT.size
    }
}