package net.hauers.tpgticket;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendMessage(View view) {

        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        Log.i("net.hauers.tpgticket", "In sendMessage, the Checkbox is " + checkBox.isChecked());
        if(checkBox.isChecked()) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("788", null, "tpg2", null, null);
        }
        checkBox.setChecked(false);
    }

    public void unlockWiFi(View view) {
        Log.i("net.hauers.tpgticket", "In unlockWifi()");

        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                Log.i("net.hauers.tpgticket", "In doInBackground()");
                try {
                    String urlParameters = "Pin_Verify=6666&save=%20Apply";
                    URL url = new URL("http://10.15.17.1/goform/Login_Verify_PIN");
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
                    writer.write(urlParameters);
                    writer.flush();

                    String line;
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line = reader.readLine()) != null) {
                        Log.i("net.hauers.tpgticket", line);
                    }

                    writer.close();
                    reader.close();

                } catch (Exception e) {
                    Log.e("net.hauers.tpgticket", e.toString());
                }
                return null;
            }
        }.execute();
    }
}
