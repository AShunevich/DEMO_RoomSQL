package com.ashunevich.booktracker;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule   {
    @ContributesAndroidInjector
    abstract MainActivity contributemainActivity();
}


/*
@module shows compiler that this  module will be used to provide various activities in app like MainActivity.
All the future generated activities must be defined here of the method above which the annotation is indicated.
 AndroidInjector is a sub-component.This subcomponent is a child of the component (or subcomponent) to which this module
(in which this annotation is present) will be added.
 */