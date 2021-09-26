package com.futurebrains.retrofitfoodexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.futurebrains.retrofitfoodexample.adapter.MyDrinkAdapter
import com.futurebrains.retrofitfoodexample.databinding.ActivityMainBinding
import com.futurebrains.retrofitfoodexample.decoration.SpaceDecoration
import com.futurebrains.retrofitfoodexample.model.User
import com.google.android.material.snackbar.Snackbar

import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {

    lateinit var dref: DatabaseReference
    lateinit var binding: ActivityMainBinding
    lateinit var layoutManager: GridLayoutManager
    lateinit var userarraylist: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        layoutManager = GridLayoutManager(this, 2)
        binding.rvMain.layoutManager = layoutManager
        binding.rvMain.setHasFixedSize(true)
//        binding.rvMain.addItemDecoration(SpaceDecoration())

        userarraylist = arrayListOf()

        getUserData()


    }

    private fun getUserData() {
        dref = FirebaseDatabase.getInstance().getReference("Drink")

        dref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {

                if (p0.exists()) {

                    for (drinkdetail in p0.children) {
                        val user = drinkdetail.getValue(User::class.java)
                        userarraylist.add(user!!)
                    }

                    binding.rvMain.adapter = MyDrinkAdapter(this@MainActivity, userarraylist)
                }else
                    Snackbar.make(binding.rlayoutMain, "Check Your Json DB ", Snackbar.LENGTH_LONG)
                        .show()


            }

            override fun onCancelled(p0: DatabaseError) {
                Snackbar.make(binding.rlayoutMain, p0.message, Snackbar.LENGTH_LONG)
                    .show()
            }

        })
    }


}