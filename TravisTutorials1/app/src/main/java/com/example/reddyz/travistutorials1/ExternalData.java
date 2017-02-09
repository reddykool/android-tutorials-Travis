package com.example.reddyz.travistutorials1;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Reddyz on 14-10-2016.
 */
public class ExternalData extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    TextView canWrite, canRead;
    String state;
    boolean canW, canR;
    Spinner spinner;
    String[] paths = {"Music", "Pictures", "Downloads"};
    File path = null;
    File file = null;
    EditText etSaveFile;
    Button bConfirm, bSave;
    int InputImgResId;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.externaldata);

        canWrite = (TextView) findViewById(R.id.tvCanWrite);
        canRead = (TextView) findViewById(R.id.tvCanRead);
        bSave = (Button) findViewById(R.id.bEDSave);
        bConfirm = (Button) findViewById(R.id.bEDConfirm);
        etSaveFile = (EditText) findViewById(R.id.etEDSaveAs);
        InputImgResId = R.drawable.smiley_smile;

        bConfirm.setOnClickListener(this);
        bSave.setOnClickListener(this);

        checkState();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ExternalData.this,
                android.R.layout.simple_spinner_item, paths);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        verifyStoragePermissions(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int pos = spinner.getSelectedItemPosition();
        switch (pos) {
            case 0:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
                break;
            case 1:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                break;
            case 2:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bEDSave:
                File myDir = new File(path + "/redz_files");
                myDir.mkdirs();

                String fileStr = etSaveFile.getText().toString();
                file = new File(myDir, fileStr + ".png");
                if (file.exists())
                    file.delete();
                //checkState();
                if (canW == canR == true) {
                    try {
                        //InputStream inputStream = getResources().openRawResource(R.drawable.pic3);
                        InputStream inputStream;
                        inputStream = getResources().openRawResource(InputImgResId);
                        OutputStream outputStream = new FileOutputStream(file);
                        byte[] data = new byte[inputStream.available()];
                        inputStream.read(data);
                        outputStream.write(data);
                        inputStream.close();
                        outputStream.close();

                        Toast.makeText(this, "File Saved to ED", Toast.LENGTH_LONG).show();
                        //Update files for the user to use
                        MediaScannerConnection.scanFile(this,
                                new String[]{file.toString()},
                                null,
                                new MediaScannerConnection.OnScanCompletedListener() {

                                    @Override
                                    public void onScanCompleted(String path, Uri uri) {
                                        Toast.makeText(ExternalData.this, "Scan Complete...", Toast.LENGTH_LONG).show();
                                    }
                                }
                        );

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.bEDConfirm:
                bSave.setVisibility(View.VISIBLE);
                //bSave.setEnabled(true);
                File dirToRead = new File(path + "/redz_files");
                File fileToRead = new File(dirToRead, "testdata2.txt");
                boolean fileExists = fileToRead.exists();
                if (fileExists) {
                    FileInputStream inStream = null;
                    try {
                        inStream = new FileInputStream(fileToRead);
                        int readSize = inStream.available();
                        //Log.i(TAG, "file read bytes available: " + readSize);
                        if (readSize != 0) {
                            byte[] dataArray = new byte[readSize];
                            inStream.read(dataArray);
                            String collected = new String(dataArray);
                            inStream.close();
                            Toast.makeText(ExternalData.this, "Read Success!!!", Toast.LENGTH_LONG).show();
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    private void checkState() {
        state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            canWrite.setText("Can Write: True");
            canRead.setText("Can Read: True");
            canW = canR = true;
        } else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            canWrite.setText("Can Write: False");
            canRead.setText("Can Read: True");
            canW = false;
            canR = true;
        } else {
            canWrite.setText("Can Write: False");
            canRead.setText("Can Read: False");
            canW = canR = false;
        }
    }


    //permission method.
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have read or write permission
        int writePermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, PERMISSIONS_STORAGE[0])) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {
                // We don't have permission so prompt the user
                ActivityCompat.requestPermissions( activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE:
                canR = canW = false;
                if(grantResults.length > 0) {
                    canR = (grantResults[0] == PackageManager.PERMISSION_GRANTED);
                    canW = (grantResults[1] == PackageManager.PERMISSION_GRANTED);
                }
                if(!canR && !canW) {
                    Toast.makeText(ExternalData.this, "Runtime...R/W Permission Denied :(", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "ExternalData Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.reddyz.travistutorials1/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "ExternalData Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.reddyz.travistutorials1/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
