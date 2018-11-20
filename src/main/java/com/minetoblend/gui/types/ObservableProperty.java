package com.minetoblend.gui.types;

import java.util.ArrayList;
import java.util.List;

public class ObservableProperty<T> {

    T value;

    List<Listener> listeners = new ArrayList<>();

    public ObservableProperty(T initialValue) {
        this.value = initialValue;
    }

    void set(T newValue) {
        var oldValue = this.value;
        value = newValue;
        notifyListeners(newValue, oldValue);
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

    T get() {
        return value;
    }


    interface Listener<T> {

        void onValueChange(T newValue, T oldValue, ObservableProperty<T> property);

    }

}
