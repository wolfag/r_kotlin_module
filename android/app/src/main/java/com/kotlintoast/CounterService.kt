package com.kotlintoast

import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Callback
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.WritableMap
import com.facebook.react.modules.core.DeviceEventManagerModule
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter

class CounterService(private val reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext) {

    private var eventCounter = 0

    override fun getName(): String {
        return "CounterModule"
    }

    @ReactMethod
    fun createCounterEvent(callback: Callback) {
        callback.invoke("event from native")
    }

    @ReactMethod
    fun createCounterPromise(promise: Promise) {
        try {
            promise.resolve("promise from native")
            eventCounter += 1

            // send simple value
            sendEvent(reactApplicationContext, "eventCounter", eventCounter)

            // send object value
            var map: WritableMap = Arguments.createMap()
            map.putString("k1", "name")
            map.putInt("k2", 32)
            sendEvent(reactApplicationContext, "eventCounter", map)
        } catch (e: java.lang.Exception) {
            promise.reject(e.toString())
        }
    }

    @ReactMethod
    fun createCounterWithInit(input: ReadableMap) {
        eventCounter = input.getInt("init")
    }

    private fun sendEvent(reactContext: ReactContext, eventName: String, param: Int) {
        reactContext.getJSModule(RCTDeviceEventEmitter::class.java).emit(eventName, param)
    }

    private fun sendEvent(reactContext: ReactContext, eventName: String, param: WritableMap) {
        reactContext.getJSModule(RCTDeviceEventEmitter::class.java).emit(eventName, param)
    }
}