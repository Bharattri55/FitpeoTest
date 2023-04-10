package com.test.fitpeo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.fitpeo.adapter.AlbumAdapter
import com.test.fitpeo.data.model.AlbumResponse
import com.test.fitpeo.databinding.ActivityMainBinding
import com.test.fitpeo.util.Status
import com.test.fitpeo.viewModel.AlbumViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: AlbumViewModel by viewModels()
    @Inject
    lateinit var adapter: AlbumAdapter

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        setupUI()
        setupObservers()
        Toast.makeText(this, "Activity calling.....", Toast.LENGTH_SHORT).show()
    }

    private fun setupUI() {
        with(activityMainBinding){
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
            recyclerView.adapter = adapter
        }
    }

    private fun setupObservers() {
        viewModel.users.observe(this) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        with(activityMainBinding) {
                            recyclerView.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                        }
                        resource.data?.let { users -> retrieveList(users) }

                    }
                    Status.ERROR -> {
                        with(activityMainBinding) {
                            recyclerView.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                        }
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        with(activityMainBinding) {
                            progressBar.visibility = View.VISIBLE
                            recyclerView.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun retrieveList(users: List<AlbumResponse>) {
        adapter.apply {
            addUsers(users)
            notifyItemChanged(0,users.size)
        }
    }
}