package com.ashunevich.booktracker;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ViewModelModule.class})
public class AppModule {
    @Provides
    @Singleton
    Context provvideContext (Application app){
        return app;
    }
    @Provides
    @Singleton
    BookRepository provideBookRepository(Context context){
        return new BookRepository(context);
    }
}
