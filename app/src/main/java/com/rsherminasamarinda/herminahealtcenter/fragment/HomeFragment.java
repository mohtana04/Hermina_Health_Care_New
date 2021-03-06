package com.rsherminasamarinda.herminahealtcenter.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.rsherminasamarinda.herminahealtcenter.FarmasiHeaderActivity;
import com.rsherminasamarinda.herminahealtcenter.FisioHeaderActivity;
import com.rsherminasamarinda.herminahealtcenter.KtkHeaderActivity;
import com.rsherminasamarinda.herminahealtcenter.LaboratoriumHeaderActivity;
import com.rsherminasamarinda.herminahealtcenter.R;
import com.rsherminasamarinda.herminahealtcenter.RadiologiHeaderActivity;
import com.rsherminasamarinda.herminahealtcenter.RiwayatActivity;
import com.rsherminasamarinda.herminahealtcenter.utils.SessionsManager;

public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    SessionsManager sessionsManager;
    ImageView imageViewAvatar, imageViewlab, imageViewRiwayat, imageViewRad, imageViewKtk, imageViewFis, imageViewFar;
    TextView textViewNmpasien, textViewNorm , textViewlab, textViewRiwayat, textViewRad, textViewKtk, textViewFis, textViewFar;
    String norm, nmpasien, gender;
    Context context;

    public HomeFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionsManager = new SessionsManager(getContext());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        imageViewAvatar = (ImageView) view.findViewById(R.id.IVavatar);
        imageViewlab = (ImageView) view.findViewById(R.id.btnimglab);
        textViewlab = (TextView) view.findViewById(R.id.btntvlab);
        imageViewRiwayat = (ImageView) view.findViewById(R.id.btnimgriwayat);
        textViewRiwayat = (TextView) view.findViewById(R.id.btntvriwayat);
        imageViewRad = (ImageView) view.findViewById(R.id.btnimgrad);
        textViewRad = (TextView) view.findViewById(R.id.btntvrad);
        imageViewKtk = (ImageView) view.findViewById(R.id.btnimgktk);
        textViewKtk = (TextView) view.findViewById(R.id.btntvktk);
        imageViewFis = (ImageView) view.findViewById(R.id.btnimgfis);
        textViewFis = (TextView) view.findViewById(R.id.btntvfis);
        textViewNmpasien = (TextView) view.findViewById(R.id.TVnamapasien);
        textViewNorm = (TextView) view.findViewById(R.id.TVcmpasien);
        imageViewFar = (ImageView) view.findViewById(R.id.btnimgfar);
        textViewFar = (TextView) view.findViewById(R.id.btntvfar);


        norm = sessionsManager.getUserNomr();
        nmpasien = sessionsManager.getUserName();
        gender = sessionsManager.getUserGender();


        textViewNorm.setText(norm);
        textViewNmpasien.setText(nmpasien);

        if (gender.equals("L")){
            imageViewAvatar.setImageResource(R.drawable.userman);
        } else {
            imageViewAvatar.setImageResource(R.drawable.usergirl);
        }


        imageViewlab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LaboratoriumHeaderActivity.class);
                startActivity(intent);
            }
        });

        textViewlab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LaboratoriumHeaderActivity.class);
                startActivity(intent);
            }
        });

        imageViewRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), RiwayatActivity.class));
            }
        });

        textViewRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), RiwayatActivity.class));
            }
        });

        imageViewRad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), RadiologiHeaderActivity.class));
            }
        });


        textViewRad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), RadiologiHeaderActivity.class));
            }
        });

        imageViewKtk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), KtkHeaderActivity.class));
            }
        });


        textViewKtk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), KtkHeaderActivity.class));
            }
        });

        imageViewKtk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), KtkHeaderActivity.class));
            }
        });


        textViewKtk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), KtkHeaderActivity.class));
            }
        });

        imageViewFis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FisioHeaderActivity.class));
            }
        });


        textViewFis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FisioHeaderActivity.class));
            }
        });

        imageViewFar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FarmasiHeaderActivity.class));
            }
        });


        textViewFar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FarmasiHeaderActivity.class));
            }
        });

        return view;

    }


}