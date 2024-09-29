package com.example.wampus;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.wampus.storage.Journal;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;

public class JournalListEntry {
    private Journal journal;

    public JournalListEntry(Journal journal) {
        this.journal = journal;
    }

    public View getListEntry(Context context, Runnable onClickListener) {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 180));
        layout.setOnClickListener(v -> onClickListener.run());

        LinearLayout dateLayout = new LinearLayout(context);
        dateLayout.setOrientation(LinearLayout.VERTICAL);
        dateLayout.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 2));

        TextView dateTextView = new TextView(context);
        dateTextView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1));
        dateTextView.setGravity(Gravity.BOTTOM | Gravity.CENTER);
        dateTextView.setTextAlignment(TextView.TEXT_ALIGNMENT_GRAVITY);
        dateTextView.setText(Integer.valueOf(journal.date.substring(0, 2)) + "/" + Integer.valueOf(journal.date.substring(3, 5)));
        dateTextView.setTextSize(20);
        dateTextView.setTypeface(null, android.graphics.Typeface.BOLD);
        dateLayout.addView(dateTextView);

        TextView dayTextView = new TextView(context);
        dayTextView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1));
        dayTextView.setGravity(Gravity.TOP | Gravity.CENTER);
        dayTextView.setTextAlignment(TextView.TEXT_ALIGNMENT_GRAVITY);
        dayTextView.setText(journal.dayOfWeek.substring(0, 3));
        dayTextView.setTextSize(18);
        dateLayout.addView(dayTextView);

        layout.addView(dateLayout);


        LinearLayout entryLayout = new LinearLayout(context);
        entryLayout.setOrientation(LinearLayout.VERTICAL);
        entryLayout.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 8));

        TextView moodTextView = new TextView(context);
        moodTextView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1));
        moodTextView.setBackgroundColor(Color.parseColor("#C7C7C7"));
        moodTextView.setPadding(10,0,10,0);
        moodTextView.setText(journal.mood);
        MaterialShapeDrawable moodShapeDrawable = new MaterialShapeDrawable(new ShapeAppearanceModel()
                .toBuilder()
                .setTopLeftCorner(CornerFamily.ROUNDED,15)
                .setTopRightCorner(CornerFamily.ROUNDED,15)
                .build());
        moodShapeDrawable.setFillColor(ContextCompat.getColorStateList(context,R.color.background_grey));
        moodTextView.setBackground(moodShapeDrawable);
        entryLayout.addView(moodTextView);

        TextView entryTextView = new TextView(context);
        entryTextView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,2));
        entryTextView.setBackgroundColor(android.graphics.Color.parseColor("#C7C7C7"));
        entryTextView.setEllipsize(android.text.TextUtils.TruncateAt.END);
        entryTextView.setMaxLines(2);
        entryTextView.setPadding(10,0,10,0);
        entryTextView.setText(journal.journalEntry);
        MaterialShapeDrawable entryShapeDrawable = new MaterialShapeDrawable(new ShapeAppearanceModel()
                .toBuilder()
                .setBottomLeftCorner(CornerFamily.ROUNDED,15)
                .setBottomRightCorner(CornerFamily.ROUNDED,15)
                .build());
        entryShapeDrawable.setFillColor(ContextCompat.getColorStateList(context,R.color.background_grey));
        entryTextView.setBackground(entryShapeDrawable);
        entryLayout.addView(entryTextView);

        layout.addView(entryLayout);

        return layout;
    }
}
