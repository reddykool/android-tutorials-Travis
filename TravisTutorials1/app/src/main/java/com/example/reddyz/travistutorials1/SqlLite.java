package com.example.reddyz.travistutorials1;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Reddyz on 21-10-2016.
 */
public class SqlLite extends AppCompatActivity implements View.OnClickListener {
    Button sqlUpdateButton, sqlViewButton, sqlEditButton, sqlDeleteButton, sqlGetInfoButton;
    EditText sqlNameText, sqlAgeText, sqlRowIdText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqllite);

        sqlNameText = (EditText) findViewById(R.id.etSqlName);
        sqlAgeText = (EditText) findViewById(R.id.etSqlAge);
        sqlUpdateButton = (Button) findViewById(R.id.bSqlUpdate);
        sqlViewButton = (Button) findViewById(R.id.bSqlView);

        sqlRowIdText = (EditText) findViewById(R.id.etSqlRowId);
        sqlGetInfoButton = (Button) findViewById(R.id.bSqlGetInfo);
        sqlEditButton = (Button) findViewById(R.id.bSqlEdit);
        sqlDeleteButton = (Button) findViewById(R.id.bSqlDelete);

        sqlUpdateButton.setOnClickListener(this);
        sqlViewButton.setOnClickListener(this);
        sqlGetInfoButton.setOnClickListener(this);
        sqlEditButton.setOnClickListener(this);
        sqlDeleteButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bSqlUpdate:
                boolean working =true;
                try {
                    String name = sqlNameText.getText().toString();
                    String age = sqlAgeText.getText().toString();
                    SqlHandleData sqlHandle = new SqlHandleData(SqlLite.this);
                    sqlHandle.open();
                    sqlHandle.createEntry(name, age);
                    sqlHandle.close();
                } catch (Exception e) {
                    working = false;
                    String err = e.toString();
                    Dialog dlg = new Dialog(this);
                    dlg.setTitle(" Damn !");
                    TextView tv = new TextView(this);
                    tv.setText("  \n Update Exception :: \n" + err);
                    dlg.setContentView(tv);
                    dlg.show();
                } finally {
                    if(working) {
                        Dialog dlg = new Dialog(this);
                        dlg.setTitle("Yo Yo !");
                        TextView tv = new TextView(this);
                        tv.setText("  \n Success !!! \n");
                        dlg.setContentView(tv);
                        dlg.show();
                    }
                }
                break;

            case R.id.bSqlView: {
                Intent viewIntent = new Intent(this, SqlView.class);
                //Intent viewIntent = new Intent("com.example.reddyz.travistutorials1.SQLVIEW");
                startActivity(viewIntent);
                break;
            }

            case R.id.bSqlGetInfo: {
                try {
                    String str = sqlRowIdText.getText().toString();
                    long id = Long.parseLong(str);
                    SqlHandleData sqlHandle = new SqlHandleData(SqlLite.this);
                    sqlHandle.open();
                    str = sqlHandle.getName(id);
                    sqlNameText.setText(str);
                    str = sqlHandle.getAge(id);
                    sqlAgeText.setText(str);
                    sqlHandle.close();
                }catch (Exception e) {
                    String err = e.toString();
                    Dialog dlg = new Dialog(this);
                    dlg.setTitle(" Damn !");
                    TextView tv = new TextView(this);
                    tv.setText("  \n GetInfo Exception :: \n" + err);
                    dlg.setContentView(tv);
                    dlg.show();
                }
                break;
            }

            case R.id.bSqlEdit: {
                try {
                    String name = sqlNameText.getText().toString();
                    String age = sqlAgeText.getText().toString();
                    String strId = sqlRowIdText.getText().toString();
                    long id = Long.parseLong(strId);
                    SqlHandleData sqlHandle = new SqlHandleData(SqlLite.this);
                    sqlHandle.open();
                    sqlHandle.EditEntry(id, name, age);
                    sqlHandle.close();
                }catch (Exception e) {
                    String err = e.toString();
                    Dialog dlg = new Dialog(this);
                    dlg.setTitle(" Damn !");
                    TextView tv = new TextView(this);
                    tv.setText("  \n Edit Exception :: \n" + err);
                    dlg.setContentView(tv);
                    dlg.show();
                }
                break;
            }

            case R.id.bSqlDelete : {
                try {
                    String str = sqlRowIdText.getText().toString();
                    long id = Long.parseLong(str);
                    SqlHandleData sqlHandle = new SqlHandleData(SqlLite.this);
                    sqlHandle.open();
                    sqlHandle.deleteEntry(id);
                    sqlHandle.close();
                }catch (Exception e) {
                    String err = e.toString();
                    Dialog dlg = new Dialog(this);
                    dlg.setTitle(" Damn !");
                    TextView tv = new TextView(this);
                    tv.setText("  \n Delete Exception :: \n" + err);
                    dlg.setContentView(tv);
                    dlg.show();
                }
                break;
            }
        }
    }
}
