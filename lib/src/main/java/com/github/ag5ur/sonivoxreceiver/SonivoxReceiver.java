package com.github.ag5ur.sonivoxreceiver;

import androidx.annotation.NonNull;

import org.billthefarmer.mididriver.MidiDriver;

import java.util.Collections;
import java.util.List;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;

@SuppressWarnings("unused")
public class SonivoxReceiver implements MidiDevice, Receiver {
    private final MidiDriver midi;
    private boolean isOpen;

    public SonivoxReceiver() {
        midi = new MidiDriver();
        isOpen = false;
    }

    @NonNull
    @Override
    public Info getDeviceInfo() {
        return new Info("SonivoxReceiver", "com.github.ag5ur.sonivoxreceiver", "Android MIDI Receiver", "0.0.1");
    }

    @Override
    public void open() {
        midi.start();
        isOpen = true;
    }

    @Override
    public void send(@NonNull MidiMessage midiMessage, long l) {
        midi.write(midiMessage.getMessage());
    }

    @Override
    public void close() {
        midi.stop();
        isOpen = false;
    }

    @Override
    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public long getMicrosecondPosition() {
        return -1;
    }

    @Override
    public int getMaxReceivers() {
        return 1;
    }

    @Override
    public int getMaxTransmitters() {
        return 0;
    }

    @NonNull
    @Override
    public Receiver getReceiver() {
        return this;
    }

    @NonNull
    @Override
    public List<Receiver> getReceivers() {
        return Collections.singletonList(this.getReceiver());
    }

    @NonNull
    @Override
    public Transmitter getTransmitter() throws MidiUnavailableException {
        throw new MidiUnavailableException("Transmitter not implemented");
    }

    @NonNull
    @Override
    public List<Transmitter> getTransmitters() {
        return Collections.emptyList();
    }
}
