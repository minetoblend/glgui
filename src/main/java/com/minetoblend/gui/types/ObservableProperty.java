package com.minetoblend.gui.types;

import java.util.ArrayList;
import java.util.List;

public class ObservableProperty<T> {

    T value;

    List<Listener> listeners = new ArrayList<>();

    public ObservableProperty(T initialValue) {
        this.value = initialValue;
    }

    public void set(T newValue) {
        var oldValue = this.value;
        value = newValue;
        notifyListeners(newValue, oldValue);
    }

    public T get() {
        return value;
    }

    private void notifyListeners(T newValue, T oldValue) {
        for (Listener listener : listeners) {
            listener.onValueChange(newValue, oldValue, this);
        }
    }

    private void subscribe(Listener<T> listener) {
        listeners.add(listener);
    }

    private void unsubscribe(Listener<T> listener) {
        listeners.remove(listener);
    }


    interface Listener<T> {

        void onValueChange(T newValue, T oldValue, ObservableProperty<T> property);

    }

    @Override
    public String toString() {
        return value.toString();
    }
}
