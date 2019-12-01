package com.dsm.iriscalendar.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.dsm.iriscalendar.R;
import com.dsm.iriscalendar.data.model.Category;

import java.util.Objects;

import butterknife.BindAnim;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryView extends ConstraintLayout implements View.OnClickListener {

    @BindView(R.id.view_purple) View viewPurple;

    @BindView(R.id.view_blue) View viewBlue;

    @BindView(R.id.view_pink) View viewPink;

    @BindView(R.id.view_orange) View viewOrange;

    @BindAnim(R.anim.anim_big) Animation animBig;

    @BindAnim(R.anim.anim_small) Animation animSmall;

    private int selectedCategory = 0;

    public CategoryView(Context context) {
        super(context);
        initView();
    }

    public CategoryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CategoryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = Objects.requireNonNull(inflater).inflate(R.layout.view_category, this, false);
        addView(view);
        ButterKnife.bind(this);
        setOnClickListener(this);
        animBig.setFillAfter(true);

        viewPurple.setOnClickListener(this);
        viewBlue.setOnClickListener(this);
        viewPink.setOnClickListener(this);
        viewOrange.setOnClickListener(this);
    }

    private void onClickCategory(View v) {
        int clickedId = v.getId();

        if (clickedId == selectedCategory) return;

        switch (selectedCategory) {
            case R.id.view_purple:
                viewPurple.startAnimation(animSmall);
                break;
            case R.id.view_blue:
                viewBlue.startAnimation(animSmall);
                break;
            case R.id.view_pink:
                viewPink.startAnimation(animSmall);
                break;
            case R.id.view_orange:
                viewOrange.startAnimation(animSmall);
                break;
        }

        if (clickedId == R.id.view_purple) {
            viewPurple.startAnimation(animBig);
            selectedCategory = R.id.view_purple;
        } else if (clickedId == R.id.view_blue) {
            viewBlue.startAnimation(animBig);
            selectedCategory = R.id.view_blue;
        } else if (clickedId == R.id.view_pink) {
            viewPink.startAnimation(animBig);
            selectedCategory = R.id.view_pink;
        } else if (clickedId == R.id.view_orange) {
            viewOrange.startAnimation(animBig);
            selectedCategory = R.id.view_orange;
        }
    }

    public void setCategory(Category category) {
        ((TextView) findViewById(R.id.tv_purple)).setText(category.getPurple());
        ((TextView) findViewById(R.id.tv_blue)).setText(category.getBlue());
        ((TextView) findViewById(R.id.tv_pink)).setText(category.getPink());
        ((TextView) findViewById(R.id.tv_orange)).setText(category.getOrange());
    }

    public String getCategory() {
        if (selectedCategory == R.id.view_purple) return "purple";
        else if (selectedCategory == R.id.view_blue) return "blue";
        else if (selectedCategory == R.id.view_pink) return "pink";
        else if (selectedCategory == R.id.view_orange) return "orange";
        else return "";
    }

    public void selectCategory(String category) {
        switch (category) {
            case "purple":
                selectedCategory = R.id.view_purple;
                viewPurple.startAnimation(animBig);
                break;
            case "blue":
                selectedCategory = R.id.view_blue;
                viewBlue.startAnimation(animBig);
                break;
            case "pink":
                selectedCategory = R.id.view_pink;
                viewPink.startAnimation(animBig);
                break;
            case "orange":
                selectedCategory = R.id.view_orange;
                viewOrange.startAnimation(animBig);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_purple:
            case R.id.view_blue:
            case R.id.view_pink:
            case R.id.view_orange:
                onClickCategory(v);
                break;
        }
    }
}
