package com.example.assignmentnew;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class FirstFragment extends Fragment {
    TextView profilefname,profilelname,profilegender,profiledob,profileemail,profilecity;
    FirebaseAuth fAuth;
    FirebaseUser user;
    FirebaseFirestore firestore;
    NavController navController;

    public FirstFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Profile");
        profilefname= view.findViewById(R.id.profilefirstname);
        profilelname=view.findViewById(R.id.profilelastname);
        profilegender=view.findViewById(R.id.pgender);
        profiledob=view.findViewById(R.id.pdate);
        profileemail=view.findViewById(R.id.pemail);
        profilecity=view.findViewById(R.id.pcity);
        //   Home=view.findViewById(R.id.home);
        firestore=FirebaseFirestore.getInstance();
        fAuth=FirebaseAuth.getInstance();


        //  navController = Navigation.findNavController(getActivity(),R.id.host_fragment);
        readFireStore();
    }

    public void readFireStore()
    {
        user=fAuth.getCurrentUser();
        DocumentReference docRef = firestore.collection("User").document(user.getUid());

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if (documentSnapshot.exists()) {

                    String FirstName=documentSnapshot.getString("FirstName");
                    String LastName=documentSnapshot.getString("LastName");
                    String Gender=documentSnapshot.getString("Gender");
                    String Date=documentSnapshot.getString("date");
                    String City=documentSnapshot.getString("City");
                    String Email=documentSnapshot.getString("Email");
                    profilefname.setText(FirstName);
                    profilelname.setText(LastName);
                    profileemail.setText(Email);
                    profiledob.setText(Date);
                    profilegender.setText(Gender);
                    profilecity.setText(City);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(),"error in data importing",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
