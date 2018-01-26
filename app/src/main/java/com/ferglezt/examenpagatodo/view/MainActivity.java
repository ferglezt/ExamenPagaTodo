package com.ferglezt.examenpagatodo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.ferglezt.examenpagatodo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fernando on 26/01/18.
 */

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.gridview) GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
