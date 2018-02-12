package com.finitydev.jewishrideshare;

import android.app.DatePickerDialog;
import android.support.v4.app.Fragment;
import android.app.TimePickerDialog;
import android.support.annotation.NonNull;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;



/**
 * A login screen that offers login via email/password.
 */
public class PostRideFragment extends Fragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{


    private static final String TAG = "PostRideFragment";



    private AutoCompleteTextView startCityTextView;
    private AutoCompleteTextView endCityTextView;
    private AutoCompleteTextView startStateTextView;
    private AutoCompleteTextView endStateTextView;
    private EditText notesTextView;
    private Spinner spaceSpinner;
    private Spinner genderSpinner;
    private Button timeButton;
    private Button dateButton;
    private Button postRideButton;
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

        startCityTextView = view.findViewById(R.id.leaving_city);
        endCityTextView = view.findViewById(R.id.to_city);
        startStateTextView = view.findViewById(R.id.leaving_state);
        endStateTextView = view.findViewById(R.id.to_state);
        spaceSpinner = view.findViewById(R.id.space);
        timeButton = view.findViewById(R.id.time);
        dateButton = view.findViewById(R.id.enter_date);
        postRideButton = view.findViewById(R.id.post_ride_button);
        notesTextView = view.findViewById(R.id.notes);


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




        timeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment newFragment = new TimePickerFragment();
                Bundle args = new Bundle();
                args.putString("tag", DrawerRootActivity.POST_RIDE);
                newFragment.setArguments(args);
                newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");

            }
        });

        dateButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment newFragment = new DatePickerFragment();
                Bundle args = new Bundle();
                args.putString("tag", DrawerRootActivity.POST_RIDE);
                newFragment.setArguments(args);

                newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
            }
        });

        postRideButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                //must do validation!!
                String startCity = startCityTextView.getText().toString();
                String endCity = endCityTextView.getText().toString();
                String startState = startStateTextView.getText().toString();
                String endState = endStateTextView.getText().toString();
                String notes = notesTextView.getText().toString();
                int space = Integer.parseInt(spaceText);







                Ride ride = new Ride(startCity,startState,endCity,endState,myDate,myTime,space, genderText ,notes);

                //Ride ride = new Ride(startCity,startState,endCity,endState,time, space, genderText, notes);


                db.collection("rides")
                        .add(ride)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });



            }
        });














    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_postride, container, false);
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

