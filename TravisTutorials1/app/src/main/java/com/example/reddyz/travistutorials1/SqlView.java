package com.example.reddyz.travistutorials1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Reddyz on 21-10-2016.
 */
public class SqlView extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlview);
        TextView tvInfo = (TextView) findViewById(R.id.tvSqlInfo);

        SqlHandleData info = new SqlHandleData(this);
        info.open();
        String data = info.getData();
        info.close();
        tvInfo.setText(data);
    }
}
