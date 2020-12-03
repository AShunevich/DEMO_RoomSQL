package com.ashunevich.booktracker;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityVm.class)
    abstract ViewModel bindMainActivityVM(MainActivityVm mainActivityVm);
}
