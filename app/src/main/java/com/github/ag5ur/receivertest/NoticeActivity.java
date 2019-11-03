package com.github.ag5ur.receivertest;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.ColorUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import io.noties.markwon.AbstractMarkwonPlugin;
import io.noties.markwon.Markwon;
import io.noties.markwon.core.MarkwonTheme;

public class NoticeActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView = findViewById(R.id.textView);

        final Markwon markwon = Markwon.builder(this)
                .usePlugin(new AbstractMarkwonPlugin() {
                    @Override
                    public void configureTheme(@NonNull MarkwonTheme.Builder builder) {
                        int textColor = textView.getCurrentTextColor();
                        float[] hsv = new float[3];
                        float[] hsl = new float[3];
                        Color.colorToHSV(textColor, hsv);
                        ColorUtils.colorToHSL(textColor, hsl);
                        hsv[0] = 210;
                        if (hsv[2] < 0.5f) {
                            hsv[1] = 1 - hsl[2];
                            hsv[2] = 1 - hsl[2];
                        } else {
                            hsv[1] = hsl[2];
                        }
                        builder.linkColor(Color.HSVToColor(hsv));
                    }
                })
                .build();

        InputStream in_s = getResources().openRawResource(R.raw.notice);
        String markdown;
        try {
            byte[] b = new byte[in_s.available()];
            //noinspection ResultOfMethodCallIgnored
            in_s.read(b);
            markdown = new String(b, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        markwon.setMarkdown(textView, markdown);
    }
}
