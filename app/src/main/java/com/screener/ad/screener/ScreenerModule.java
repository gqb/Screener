package com.screener.ad.screener;

import android.app.Application;
import android.content.Context;

import com.screener.ad.screener.network.ScreenerRestQueue;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ScreenerModule {
    private final Application application;
    private Context context;

    public ScreenerModule(Application application) {
        this.application = application;
        this.context = application.getApplicationContext();
    }

    @Provides
    @Singleton
    static ScreenerRestQueue provideScreenerRestQueue() {
        return new ScreenerRestQueue();
    }

}