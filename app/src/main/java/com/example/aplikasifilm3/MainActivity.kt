package com.example.aplikasifilm3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var activeFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Membuat instance dari HomeFragment
        val homeFragment = HomeFragment.newInstance("param1", "param2")

        // Menambahkan HomeFragment ke dalam container di dalam aktivitas (misalnya FrameLayout)
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, homeFragment)
            .commit()

        /* Isi konten */
        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNav)
        bottomNav.setOnNavigationItemSelectedListener(menuItemSelectedListener)
    }

    /* Deteksi Item Menu Yang Diklik */
    private val menuItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val fragment = when (item.itemId) {
            R.id.bottom_home -> HomeFragment.newInstance("test1", "test2")
            R.id.bottom_popular -> PopularFragment.newInstance("test1", "test2")
            R.id.bottom_top -> TopFragment.newInstance("test1", "test2")
            R.id.bottom_upcoming -> UpcomingFragment.newInstance("test1", "test2")
            else -> null
        }
        // Cek apakah fragment yang akan ditambahkan sudah sama dengan fragment yang sudah aktif atau tidak
        if (fragment != activeFragment) {
            addFragment(fragment)
            return@OnNavigationItemSelectedListener true
        }
        return@OnNavigationItemSelectedListener false
    }

    /* Memanggil Fragment ke frame_layout di activity_main */
    private fun addFragment(fragment: Fragment?) {
        fragment?.let {
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                    com.google.android.material.R.anim.design_bottom_sheet_slide_in,
                    com.google.android.material.R.anim.design_bottom_sheet_slide_out
                )
                .replace(R.id.frameLayout, it)
                .commit()
            activeFragment = it
        }
    }
}