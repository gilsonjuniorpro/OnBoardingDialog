package onboardingdialog.ca.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.view.isVisible
import onboardingdialog.ca.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.bt_get_started).setOnClickListener {
            loadData()
        }

        findViewById<ImageView>(R.id.bt_close).setOnClickListener {
            loadData()
        }
    }

    private fun loadData(){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.layout_base, OnBoardingFragment())
        transaction.commit()

        if(findViewById<FrameLayout>(R.id.layout_base).isVisible){
            findViewById<FrameLayout>(R.id.layout_base).visibility = View.GONE
            findViewById<ImageView>(R.id.bt_close).visibility = View.GONE
        }else{
            findViewById<FrameLayout>(R.id.layout_base).visibility = View.VISIBLE
            findViewById<ImageView>(R.id.bt_close).visibility = View.VISIBLE
        }
    }
}
