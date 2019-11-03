package com.github.ag5ur.receivertest;

import android.content.Intent;
import android.os.Bundle;

import com.github.ag5ur.sonivoxreceiver.SonivoxReceiver;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.sound.midi.io.StandardMidiFileReader;

public class MainActivity extends AppCompatActivity {
    private static final String STATE_PLAYING = "isPlaying";
    private FloatingActionButton fab;
    private SwitchCompat toggle;
    private SonivoxReceiver sonivoxReceiver;
    private Sequencer sequencer;
    private boolean isPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> fabClicked());

        toggle = findViewById(R.id.toggle);
        toggle.setOnClickListener(view -> toggleSwitched());

        sonivoxReceiver = new SonivoxReceiver();
        MidiSystem.addMidiDevice(sonivoxReceiver);
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
        } catch (MidiUnavailableException e) {
            Snackbar.make(getWindow().getDecorView(), "Failed to get midi sequencer", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
        if (sequencer != null) {
            sequencer.addMetaEventListener(event -> {
                if (event.getType() == MetaMessage.TYPE_END_OF_TRACK) {
                    stop();
                }
            });
            toggleSwitched();
        } else {
            fab.setEnabled(false);
            toggle.setEnabled(false);
        }
        if (savedInstanceState != null) {
            isPlaying = savedInstanceState.getBoolean(STATE_PLAYING);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_license) {
            startActivity(new Intent(this, LicenseActivity.class));
        } else if (id == R.id.action_notice) {
            startActivity(new Intent(this, NoticeActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(STATE_PLAYING, isPlaying);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        if (!isChangingConfigurations()) {
            stop();
        }
    }

    private void fabClicked() {
        if (isPlaying) {
            stop();
        } else {
            start();
        }
    }

    private void stop() {
        if (isPlaying) {
            fab.setImageResource(R.drawable.ic_play);
            sequencer.stop();
            sonivoxReceiver.close();
            isPlaying = false;
        }
    }

    private void start() {
        if (!isPlaying) {
            fab.setImageResource(R.drawable.ic_stop);
            sonivoxReceiver.open();
            sequencer.start();
            isPlaying = true;
        }
    }

    private void toggleSwitched() {
        stop();
        Sequence sequence;
        try {
            if (toggle.isChecked()) {
                sequence = new Sequence(Sequence.PPQ, 240);
                Track track = sequence.createTrack();

                // track.add(new MidiEvent(new ShortMessage(-64, 44, 0), 0));
                // track.add(new MidiEvent(new ShortMessage(-112, 81, 64), 0));
                // track.add(new MidiEvent(new ShortMessage(-128, 81, 64), 30));
                // track.add(new MidiEvent(new ShortMessage(-112, 79, 64), 45));
                // track.add(new MidiEvent(new ShortMessage(-128, 79, 64), 85));
                // track.add(new MidiEvent(new ShortMessage(-112, 81, 64), 90));
                // track.add(new MidiEvent(new ShortMessage(-128, 81, 64), 945));
                // track.add(new MidiEvent(new ShortMessage(-112, 79, 64), 960));
                // track.add(new MidiEvent(new ShortMessage(-128, 79, 64), 1000));
                // track.add(new MidiEvent(new ShortMessage(-112, 77, 64), 1005));
                // track.add(new MidiEvent(new ShortMessage(-128, 77, 64), 1045));
                // track.add(new MidiEvent(new ShortMessage(-112, 76, 64), 1050));
                // track.add(new MidiEvent(new ShortMessage(-128, 76, 64), 1090));
                // track.add(new MidiEvent(new ShortMessage(-112, 74, 64), 1095));
                // track.add(new MidiEvent(new ShortMessage(-128, 74, 64), 1135));
                // track.add(new MidiEvent(new ShortMessage(-112, 73, 64), 1140));
                // track.add(new MidiEvent(new ShortMessage(-128, 73, 64), 1665));
                // track.add(new MidiEvent(new ShortMessage(-112, 74, 64), 1695));
                // track.add(new MidiEvent(new ShortMessage(-128, 74, 64), 1995));
                // track.add(new MidiEvent(new MetaMessage(47, null, 0), 2295));

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
                track.add(new MidiEvent(new MetaMessage(47, null, 0), 5100));
            } else {
                InputStream is = getResources().openRawResource(R.raw.bach);
                StandardMidiFileReader reader = new StandardMidiFileReader();
                sequence = reader.getSequence(is);
            }
            sequencer.setSequence(sequence);
            fab.setEnabled(true);
        } catch (InvalidMidiDataException | IOException e) {
            fab.setEnabled(false);
        }
    }
}
