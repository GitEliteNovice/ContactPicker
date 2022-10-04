package com.aryan.contactpicker

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aryan.contactpicker.R
import com.aryan.contactpicker.adapters.Adapter
import com.aryan.fastcontacts_picker.picker.FastContactPickerImpl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var pb_bar: ProgressBar
    lateinit var recyler_view: RecyclerView
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var adapter: Adapter
    lateinit var fetch_contacts: Button
    val MY_PERMISSIONS_REQUEST_READ_CONTACTS = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        recyler_view = findViewById(R.id.recyler_view)
        linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyler_view.layoutManager = linearLayoutManager
        pb_bar = findViewById(R.id.pb_bar)
        fetch_contacts = findViewById(R.id.fetch_contacts)
        fetch_contacts.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                // Permission is not granted
                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_CONTACTS),
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS
                )

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.

            } else {
                displaycontacts()
            }

        }

    }

    fun displaycontacts() {

        pb_bar.visibility = View.VISIBLE
        var obj = FastContactPickerImpl(this);
        GlobalScope.launch(Dispatchers.Main) {
            var contacts = obj.getContacts();
            pb_bar.visibility = View.GONE
            if (contacts != null) {
                adapter = Adapter(this@MainActivity, contacts!!)
                recyler_view.adapter = adapter

            }

        }

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_READ_CONTACTS -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.size > 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay! Do the

                    displaycontacts()

                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }
}