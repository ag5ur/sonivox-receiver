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

### Basic Example

```java
          try {
            SonivoxReceiver sonivoxReceiver = new SonivoxReceiver();
            MidiSystem.addMidiDevice(sonivoxReceiver);
            
            Sequencer sequencer = MidiSystem.getSequencer();
            
            sequencer.open();
            sonivoxReceiver.open();
            
            Sequence sequence = new Sequence(Sequence.PPQ, 240);

            Track track = sequence.createTrack();
            track.add(new MidiEvent(new ShortMessage(-64, 44, 0), 2880));
            track.add(new MidiEvent(new ShortMessage(-112, 69, 64), 2880));
            track.add(new MidiEvent(new ShortMessage(-128, 69, 64), 2910));
            track.add(new MidiEvent(new ShortMessage(-112, 67, 64), 2925));
            track.add(new MidiEvent(new ShortMessage(-128, 67, 64), 2965));
            track.add(new MidiEvent(new ShortMessage(-112, 69, 64), 2970));
            track.add(new MidiEvent(new ShortMessage(-128, 69, 64), 3765));
            track.add(new MidiEvent(new ShortMessage(-112, 64, 64), 3840));
            track.add(new MidiEvent(new ShortMessage(-128, 64, 64), 4005));
            track.add(new MidiEvent(new ShortMessage(-112, 65, 64), 4020));
            track.add(new MidiEvent(new ShortMessage(-128, 65, 64), 4185));
            track.add(new MidiEvent(new ShortMessage(-112, 61, 64), 4200));
            track.add(new MidiEvent(new ShortMessage(-128, 61, 64), 4365));
            track.add(new MidiEvent(new ShortMessage(-112, 62, 64), 4380));
            track.add(new MidiEvent(new ShortMessage(-128, 62, 64), 4740));

            sequencer.setSequence(sequence);
            
            sequencer.start();
        } catch (MidiUnavailableException | InvalidMidiDataException ignored) {}
```
