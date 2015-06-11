package app.demo.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends ActionBarActivity implements Datepickdialog.CustomDialoglistner {
    AutoCompleteTextView origincity, destinationcity;
    EditText edittextdate;
    FragmentManager fm;
    Datepickdialog datepick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializecontrols();
    }


    private void initializecontrols() {
        edittextdate = (EditText) findViewById(R.id.edittextdate);
        origincity = (AutoCompleteTextView) findViewById(R.id.actvorigincity);
        destinationcity = (AutoCompleteTextView) findViewById(R.id.actvdestinationcity);
        String[] countries = getResources().getStringArray(R.array.list_of_countries);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        origincity.setAdapter(adapter);
        destinationcity.setAdapter(adapter);

        edittextdate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    showDateDialog();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    private void showdatedialog() {
//
//        Calendar c1 = Calendar.getInstance();
//        int year = c1.get(Calendar.YEAR);
//        int month = c1.get(Calendar.MONTH);
//        int day = c1.get(Calendar.DAY_OF_MONTH);
//
//        DatePickerDialog.OnDateSetListener myDateListener  = new DatePickerDialog.OnDateSetListener() {
//
//            public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
//                if(arg0.isShown()){
//                    //do further code here
//                    String date = arg1 + "-" + arg2 + "-" + arg3;
//                    edittextdate.setText(date);
//                }
//            }
//
//        };
//
//        DatePickerDialog dp = new DatePickerDialog(MainActivity.this, myDateListener, year, month, day);
//        dp.show();
//    }

    private void showdatedialog() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialoglayout);
        dialog.setTitle("Title...");
        dialog.show();
    }


    private void showDateDialog() {
        fm = getSupportFragmentManager();
        datepick = new Datepickdialog();
        datepick.show(fm, "fragment_edit_name");
    }


    @Override
    public void cancelclick() {
        datepick.dismiss();
    }

    @Override
    public void okLclick(String s) {
        edittextdate.setText(s);
        datepick.dismiss();
    }
}
