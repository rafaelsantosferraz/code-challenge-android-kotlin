package com.arctouch.codechallenge.app

import android.os.Bundle
import android.view.View
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.home.presentation.network.TmdbApi
import com.arctouch.codechallenge.base.presentation.ui.BaseActivity
import com.arctouch.codechallenge.home.data.datasources.Cache
import com.arctouch.codechallenge.home.presentation.ui.adapter.HomeAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.home_activity.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)


    }
}
