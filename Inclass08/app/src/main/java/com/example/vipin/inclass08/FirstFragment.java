package com.example.vipin.inclass08;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FirstFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

//this is done by me
public class FirstFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    LinearLayout layout;
    RecyclerView rcv;
    ArrayList<String> arrayList = new ArrayList<String>();
    static FloatingActionButton fab;
    FloatingActionButton fab2;
    static EditText ed;
    EditText ed2;
    EditText dish;
    Button btn;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Recipe> list;

    private OnFragmentInteractionListener mListener;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_first, container, false);


        rcv = (RecyclerView) view.findViewById(R.id.rcv);
        fab = (FloatingActionButton) view.findViewById(R.id.fab1);

        fab2 = (FloatingActionButton) view.findViewById(R.id.fab2);
        ed = (EditText) view.findViewById(R.id.ed);

        ed2 = (EditText) view.findViewById(R.id.ed2);
        dish = (EditText) view.findViewById(R.id.dish_ed);
        dish.requestFocus();
        layoutManager = new LinearLayoutManager(getActivity());
        rcv.setLayoutManager(layoutManager);
        mListener.onFragmentInteraction1(arrayList);
        RecyclerView.Adapter adapter = new MyAdapter(arrayList);
        rcv.setAdapter(adapter);

        //arrayList = new ArrayList<>();
        btn = (Button) view.findViewById(R.id.searchbtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rcv.setHasFixedSize(true);
                if (!ed.getText().toString().matches("")) {
                    if ((arrayList.size() < 5)) {
                        arrayList.add(ed.getText().toString());
                        ed.setText("");
                        // use a linear layout manager
                        RecyclerView.Adapter adapter = new MyAdapter(arrayList);
                        rcv.setAdapter(adapter);
                        ed.requestFocus();
                        if (arrayList.size() == 5) {
                            fab.setVisibility(View.GONE);
                            ed.setVisibility(View.GONE);
                            Toast.makeText(getActivity(), "You can only add 5 ingredients", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getActivity(), "You can only add 5 ingredients", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Please enter something to add", Toast.LENGTH_SHORT).show();
                }

            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dish.getText().toString().matches("")) {
                    Toast.makeText(getActivity(), "Dish cannot be blank", Toast.LENGTH_SHORT).show();
                } else if (arrayList.size() == 0) {
                    Toast.makeText(getActivity(), "Please enter one or more ingredients", Toast.LENGTH_SHORT).show();
                } else {
                    for (int j = 0; j < arrayList.size(); j++) {

                        if (!arrayList.get(j).matches("") && !dish.getText().toString().matches("")) {

                            String url = create_base_url(arrayList, dish.getText().toString());

                            Log.d("demo4", arrayList.toString());
                            mListener.gotonextfragment();
                            mListener.seturl(url);

                        }
                    }
                }

            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(list);
        }
    }

    private String create_base_url(ArrayList<String> ingredientlist, String dish) {
        String baseurl = "http://www.recipepuppy.com/";
        String encoded_url = null;
        for (int i = 0; i < ingredientlist.size(); i++) {
            if (i == 0) {
                encoded_url = baseurl + "/api/?i=" + ingredientlist.get(i);

            } else {
                encoded_url = encoded_url + "," + ingredientlist.get(i);
            }

            if (i == 4) {
                encoded_url = encoded_url + "&q=" + dish;
            }
        }
        return encoded_url;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(ArrayList<Recipe> list);

        void gotonextfragment();

        void onFragmentInteraction1(ArrayList<String> arrayList);

        void seturl(String url);
    }
}
