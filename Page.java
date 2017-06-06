package com.example.user.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by user on 2016-12-22.
 */

public class Page {
    private View view;
    private String title;

    public Page(Context context, int viewId, String title) {
        this.view = LayoutInflater.from(context).inflate(viewId,null);
        this.title = title;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
