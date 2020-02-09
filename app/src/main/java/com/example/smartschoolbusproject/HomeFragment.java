package com.example.smartschoolbusproject;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class HomeFragment extends Fragment {

    private static final String CHANNEL_1_ID = "";
    private Button btnViewStudent;
    private Button btnTracker;
    private TextView testing;
    private NotificationManagerCompat notificationManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View rootView = inflater.inflate(
                R.layout.fragment_home, container, false);
        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnViewStudent = (Button) view.findViewById(R.id.btnViewStudent);
        btnTracker = (Button)view.findViewById(R.id.btnTracker);
        testing = view.findViewById(R.id.txtWelcome);

        btnViewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnTracker.setEnabled(false);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new StudentFragment()).commit();


            }
        });

        notificationManager = NotificationManagerCompat.from(getActivity());

        testing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnTracker.setEnabled(true);
                }
            });



       // if(btnTracker.isEnabled()) {
            btnTracker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Notification notification = new NotificationCompat.Builder(getActivity(),CHANNEL_1_ID)
//                            .setContentTitle("Map is being used...").setPriority(NotificationCompat.PRIORITY_HIGH)
//                            .setCategory(NotificationCompat.CATEGORY_MESSAGE).build();
//
//                    notificationManager.notify(1, notification);


                    Intent i = new Intent(getActivity(), Tracker.class);
                    startActivity(i);
                }
            });
       // }
    }

    }

