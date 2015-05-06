package net.hauers.tpgticket;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;


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
}
