package com.example.retrofit.screen.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.retrofit.R
import com.example.retrofit.adapter.PostAdapter
import com.example.retrofit.api.ApiServise
import com.example.retrofit.api.BaseResponse
import com.example.retrofit.model.PostModel
import com.example.retrofit.model.UserModel
import kotlinx.android.synthetic.main.activity_post.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    lateinit var user: UserModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        user = intent.getSerializableExtra("extra_data") as UserModel


        nameBlog.text = user.firstName +  " " + user.lastName
        loadPosts()


        swipe1.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        loadPosts()
    }

    fun loadPosts() {
        swipe1.isRefreshing = true

        ApiServise.apiClint().getPostByUser(user.id).enqueue(object : Callback<BaseResponse<List<PostModel>>>{
            override fun onResponse(
                call: Call<BaseResponse<List<PostModel>>>,
                response: Response<BaseResponse<List<PostModel>>>
            ) {
                swipe1.isRefreshing = false
                recyclerPost.layoutManager = LinearLayoutManager(this@PostActivity)
                recyclerPost.adapter = PostAdapter(response.body()?.data ?: emptyList())
            }

            override fun onFailure(call: Call<BaseResponse<List<PostModel>>>, t: Throwable) {
                swipe1.isRefreshing = false
                Toast.makeText(this@PostActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}