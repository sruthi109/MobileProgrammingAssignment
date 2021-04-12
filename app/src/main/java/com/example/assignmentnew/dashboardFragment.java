package com.example.assignmentnew;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class dashboardFragment extends Fragment {


    FirebaseUser user;
    FirebaseFirestore fireStore;
    NavController navController;
    FirebaseAuth firebaseAuth;
    TextView txt_Name;



    public dashboardFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         getActivity().setTitle("Welcome");

        txt_Name = view.findViewById(R.id.txt_welcome);
       firebaseAuth=FirebaseAuth.getInstance();
       fireStore=FirebaseFirestore.getInstance();

        readFireStore();

    }

    public void readFireStore()
    {
        user=firebaseAuth.getCurrentUser();
        DocumentReference docRef = fireStore.collection("User").document(user.getUid());

        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful())
            {
                DocumentSnapshot doc = task.getResult();

                if (doc.exists())
                {
                    Log.d("DashboardFragment",doc.getData().toString());

                    txt_Name.setText("Welcome "+doc.get("FirstName") +" "+doc.get("LastName") +" !");


                }

            }
        });
    }
}