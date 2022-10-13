package com.devcommop.songstune.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.devcommop.songstune.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)//InstallIn ApplicationComponent::class makes all the dependencies declared here to become immortal as long as the Application lives. --> Because the Component basically specifies how long our dependencies should live
object AppModule {

    @Singleton
    @Provides
    fun provideGlideInstance(
        //context: Context  --> We will give context and hilt will give us the Glide singleton instance
        @ApplicationContext context: Context// --> Hilt will automatically insert ApplicationContext here without our need to intervention and provide us the Glide singleton instance
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_image)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
    )
}