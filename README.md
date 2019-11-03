# sonivox-receiver [![Build Status](https://travis-ci.org/ag5ur/sonivox-receiver.svg?branch=master)](https://travis-ci.org/ag5ur/sonivox-receiver) [![JitPack](https://jitpack.io/v/ag5ur/sonivox-receiver.svg)](https://jitpack.io/#ag5ur/sonivox-receiver)

This library provides a simple `javax.sound.midi.Receiver` for the Sonivox EAS Synthesizer on Android.

## Use

Add the libraries to your build.gradle with
```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
and
```gradle
dependencies {
    implementation 'com.github.ag5ur:javax.sound.midi-android:v0.0.4-alpha.2'
    implementation 'com.github.ag5ur:sonivox-receiver:v0.0.1'
}
```
See the example app for sample code.