package com.example.assignmentnew;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class registerFragment extends Fragment {


    EditText edt_email,edt_pass,edt_firstname,edt_lastname,edt_city,edt_mobilenumber,edt_cpass,edt_date,edt_gender;
    Button btn_register;

    private FirebaseAuth auth;
    private FirebaseFirestore firestore;
    private NavController navController;

    public registerFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edt_email = view.findViewById(R.id.email);
        edt_pass = view.findViewById(R.id.password);
        edt_firstname = view.findViewById(R.id.firstName);
        edt_lastname = view.findViewById(R.id.lastName);
        edt_city = view.findViewById(R.id.city);
        btn_register = view.findViewById(R.id.btn_register);

        edt_date=view.findViewById(R.id.date);
        edt_gender=view.findViewById(R.id.gender);
        edt_mobilenumber = view.findViewById(R.id.mobile);
        edt_cpass = view.findViewById(R.id.Cpassword);
        navController=Navigation.findNavController(getActivity(),R.id.host_fragment);


        btn_register.setOnClickListener(view1 -> {

            if (!checkEmptyfields()) {
                if(edt_gender.getText().toString().length()>1)
                {
                    edt_gender.setError("Please Enter M or F");
                    edt_gender.requestFocus();
                }
                else {
                    if (edt_date.getText().toString().length() < 10) {
                        edt_date.setError("Invalid date format");
                        edt_date.requestFocus();
                    } else {
                        if (edt_mobilenumber.getText().toString().length() < 10) {
                            edt_mobilenumber.setError("Invalid phone Number");
                            edt_mobilenumber.requestFocus();
                        } else {
                            if (edt_pass.getText().length() < 6) {
                                edt_pass.setError("Invalid Password,Password should be of 6 characters");
                                edt_pass.requestFocus();
                            } else {
                                if (!edt_pass.getText().toString().equals(edt_cpass.getText().toString())) {
                                    edt_cpass.setError("Password doesnot match!");
                                    edt_cpass.requestFocus();
                                } else {
                                    String email = edt_email.getText().toString();
                                    String pass = edt_pass.getText().toString();
                                    String firstname = edt_firstname.getText().toString();
                                    String lastname = edt_lastname.getText().toString();
                                    String city = edt_city.getText().toString();
                                    String mobilenumber = edt_mobilenumber.getText().toString();
                                    String gender = edt_gender.getText().toString();
                                    String date = edt_date.getText().toString();


                                    Person person = new Person(email,pass,firstname,lastname,city,mobilenumber,gender, date);

                                    createUser(person);
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    public boolean checkEmptyfields(){
        if(TextUtils.isEmpty(edt_email.getText().toString()))
        {
            edt_email.setError("Email cannot be empty!");
            edt_email.requestFocus();
            return true;
        }else if(TextUtils.isEmpty(edt_pass.getText().toString()))
        {
            edt_pass.setError("Password cannot be empty!");
            edt_pass.requestFocus();
            return true;
        }else if(TextUtils.isEmpty(edt_cpass.getText().toString()))
        {
            edt_cpass.setError("Confirm Password cannot be empty!");
            edt_cpass.requestFocus();
            return true;
        }else if(TextUtils.isEmpty(edt_firstname.getText().toString()))
        {
            edt_firstname.setError("Firstname cannot be empty!");
            edt_firstname.requestFocus();
            return true;
        }else if(TextUtils.isEmpty(edt_lastname.getText().toString()))
        {
            edt_lastname.setError("Lastname cannot be empty!");
            edt_lastname.requestFocus();
            return true;
        }else if(TextUtils.isEmpty(edt_city.getText().toString()))
        {
            edt_city.setError("City cannot be empty!");
            edt_city.requestFocus();
            return true;
        }else if(TextUtils.isEmpty(edt_mobilenumber.getText().toString()))
        {
            edt_mobilenumber.setError("Mobile Number cannot be empty!");
            edt_mobilenumber.requestFocus();
            return true;
        }
        else if(TextUtils.isEmpty(edt_date.getText().toString()))
        {
            edt_date.setError("Date cannot be empty!");
            edt_date.requestFocus();
            return true;
        }
        else if(TextUtils.isEmpty(edt_gender.getText().toString()))
        {
            edt_gender.setError("Date cannot be empty!");
            edt_gender.requestFocus();
            return true;
        }



        return false;
    }

    public void createUser(Person person)
    {
        auth.createUserWithEmailAndPassword(person.getEmail(),person.getPass())
                .addOnCompleteListener(getActivity(), task -> {

                    if(task.isSuccessful())
                    {
                        FirebaseUser firebaseUser=auth.getCurrentUser();
                        writeFirestore(person,firebaseUser);
                        Toast.makeText(getActivity().getApplicationContext(),"registration is successfull",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity().getApplicationContext(),"registration error",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void writeFirestore(Person person,FirebaseUser firebaseUser)
    {
        Map<String,Object> userMap= new HashMap<>();
        userMap.put("FirstName",person.getFirstname());
        userMap.put("LastName",person.getLastname());
        userMap.put("Email",person.getEmail());
        userMap.put("Mobilenumber",person.getMobilenumber());
        userMap.put("City",person.getCity());
        userMap.put("Gender",person.getGender());
        userMap.put("date",person.getDate());
        // userMap.put("Date",person.getDate());
        //  userMap.put("Gender",person.getGender());

        firestore.collection("User").document(firebaseUser.getUid())
                .set(userMap).addOnCompleteListener(getActivity(),task -> {
            if(task.isSuccessful())
            {
                Toast.makeText(getActivity().getApplicationContext(),"registration is successful",Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                navController.navigate(R.id.loginFragment);
            }else
            {
                Toast.makeText(getActivity().getApplicationContext(),"Firestore Error!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}