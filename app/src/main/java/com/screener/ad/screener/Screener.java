package com.screener.ad.screener;

import com.screener.ad.screener.network.ScreenerRestQueue;

import dagger.Component;

@Component(modules = {ScreenerModule.class})
public interface Screener {
    ScreenerRestQueue provideScreenerRestQueue();
}