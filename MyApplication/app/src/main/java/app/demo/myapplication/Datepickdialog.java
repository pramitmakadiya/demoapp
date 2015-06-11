package app.demo.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateChangedListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jogi on 6/11/2015.
 */
public class Datepickdialog extends DialogFragment implements View.OnClickListener {
    CalendarView calendarView;
    TextView tvok, tvcancel, tvyear, tvdate;
    CustomDialoglistner mylistner;
    String Strdate,strreturn;

    public Datepickdialog() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialoglayout, container);
        bindcontrols(view);
        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
       mylistner= (CustomDialoglistner) activity;
    }

    private void bindcontrols(View view) {
        try {
            MaterialCalendarView calendarView = (MaterialCalendarView) view.findViewById(R.id.calendarView);

            tvcancel = (TextView) view.findViewById(R.id.textviewcancel);
            tvok = (TextView) view.findViewById(R.id.textviewok);
            tvdate = (TextView) view.findViewById(R.id.textviewdate);
            tvyear = (TextView) view.findViewById(R.id.textviewyear);
            Calendar c = Calendar.getInstance();
            calendarView.setCurrentDate(c);
            tvyear.setText("" + c.get(Calendar.YEAR));
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM yy");
            Strdate = sdf.format(new Date(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)));
            tvdate.setText(Strdate);
            strreturn= Calendar.DAY_OF_MONTH +" - "+Calendar.MONTH+" - "+Calendar.YEAR ;
            calendarView.setOnDateChangedListener(new OnDateChangedListener() {
                @Override
                public void onDateChanged(MaterialCalendarView materialCalendarView, CalendarDay calendarDay) {
                    tvyear.setText("" + calendarDay.getYear());
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM yy");
                    Strdate = sdf.format(new Date(calendarDay.getYear(), calendarDay.getMonth(), calendarDay.getDay()));
                    strreturn=""+calendarDay.getDay()+" - "+calendarDay.getMonth() +" - "+calendarDay.getYear();
                    tvdate.setText(Strdate);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        tvok.setOnClickListener(this);
        tvcancel.setOnClickListener(this);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onClick(View v) {
        if(v==tvcancel)
        {
            mylistner.cancelclick();
        }
        else if(v==tvok)
        {
            mylistner.okLclick(strreturn);
        }
    }

    public interface CustomDialoglistner {
        public void cancelclick();
        public void okLclick(String s);
    }
}
