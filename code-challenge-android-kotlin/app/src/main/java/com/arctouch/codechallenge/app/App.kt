package com.arctouch.codechallenge.app

import android.app.Activity
import android.app.Fragment
import android.app.Service
import androidx.multidex.MultiDexApplication
import com.arctouch.codechallenge.app.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class App : MultiDexApplication(), HasActivityInjector, HasServiceInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingServiceAndroidInjector: DispatchingAndroidInjector<Service>

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>



    override fun onCreate() {
        super.onCreate()

        AppInjector.init(this)
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return
//        }
//        LeakCanary.install(this)


    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun serviceInjector(): AndroidInjector<Service> {
        return dispatchingServiceAndroidInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<androidx.fragment.app.Fragment>? {
        return fragmentDispatchingAndroidInjector
    }

}