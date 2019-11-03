package com.github.ag5ur.receivertest;

import android.graphics.Paint;
import android.text.Spanned;
import android.text.style.LineHeightSpan;

import androidx.annotation.Px;

/**
 * Adds spacing between list items
 * See https://github.com/noties/Markwon/issues/143#issuecomment-506666146
 */
class FirstLineSpacingSpan implements LineHeightSpan {

    private final int spacing;

    private int startAscent;
    private int startTop;

    FirstLineSpacingSpan(@Px int spacing) {
        this.spacing = spacing;
    }

    @Override
    public void chooseHeight(CharSequence text, int start, int end, int spanstartv, int v, Paint.FontMetricsInt fm) {
        final int spanStart = ((Spanned) text).getSpanStart(this);
        if (start == spanStart) {
            this.startAscent = fm.ascent;
            this.startTop = fm.top;
            final FirstLineSpacingSpan[] spans = ((Spanned) text).getSpans(start - 2, start, FirstLineSpacingSpan.class);
            if (spans != null && spans.length > 0) {
                fm.ascent -= spacing;
                fm.top -= spacing;
            }
        } else {
            fm.ascent = this.startAscent;
            fm.top = this.startTop;
        }
    }
}
