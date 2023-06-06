package com.kotlintoast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class HelloService extends ReactContextBaseJavaModule {
    @NonNull
    @Override
    public String getName() {
        return "HelloModule";
    }

    @ReactMethod
    public void sayHi(String name, Promise promise) {
        try {
            promise.resolve("Hello " + name);

//            WritableMap map = Arguments.createMap();
//            map.putString("name", name);
//            sendEvent(getReactApplicationContext(), "HelloModule", map);
        } catch (Exception e) {
            promise.reject(e.toString());
        }

    }

    private void sendEvent(ReactContext reactContext,
                           String eventName,
                           @Nullable WritableMap params) {
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }

}
