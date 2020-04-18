package id.buaja.pagination

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.buaja.pagination.util.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val sectionsPagerAdapter = SectionsPagerAdapter(
            supportFragmentManager
        )
        viewPager.adapter = sectionsPagerAdapter
        viewPager.currentItem = 0

        navView.setOnNavigationItemSelectedListener {
            return@setOnNavigationItemSelectedListener when(it.itemId) {
                R.id.navigation_home -> {
                    viewPager.currentItem = 0
                    true
                }

                R.id.navigation_dashboard -> {
                    viewPager.currentItem = 1
                    true
                }

                R.id.navigation_notifications -> {
                    viewPager.currentItem = 2
                    true
                }

                else -> {
                    false
                }
            }
        }
    }
}
