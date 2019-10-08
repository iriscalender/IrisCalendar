package com.dsm.iriscalendar.ui.category;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.dsm.iriscalendar.R;
import com.dsm.iriscalendar.base.BaseActivity;
import com.dsm.iriscalendar.data.model.Category;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends BaseActivity implements CategoryContract.View {

    @BindView(R.id.tv_cancel)
    TextView tvCancel;

    @BindView(R.id.tv_purple)
    TextView tvPurple;

    @BindView(R.id.tv_blue)
    TextView tvBlue;

    @BindView(R.id.tv_pink)
    TextView tvPink;

    @BindView(R.id.tv_orange)
    TextView tvOrange;

    @BindView(R.id.tv_complete)
    TextView tvComplete;

    @BindView(R.id.cl_parent)
    ConstraintLayout clParent;

    @Inject
    CategoryContract.Presenter presenter;

    InputMethodManager imm;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        presenter.createView(this);
        presenter.getCategory();

        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        addFocusChangeListener(R.id.vs_purple);
        addFocusChangeListener(R.id.vs_blue);
        addFocusChangeListener(R.id.vs_pink);
        addFocusChangeListener(R.id.vs_orange);
        addOnEditorActionListener(R.id.vs_purple);
        addOnEditorActionListener(R.id.vs_blue);
        addOnEditorActionListener(R.id.vs_pink);
        addOnEditorActionListener(R.id.vs_orange);

        clParent.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                removeFocus(findViewById(R.id.et_purple), event);
                removeFocus(findViewById(R.id.et_blue), event);
                removeFocus(findViewById(R.id.et_pink), event);
                removeFocus(findViewById(R.id.et_orange), event);
            }
            return false;
        });

        tvComplete.setOnClickListener(v -> presenter.modifyCategory());

        tvCancel.setOnClickListener(v -> finish());
    }

    private void addOnEditorActionListener(int id) {
        ViewSwitcher vs = findViewById(id);
        TextView tv = (TextView) vs.getChildAt(0);
        EditText et = (EditText) vs.getChildAt(1);

        et.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                vs.showNext();
                tv.setText(et.getText().toString().trim());
                et.clearFocus();
                imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
            }
            return true;
        });
    }

    private void removeFocus(EditText view, MotionEvent event) {
        Rect outRect = new Rect();
        view.getGlobalVisibleRect(outRect);
        if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
            view.clearFocus();
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    private void addFocusChangeListener(int id) {
        ViewSwitcher vs = findViewById(id);
        TextView tv = (TextView) vs.getChildAt(0);
        EditText et = (EditText) vs.getChildAt(1);

        et.setOnFocusChangeListener((v1, hasFocus) -> {
            if (!hasFocus) {
                vs.showNext();
                tv.setText(et.getText().toString().trim());
            }
        });
    }

    public void onViewSwitcherClick(View v) {
        ViewSwitcher clickedView = (ViewSwitcher) v;
        TextView tv = (TextView) clickedView.getChildAt(0);
        EditText et = (EditText) clickedView.getChildAt(1);

        clickedView.showNext();

        et.setText(tv.getText().toString().trim());
        et.requestFocus();
        if (imm != null) {
            imm.showSoftInput(et, 0);
        }
    }

    @Override
    public Category getCategories() {
        Category category = new Category();
        category.setPurple(tvPurple.getText().toString().trim());
        category.setBlue(tvBlue.getText().toString().trim());
        category.setPink(tvPink.getText().toString().trim());
        category.setOrange(tvOrange.getText().toString().trim());
        return category;
    }

    @Override
    public void setCategory(Category category) {
        tvPurple.setText(category.getPurple());
        tvBlue.setText(category.getBlue());
        tvPink.setText(category.getPink());
        tvOrange.setText(category.getOrange());
    }

    @Override
    public void toastServerError() {
        Toast.makeText(this, R.string.error_server_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toastModifySuccess() {
        Toast.makeText(this, R.string.success_category_modify, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toastInvalidValue() {
        Toast.makeText(this, R.string.error_invalid_value, Toast.LENGTH_SHORT).show();
    }
}
