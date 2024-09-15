package com.example.myapplication
import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() { //MyListFragment.Listener
    private val ACTIVITY_RECOGNITION_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById<View>(R.id.toolbar_main) as androidx.appcompat.widget.Toolbar
        setSupportActionBar(toolbar)

        val drawer: DrawerLayout = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val pagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val pager = findViewById<ViewPager>(R.id.pager)
        pager.adapter = pagerAdapter
        val tabLayout = findViewById<TabLayout>(R.id.tabs)
        tabLayout.setupWithViewPager(pager)
//        if (savedInstanceState == null) {
//            val listFragment = MyListFragment()
//            val sfm = supportFragmentManager.beginTransaction()
//            sfm.replace(R.id.list_fragment_container, listFragment).commit()
//        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACTIVITY_RECOGNITION),
                    ACTIVITY_RECOGNITION_REQUEST_CODE)
            } else {
                initializeInfoFragment()
            }
        } else {
            initializeInfoFragment()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_create_order -> {
                val intent = Intent(this, ActionActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun initializeInfoFragment() {
        val container = findViewById<View>(R.id.info_fragment_container)
        if (container != null) {
            val infoFragment = InfoFragment()
            val sfm = supportFragmentManager.beginTransaction()
            sfm.replace(R.id.info_fragment_container, infoFragment)
            sfm.commit()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == ACTIVITY_RECOGNITION_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                initializeInfoFragment()
            } else {
                Toast.makeText(this, "Activity recognition permission is required to use the step counter.", Toast.LENGTH_SHORT).show()
            }
        }
    }

//    override fun trailChosen(i: Int) {
//        val container = findViewById<View>(R.id.info_fragment_container)
//        if (container != null) {
//            val infoFragment = InfoFragment()
//            val bundle = Bundle()
//            bundle.putString("index", i.toString())
//            infoFragment.arguments = bundle
//            val sfm = supportFragmentManager.beginTransaction()
//            sfm.replace(R.id.info_fragment_container, infoFragment)
//            sfm.commit()
//        } else {
//            val intent = Intent(this, InfoActivity::class.java)
//            intent.putExtra("index", i.toString())
//            startActivity(intent)
//        }
//    }
    class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> context.getString(R.string.home_tab)
                1 -> context.getString(R.string.kat1_tab)
                2 -> context.getString(R.string.kat2_tab)
                else -> null
            }
        }
        override fun getCount(): Int {
            return 3
        }

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> TopFragment()
                1 -> Tab1Fragment()
                2 -> Tab2Fragment()
                else -> throw IllegalStateException("Invalid position $position")
            }
        }

    }
}




