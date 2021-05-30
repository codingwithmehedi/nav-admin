package bd.jesuits.admin.ui.dailyreading;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

import bd.jesuits.admin.R;
import bd.jesuits.admin.datepickerdialog.NewDatePickerFragment;

public class AddDailyReadingActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {
    private DatabaseReference dailyReadingDBRef;
    ProgressDialog progressDialog;
    private DatabaseReference reference;
    private FirebaseUser user;
    private String userID;
  

    TextView addPickDate;
    Spinner addYearType;
    ImageButton btnBackDailyReading;
    Button btnInsertDailyReading;
    EditText edtWeekType,edtVestment,edtFestDay;
    //general time
    EditText tServiceSongEDT,tOpeningPrayerEDT,tFirstReadingEDT,tPsalmMusicEDT,tSecondReadingEDT,tWorshipWordEDT,tGoodNewsEDT,tOfferingPrayerEDT,tPalaceSongEDT,tClosingPrayerEDT;
    EditText rServiceSongEDT,rOpeningPrayerEDT,rFirstReadingEDT,rPsalmMusicEDT,rSecondReadingEDT,rWorshipWordEDT,rGoodNewsEDT,rOfferingPrayerEDT,rPalaceSongEDT,rClosingPrayerEDT;
    EditText dServiceSongEDT,dOpeningPrayerEDT,dFirstReadingEDT,dPsalmMusicEDT,dSecondReadingEDT,dWorshipWordEDT,dGoodNewsEDT,dOfferingPrayerEDT,dPalaceSongEDT,dClosingPrayerEDT;
    //feast day
    EditText feastTLifeOfSaintEDT, feastDLifeOfSaintEDT;
    EditText feastTServiceSongEDT,feastTOpeningPrayerEDT,feastTFirstReadingEDT,feastTPsalmMusicEDT,feastTSecondReadingEDT,feastTWorshipWordEDT,feastTGoodNewsEDT,feastTOfferingPrayerEDT,feastTPalaceSongEDT,feastTClosingPrayerEDT;
    EditText feastRServiceSongEDT,feastROpeningPrayerEDT,feastRFirstReadingEDT,feastRPsalmMusicEDT,feastRSecondReadingEDT,feastRWorshipWordEDT,feastRGoodNewsEDT,feastROfferingPrayerEDT,feastRPalaceSongEDT,feastRClosingPrayerEDT;
    EditText feastDServiceSongEDT,feastDOpeningPrayerEDT,feastDFirstReadingEDT,feastDPsalmMusicEDT,feastDSecondReadingEDT,feastDWorshipWordEDT,feastDGoodNewsEDT,feastDOfferingPrayerEDT,feastDPalaceSongEDT,feastDClosingPrayerEDT;



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_add_daily_reading);


        androidx.appcompat.widget.Toolbar toolbar = findViewById( R.id.add_daily_reading_toolbar );
        setSupportActionBar(toolbar);

        Objects.requireNonNull( getSupportActionBar() ).setDisplayShowHomeEnabled(true);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add daily reading");
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorWhite)));
        actionBar.setDisplayHomeAsUpEnabled(true);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userID = user.getUid();

        //text view pick date
        addPickDate = (TextView)findViewById( R.id.addPickDate );
        addYearType = findViewById( R.id.addYearType );

        //Spinner year name
        ArrayAdapter<CharSequence> adapterYear = ArrayAdapter.createFromResource(this, R.array.year_type,android.R.layout.simple_spinner_item);
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addYearType.setAdapter(adapterYear);
        addYearType.setOnItemSelectedListener(this );

        btnInsertDailyReading = (Button)findViewById( R.id.btnInsertDailyReading );

        edtWeekType = (EditText) findViewById( R.id.edtWeekType );
        edtVestment = (EditText) findViewById( R.id.edtVestment );
        edtFestDay = (EditText) findViewById( R.id.edtFestDay );

        //General Time edit Text hooks
        tServiceSongEDT = (EditText) findViewById( R.id.tServiceSongEDT );
        rServiceSongEDT = (EditText) findViewById( R.id.rServiceSongEDT );
        dServiceSongEDT = (EditText) findViewById( R.id.dServiceSongEDT );

        tOpeningPrayerEDT = (EditText) findViewById( R.id.tOpeningPrayerEDT );
        rOpeningPrayerEDT = (EditText) findViewById( R.id.rOpeningPrayerEDT );
        dOpeningPrayerEDT = (EditText) findViewById( R.id.dOpeningPrayerEDT );

        tFirstReadingEDT = (EditText) findViewById( R.id.tFirstReadingEDT );
        rFirstReadingEDT = (EditText) findViewById( R.id.rFirstReadingEDT );
        dFirstReadingEDT = (EditText) findViewById( R.id.dFirstReadingEDT );

        tPsalmMusicEDT = (EditText) findViewById( R.id.tPsalmMusicEDT );
        rPsalmMusicEDT = (EditText) findViewById( R.id.rPsalmMusicEDT );
        dPsalmMusicEDT = (EditText) findViewById( R.id.dPsalmMusicEDT );

        tSecondReadingEDT = (EditText) findViewById( R.id.tSecondReadingEDT );
        rSecondReadingEDT = (EditText) findViewById( R.id.rSecondReadingEDT );
        dSecondReadingEDT = (EditText) findViewById( R.id.dSecondReadingEDT );

        tWorshipWordEDT = (EditText) findViewById( R.id.tWorshipWordEDT );
        rWorshipWordEDT = (EditText) findViewById( R.id.rWorshipWordEDT );
        dWorshipWordEDT = (EditText) findViewById( R.id.dWorshipWordEDT );

        tGoodNewsEDT = (EditText) findViewById( R.id.tGoodNewsEDT );
        rGoodNewsEDT = (EditText) findViewById( R.id.rGoodNewsEDT );
        dGoodNewsEDT = (EditText) findViewById( R.id.dGoodNewsEDT );

        tOfferingPrayerEDT = (EditText) findViewById( R.id.tOfferingPrayerEDT );
        rOfferingPrayerEDT = (EditText) findViewById( R.id.rOfferingPrayerEDT );
        dOfferingPrayerEDT = (EditText) findViewById( R.id.dOfferingPrayerEDT );

        tPalaceSongEDT = (EditText) findViewById( R.id.tPalaceSongEDT );
        rPalaceSongEDT = (EditText) findViewById( R.id.rPalaceSongEDT );
        dPalaceSongEDT = (EditText) findViewById( R.id.dPalaceSongEDT );

        tClosingPrayerEDT = (EditText) findViewById( R.id.tClosingPrayerEDT );
        rClosingPrayerEDT = (EditText) findViewById( R.id.rClosingPrayerEDT );
        dClosingPrayerEDT = (EditText) findViewById( R.id.dClosingPrayerEDT );


        //feast Day edit text hooks

        feastTLifeOfSaintEDT = (EditText) findViewById( R.id.feast_tLifeOfSaintEDT );
        feastDLifeOfSaintEDT = (EditText) findViewById( R.id.feast_dLifeOfSaintEDT );

        feastTServiceSongEDT = (EditText) findViewById( R.id.feast_tServiceSongEDT );
        feastRServiceSongEDT = (EditText) findViewById( R.id.feast_rServiceSongEDT );
        feastDServiceSongEDT = (EditText) findViewById( R.id.feast_dServiceSongEDT );

        feastTOpeningPrayerEDT = (EditText) findViewById( R.id.feast_tOpeningPrayerEDT );
        feastROpeningPrayerEDT = (EditText) findViewById( R.id.feast_rOpeningPrayerEDT );
        feastDOpeningPrayerEDT = (EditText) findViewById( R.id.feast_dOpeningPrayerEDT );

        feastTFirstReadingEDT = (EditText) findViewById( R.id.feast_tFirstReadingEDT );
        feastRFirstReadingEDT = (EditText) findViewById( R.id.feast_rFirstReadingEDT );
        feastDFirstReadingEDT = (EditText) findViewById( R.id.feast_dFirstReadingEDT );

        feastTPsalmMusicEDT = (EditText) findViewById( R.id.feast_tPsalmMusicEDT );
        feastRPsalmMusicEDT = (EditText) findViewById( R.id.feast_rPsalmMusicEDT );
        feastDPsalmMusicEDT = (EditText) findViewById( R.id.feast_dPsalmMusicEDT );

        feastTSecondReadingEDT = (EditText) findViewById( R.id.feast_tSecondReadingEDT );
        feastRSecondReadingEDT = (EditText) findViewById( R.id.feast_rSecondReadingEDT );
        feastDSecondReadingEDT = (EditText) findViewById( R.id.feast_dSecondReadingEDT );

        feastTWorshipWordEDT = (EditText) findViewById( R.id.feast_tWorshipWordEDT );
        feastRWorshipWordEDT = (EditText) findViewById( R.id.feast_rWorshipWordEDT );
        feastDWorshipWordEDT = (EditText) findViewById( R.id.feast_dWorshipWordEDT );

        feastTGoodNewsEDT = (EditText) findViewById( R.id.feast_tGoodNewsEDT );
        feastRGoodNewsEDT = (EditText) findViewById( R.id.feast_rGoodNewsEDT );
        feastDGoodNewsEDT = (EditText) findViewById( R.id.feast_dGoodNewsEDT );

        feastTOfferingPrayerEDT = (EditText) findViewById( R.id.feast_tOfferingPrayerEDT );
        feastROfferingPrayerEDT = (EditText) findViewById( R.id.feast_rOfferingPrayerEDT );
        feastDOfferingPrayerEDT = (EditText) findViewById( R.id.feast_dOfferingPrayerEDT );

        feastTPalaceSongEDT = (EditText) findViewById( R.id.feast_tPalaceSongEDT );
        feastRPalaceSongEDT = (EditText) findViewById( R.id.feast_rPalaceSongEDT );
        feastDPalaceSongEDT = (EditText) findViewById( R.id.feast_dPalaceSongEDT );

        feastTClosingPrayerEDT = (EditText) findViewById( R.id.feast_tClosingPrayerEDT );
        feastRClosingPrayerEDT = (EditText) findViewById( R.id.feast_rClosingPrayerEDT );
        feastDClosingPrayerEDT = (EditText) findViewById( R.id.feast_dClosingPrayerEDT );




        progressDialog = new ProgressDialog(this);



        addPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new NewDatePickerFragment();
                dialogFragment.show(getSupportFragmentManager(),"Date Picker");
            }
        });

        btnInsertDailyReading.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertDailyReading();

            }


        } );



    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        final String currentD = new SimpleDateFormat( "EEE, d MMM yyyy" ).format( calendar.getTime() );

       // String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        addPickDate.setText(currentD);
    }

    private void insertDailyReading() {

       String date = addPickDate.getText().toString();
       final String year = addYearType.getSelectedItem().toString();
       String week = edtWeekType.getText().toString();
       String vestment = edtVestment.getText().toString();
       String feastDay = edtFestDay.getText().toString();

       //daily reading
       String tServiceSong = tServiceSongEDT.getText().toString();
       String rServiceSong = rServiceSongEDT.getText().toString();
       String dServiceSong = dServiceSongEDT.getText().toString();

       String tOpeningPrayer = tOpeningPrayerEDT.getText().toString();
       String rOpeningPrayer = rOpeningPrayerEDT.getText().toString();
       String dOpeningPrayer = dOpeningPrayerEDT.getText().toString();

       String tFirstReading = tFirstReadingEDT.getText().toString();
       String rFirstReading = rFirstReadingEDT.getText().toString();
       String dFirstReading = dFirstReadingEDT.getText().toString();


       String tPsalmMusic = tPsalmMusicEDT.getText().toString();
       String rPsalmMusic = rPsalmMusicEDT.getText().toString();
       String dPsalmMusic = dPsalmMusicEDT.getText().toString();

       String tSecondReading = tSecondReadingEDT.getText().toString();
       String rSecondReading = rSecondReadingEDT.getText().toString();
       String dSecondReading = dSecondReadingEDT.getText().toString();

       String tWorshipWord = tWorshipWordEDT.getText().toString();
       String rWorshipWord = rWorshipWordEDT.getText().toString();
       String dWorshipWord = dWorshipWordEDT.getText().toString();

       String tGoodNews = tGoodNewsEDT.getText().toString();
       String rGoodNews = rGoodNewsEDT.getText().toString();
       String dGoodNews = dGoodNewsEDT.getText().toString();

       String tOfferingPrayer = tOfferingPrayerEDT.getText().toString();
       String rOfferingPrayer = rOfferingPrayerEDT.getText().toString();
       String dOfferingPrayer = dOfferingPrayerEDT.getText().toString();

       String tPalaceSong = tPalaceSongEDT.getText().toString();
       String rPalaceSong = rPalaceSongEDT.getText().toString();
       String dPalaceSong = dPalaceSongEDT.getText().toString();

       String tClosingPrayer = tClosingPrayerEDT.getText().toString();
       String rClosingPrayer = rClosingPrayerEDT.getText().toString();
       String dClosingPrayer = dClosingPrayerEDT.getText().toString();


       //Feast Day
        String feast_tLifeOfSaint = feastTLifeOfSaintEDT.getText().toString();
        String feast_dLifeOfSaint = feastDLifeOfSaintEDT.getText().toString();

        String feast_tServiceSong = feastTServiceSongEDT.getText().toString();
        String feast_rServiceSong = feastRServiceSongEDT.getText().toString();
        String feast_dServiceSong = feastDServiceSongEDT.getText().toString();

        String feast_tOpeningPrayer = feastTOpeningPrayerEDT.getText().toString();
        String feast_rOpeningPrayer = feastROpeningPrayerEDT.getText().toString();
        String feast_dOpeningPrayer = feastDOpeningPrayerEDT.getText().toString();

        String feast_tFirstReading = feastTFirstReadingEDT.getText().toString();
        String feast_rFirstReading = feastRFirstReadingEDT.getText().toString();
        String feast_dFirstReading = feastDFirstReadingEDT.getText().toString();


        String feast_tPsalmMusic = feastTPsalmMusicEDT.getText().toString();
        String feast_rPsalmMusic = feastRPsalmMusicEDT.getText().toString();
        String feast_dPsalmMusic = feastDPsalmMusicEDT.getText().toString();

        String feast_tSecondReading = feastTSecondReadingEDT.getText().toString();
        String feast_rSecondReading = feastRSecondReadingEDT.getText().toString();
        String feast_dSecondReading = feastDSecondReadingEDT.getText().toString();

        String feast_tWorshipWord = feastTWorshipWordEDT.getText().toString();
        String feast_rWorshipWord = feastRWorshipWordEDT.getText().toString();
        String feast_dWorshipWord = feastDWorshipWordEDT.getText().toString();

        String feast_tGoodNews = feastTGoodNewsEDT.getText().toString();
        String feast_rGoodNews = feastRGoodNewsEDT.getText().toString();
        String feast_dGoodNews = feastDGoodNewsEDT.getText().toString();

        String feast_tOfferingPrayer = feastTOfferingPrayerEDT.getText().toString();
        String feast_rOfferingPrayer = feastROfferingPrayerEDT.getText().toString();
        String feast_dOfferingPrayer = feastDOfferingPrayerEDT.getText().toString();

        String feast_tPalaceSong = feastTPalaceSongEDT.getText().toString();
        String feast_rPalaceSong = feastRPalaceSongEDT.getText().toString();
        String feast_dPalaceSong = feastDPalaceSongEDT.getText().toString();

        String feast_tClosingPrayer = feastTClosingPrayerEDT.getText().toString();
        String feast_rClosingPrayer = feastRClosingPrayerEDT.getText().toString();
        String feast_dClosingPrayer = feastDClosingPrayerEDT.getText().toString();

       if (date.isEmpty() || date.equals( null )){
           addPickDate.setError( "দয়া করে তারিখ নির্ধারণ করুন" );
           addPickDate.requestFocus();
           return;
       }
       if (week.isEmpty()){
           edtWeekType.setError( "সপ্তাহের ধরনটি লিখুন" );
           edtWeekType.requestFocus();
           return;
       }
       if (vestment.isEmpty()){
           edtVestment.setError( "রঙ-টি লিখুন" );
           edtVestment.requestFocus();
           return;
       }

           progressDialog.show();
           progressDialog.setMessage("Inserting daily reading...");


           HashMap<Object,String> hashMap = new HashMap<>(  );
           hashMap.put( "date",date );
           hashMap.put( "yearType",year );
           hashMap.put( "weekType",week );
           hashMap.put( "vestment",vestment );
           hashMap.put( "feastDay",feastDay );


           //General time
            hashMap.put( "tServiceSong",tServiceSong );
            hashMap.put( "rServiceSong",rServiceSong );
            hashMap.put( "dServiceSong",dServiceSong );

            hashMap.put( "tOpeningPrayer",tOpeningPrayer );
            hashMap.put( "rOpeningPrayer",rOpeningPrayer );
            hashMap.put( "dOpeningPrayer",dOpeningPrayer );

            hashMap.put( "tFirstReading",tFirstReading );
            hashMap.put( "rFirstReading",rFirstReading );
            hashMap.put( "dFirstReading",dFirstReading );

            hashMap.put( "tPsalmMusic",tPsalmMusic );
            hashMap.put( "rPsalmMusic",rPsalmMusic );
            hashMap.put( "dPsalmMusic",dPsalmMusic );

            hashMap.put( "tSecondReading",tSecondReading );
            hashMap.put( "rSecondReading",rSecondReading );
            hashMap.put( "dSecondReading",dSecondReading );

            hashMap.put( "tWorshipWord",tWorshipWord );
            hashMap.put( "rWorshipWord",rWorshipWord );
            hashMap.put( "dWorshipWord",dWorshipWord );

            hashMap.put( "tGoodNews",tGoodNews );
            hashMap.put( "rGoodNews",rGoodNews );
            hashMap.put( "dGoodNews",dGoodNews );

            hashMap.put( "tOfferingPrayer",tOfferingPrayer );
            hashMap.put( "rOfferingPrayer",rOfferingPrayer );
            hashMap.put( "dOfferingPrayer",dOfferingPrayer );

            hashMap.put( "tPalaceSong",tPalaceSong );
            hashMap.put( "rPalaceSong",rPalaceSong );
            hashMap.put( "dPalaceSong",dPalaceSong );

            hashMap.put( "tClosingPrayer",tClosingPrayer );
            hashMap.put( "rClosingPrayer",rClosingPrayer );
            hashMap.put( "dClosingPrayer",dClosingPrayer );


        //Feast Day

        hashMap.put( "feast_tLifeOfSaint",feast_tLifeOfSaint );
        hashMap.put( "feast_dLifeOfSaint",feast_dLifeOfSaint );

        hashMap.put( "feast_tServiceSong",feast_tServiceSong );
        hashMap.put( "feast_rServiceSong",feast_rServiceSong );
        hashMap.put( "feast_dServiceSong",feast_dServiceSong );

        hashMap.put( "feast_tOpeningPrayer",feast_tOpeningPrayer );
        hashMap.put( "feast_rOpeningPrayer",feast_rOpeningPrayer );
        hashMap.put( "feast_dOpeningPrayer",feast_dOpeningPrayer );

        hashMap.put( "feast_tFirstReading",feast_tFirstReading );
        hashMap.put( "feast_rFirstReading",feast_rFirstReading );
        hashMap.put( "feast_dFirstReading",feast_dFirstReading );

        hashMap.put( "feast_tPsalmMusic",feast_tPsalmMusic );
        hashMap.put( "feast_rPsalmMusic",feast_rPsalmMusic );
        hashMap.put( "feast_dPsalmMusic",feast_dPsalmMusic );

        hashMap.put( "feast_tSecondReading",feast_tSecondReading );
        hashMap.put( "feast_rSecondReading",feast_rSecondReading );
        hashMap.put( "feast_dSecondReading",feast_dSecondReading );

        hashMap.put( "feast_tWorshipWord",feast_tWorshipWord );
        hashMap.put( "feast_rWorshipWord",feast_rWorshipWord );
        hashMap.put( "feast_dWorshipWord",feast_dWorshipWord );

        hashMap.put( "feast_tGoodNews",feast_tGoodNews );
        hashMap.put( "feast_rGoodNews",feast_rGoodNews );
        hashMap.put( "feast_dGoodNews",feast_dGoodNews );

        hashMap.put( "feast_tOfferingPrayer",feast_tOfferingPrayer );
        hashMap.put( "feast_rOfferingPrayer",feast_rOfferingPrayer );
        hashMap.put( "feast_dOfferingPrayer",feast_dOfferingPrayer );

        hashMap.put( "feast_tPalaceSong",feast_tPalaceSong );
        hashMap.put( "feast_rPalaceSong",feast_rPalaceSong );
        hashMap.put( "feast_dPalaceSong",feast_dPalaceSong );

        hashMap.put( "feast_tClosingPrayer",feast_tClosingPrayer );
        hashMap.put( "feast_rClosingPrayer",feast_rClosingPrayer );
        hashMap.put( "feast_dClosingPrayer",feast_dClosingPrayer );
           

           dailyReadingDBRef = FirebaseDatabase.getInstance().getReference( "dailyReading" );
           //DatabaseReference newReadingRef = dailyReadingDBRef.child( year );
           dailyReadingDBRef.child(date).setValue(hashMap).addOnSuccessListener( new OnSuccessListener<Void>() {
               @Override
               public void onSuccess(Void aVoid) {
                   progressDialog.dismiss();
                   Toast.makeText(getApplicationContext(), "Data inserted successfully", Toast.LENGTH_SHORT).show();

               }
           } ).addOnFailureListener( new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
                   progressDialog.dismiss();
                   Toast.makeText(AddDailyReadingActivity.this, "Upload failed!", Toast.LENGTH_SHORT).show();
               }
           } );


   

   }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String string = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}