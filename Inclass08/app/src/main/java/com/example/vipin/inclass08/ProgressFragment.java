package com.example.vipin.inclass08;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProgressFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ProgressFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public ProgressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_progress, container, false);

        ProgressBar progressBar = view.findViewById(R.id.progressBar2);
        TextView loadingtv = view.findViewById(R.id.loadingtv);
        progressBar.setProgress(0);
        try {
            String url = mListener.geturl();
            ArrayList<Recipe> list = new Recipe_parser_AsyncTask((MainActivity) getActivity()).execute(url).get();
            if (list.size() != 0) {

                progressBar.setProgress(100);
                mListener.onFragmentInteraction2(list);
                mListener.gotofinalfragment();

            } else {

                Toast.makeText(getActivity(), "No Recipe Found", Toast.LENGTH_SHORT).show();
                mListener.gotofirst();

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            //    mListener.onFragmentInteraction(uri);
        }
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction2(ArrayList<Recipe> uri);

        void gotofinalfragment();

        String geturl();

        void gotofirst();
    }
}
