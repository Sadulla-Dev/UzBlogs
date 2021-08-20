package com.example.retrofit.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.retrofit.R
import com.example.retrofit.adapter.PostAdapter
import com.example.retrofit.adapter.UserAdapter
import com.example.retrofit.adapter.UserAdapterListener
import com.example.retrofit.api.ApiServise
import com.example.retrofit.api.BaseResponse
import com.example.retrofit.model.PostModel
import com.example.retrofit.model.UserModel
import com.example.retrofit.screen.post.PostActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(),SwipeRefreshLayout.OnRefreshListener {


//    val postList = listOf(
//        PostModel(""),
//        PostModel(""),
//        PostModel(""),
//        PostModel(""),
//        PostModel(""),
//        PostModel(""),
//        PostModel("")
//    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadUsers()
        loadUser2()
        swipe.isRefreshing = true



        swipe.setOnRefreshListener(this)
    }


    fun loadUsers() {
        ApiServise.apiClint().getUsers().enqueue(object : Callback<BaseResponse<List<UserModel>>>{
            override fun onResponse(
                call: Call<BaseResponse<List<UserModel>>>,
                response: Response<BaseResponse<List<UserModel>>>) {
                swipe.isRefreshing = false

                recyclerUser.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                recyclerUser.adapter = UserAdapter(response.body()?.data ?: emptyList(), object : UserAdapterListener{
                    override fun onClick(item: UserModel) {
                        val intent = Intent(this@MainActivity,PostActivity::class.java)
                        intent.putExtra("extra_data", item)
                        startActivity(intent)
                    }
                })

            }

            override fun onFailure(call: Call<BaseResponse<List<UserModel>>>, t: Throwable) {
                swipe.isRefreshing = false
                Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()

            }
        })
    }


    fun loadUser2() {
        ApiServise.apiClint().getPosts().enqueue(object : Callback<BaseResponse<List<PostModel>>>{
            override fun onResponse(
                call: Call<BaseResponse<List<PostModel>>>,
                response: Response<BaseResponse<List<PostModel>>>) {

                recyclerPost.layoutManager = LinearLayoutManager(this@MainActivity)
                recyclerPost.adapter = PostAdapter(response.body()?.data ?: emptyList())

            }
            override fun onFailure(call: Call<BaseResponse<List<PostModel>>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onRefresh() {
        loadUser2()
        loadUsers()
    }

}