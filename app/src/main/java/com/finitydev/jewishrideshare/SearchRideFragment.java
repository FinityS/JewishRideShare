package com.finitydev.jewishrideshare;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchRideFragment extends Fragment  implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{


    private static final String TAG = "SearchRideFragment";



    private AutoCompleteTextView startCityTextView;
    private AutoCompleteTextView endCityTextView;
    private AutoCompleteTextView startStateTextView;
    private AutoCompleteTextView endStateTextView;
    private Spinner spaceSpinner;
    private Spinner genderSpinner;
    private Button timeButton;
    private Button dateButton;
    private Button searchRideButton;
    private String spaceText;
    private String genderText;


    private Date myDate;
    private Time myTime;

    private FirebaseFirestore db;







    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);










        db = FirebaseFirestore.getInstance();



    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);

        View view = getView();

        startCityTextView = view.findViewById(R.id.leaving_city_search);
        endCityTextView = view.findViewById(R.id.to_city_search);
        startStateTextView = view.findViewById(R.id.leaving_state_search);
        endStateTextView = view.findViewById(R.id.to_state_search);
        spaceSpinner = view.findViewById(R.id.space_search);
        timeButton = view.findViewById(R.id.time_search);
        dateButton = view.findViewById(R.id.enter_date_search);
        searchRideButton = view.findViewById(R.id.search_ride_button);



        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.space_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spaceSpinner.setAdapter(adapter);
        spaceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spaceText = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        genderSpinner = view.findViewById(R.id.gender);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterGender = ArrayAdapter.createFromResource(getActivity(),
                R.array.gender_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        genderSpinner.setAdapter(adapterGender);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                genderText = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment newFragment = new TimePickerFragment();
                Bundle args = new Bundle();
                args.putString("tag", DrawerRootActivity.SEARCH_RIDE);
                newFragment.setArguments(args);
                newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");

            }
        });

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment newFragment = new DatePickerFragment();
                Bundle args = new Bundle();
                args.putString("tag", DrawerRootActivity.SEARCH_RIDE);
                newFragment.setArguments(args);

                newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
            }
        });

        searchRideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //must do validation!!
                String startCity = startCityTextView.getText().toString();
                String endCity = endCityTextView.getText().toString();
                String startState = startStateTextView.getText().toString();
                String endState = endStateTextView.getText().toString();

                int space = Integer.parseInt(spaceText);







                //Ride ride = new Ride(startCity,startState,endCity,endState,myDate,myTime,space, genderText);

                //Ride ride = new Ride(startCity,startState,endCity,endState,time, space, genderText, notes);

                // Create a reference to the rides collection
                CollectionReference ridesRef = db.collection("rides");

                Query query = ridesRef
                        .whereEqualTo("startingCity", startCity)
                        .whereEqualTo("startingState",startState)
                        .whereEqualTo("endingCity",endCity)
                        .whereEqualTo("endingState", endState);

                query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot documentList = task.getResult();
                            if (document != null) {
                                Log.d(TAG, "DocumentSnapshot data: " + task.getResult().getData());
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });








            }
        });














    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_ride, container, false);
    }



    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        myDate = new Date(month + 1, dayOfMonth);
        dateButton.setText(myDate.toString());



    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        myTime = new Time(hourOfDay, minute);
        timeButton.setText(myTime.toString());



    }
}

