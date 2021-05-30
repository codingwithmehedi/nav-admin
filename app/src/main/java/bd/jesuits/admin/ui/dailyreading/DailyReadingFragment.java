package bd.jesuits.admin.ui.dailyreading;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import bd.jesuits.admin.R;

public class DailyReadingFragment extends Fragment {

    public DailyReadingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(  true);
        super.onCreate( savedInstanceState );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_daily_reading, container, false );

        FloatingActionButton fab = view.findViewById( R.id.fab_dailyReading );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity( new Intent( getContext(),AddDailyReadingActivity.class ) );
            }
        } );

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.daily_reading_menu,menu);
        super.onCreateOptionsMenu( menu, inflater );

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       if(item.getItemId()==R.id.item_daily_reading){
               // Intent intent = new Intent(getContext(), .class );
               // startActivity( intent );
              //  getActivity().finish();
           Toast.makeText( getContext(), "Daily Reading Menu Clicked", Toast.LENGTH_SHORT ).show();

        }
        return super.onOptionsItemSelected(item);
    }
}