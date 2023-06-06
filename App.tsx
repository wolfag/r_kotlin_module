/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React, {useEffect} from 'react';
import {
  SafeAreaView,
  Text,
  TouchableOpacity,
  NativeModules,
  DeviceEventEmitter,
  NativeEventEmitter,
} from 'react-native';

const ToastService = NativeModules.ToastModule;
const CounterService = NativeModules.CounterModule;
const HelloService = NativeModules.HelloModule;
const ImagePickerModule = NativeModules.ImagePickerModule;

function App() {
  useEffect(() => {
    CounterService.createCounterWithInit({init: 30});

    const eventListener = DeviceEventEmitter.addListener(
      'eventCounter',
      event => {
        console.log({event});
      },
    );

    return () => {
      eventListener.remove();
    };
  }, []);

  useEffect(() => {
    const eventEmitter = new NativeEventEmitter(NativeModules.HelloModule);
    const eventListener = eventEmitter.addListener('EventReminder', event => {
      console.log(event.eventProperty);
    });

    return () => {
      eventListener.remove();
    };
  }, []);

  return (
    <SafeAreaView
      style={{flex: 1, justifyContent: 'center', alignItems: 'center'}}>
      <TouchableOpacity
        onPress={async () => {
          ToastService.showToast('hello world');
          CounterService.createCounterEvent(e => {
            console.log({e});
          });

          const tmp = await CounterService.createCounterPromise();
          console.log({tmp});

          const sayhi = await HelloService.sayHi('Teo');
          console.log({sayhi});
        }}
        style={{
          padding: 10,
          backgroundColor: 'cyan',
        }}>
        <Text>Show toast</Text>
      </TouchableOpacity>
      <TouchableOpacity
        onPress={async () => {
          const p = await ImagePickerModule.pickerImage('please choose image');
          console.log({p});
        }}>
        <Text>gallery</Text>
      </TouchableOpacity>
    </SafeAreaView>
  );
}

export default App;
