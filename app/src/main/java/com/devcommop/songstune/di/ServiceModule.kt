package com.devcommop.songstune.di

import android.content.Context
import com.devcommop.songstune.data.remote.MusicDatabase
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped

@Module
@InstallIn(ServiceComponent::class)//These dependencies will live as long as the Service is alive beacuse we don't want these dependencies to live as long as the Application lives.
object ServiceModule {

    @ServiceScoped//Its counterpart of 'Singleton' in AppModule, i.e. The dependencies would be created only one-time.
    @Provides//Note: Its better to create and henceforth inject AudioAttributes of exoplayer to keep the app more modular.
    fun provideAudioAttributes() = AudioAttributes.Builder()
        .setContentType(C.CONTENT_TYPE_MUSIC)
        .setUsage(C.USAGE_MEDIA)
        .build()

    @ServiceScoped
    @Provides
    fun provideExoPlayer(
        @ApplicationContext context: Context,
        audioAttributes: AudioAttributes
    ) = SimpleExoPlayer.Builder(context).build().apply{
        setAudioAttributes(audioAttributes, true)
        setHandleAudioBecomingNoisy(true)
    }

    @ServiceScoped
    @Provides
    fun provideDataSourceFactory(
        @ApplicationContext context: Context
    ) = DefaultDataSourceFactory(context, Util.getUserAgent(context, "TuneIo Naya wala App"))

    @ServiceScoped
    @Provides
    fun providesMusicDatabase() = MusicDatabase()
}