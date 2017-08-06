package com.sdo.borachok.noteapp.populator;

public interface Populator<S, T>
{
    public void populate(S source, T target);
}
