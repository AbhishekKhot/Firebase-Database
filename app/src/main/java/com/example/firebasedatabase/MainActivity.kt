package com.example.firebasedatabase

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val db = FirebaseDatabase.getInstance()
    val root = db.reference.child("Users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButtonSaveData.setOnClickListener { v ->
            val name = EditTextName.text.toString()
            val email = EditTextEmail.text.toString()
            val surname = EditTextSurname.text.toString()

            val user_map: HashMap<String, String> = HashMap()

            user_map["name"] = name
            user_map["surname"] = surname
            user_map["email"] = email

            root.push().setValue(user_map).addOnCompleteListener {
                Snackbar.make(v,
                    "User data successfully saved to RealTime database",
                    Snackbar.LENGTH_SHORT).show()
            }
        }

        ButtonShowData.setOnClickListener {
            startActivity(Intent(this,ShowAllDataActivity::class.java))
        }
    }
}