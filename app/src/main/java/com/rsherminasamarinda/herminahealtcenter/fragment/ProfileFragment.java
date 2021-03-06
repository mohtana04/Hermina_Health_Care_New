package com.rsherminasamarinda.herminahealtcenter.fragment;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.rsherminasamarinda.herminahealtcenter.R;
import com.rsherminasamarinda.herminahealtcenter.utils.SessionsManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    TextView textViewnamapasienkartu, textViewnomorpasienkartu;
    SessionsManager sessionsManager;
    ImageView imageViewbarcodecm;
    Button textViewLogout;

    String namapasien, nomorkartu;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();

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

        namapasien = sessionsManager.getUserName();
        nomorkartu = sessionsManager.getUserNomr();

        final View view = inflater.inflate(R.layout.fragment_profile, container, false);
        sessionsManager = new SessionsManager(getContext());

        imageViewbarcodecm = (ImageView) view.findViewById(R.id.IVbarcodekartupengaturan);
        textViewLogout = (Button) view.findViewById(R.id.TVlogoutpengaturan);
        textViewnamapasienkartu = (TextView) view.findViewById(R.id.TVnamapasienkartupengaturan);
        textViewnomorpasienkartu = (TextView) view.findViewById(R.id.TVnormkartupengaturan);
        textViewnamapasienkartu.setText(namapasien);
        textViewnomorpasienkartu.setText(nomorkartu);

        imageViewbarcodecm.post(new Runnable() {
            @Override
            public void run() {
                generatebarcode(textViewnomorpasienkartu);
            }
        });

        textViewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionsManager.logoutUser();
                getActivity().finish();
            }
        });
        return view;
    }


    public void generatebarcode (TextView textViewnomorpasienkartu){
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(textViewnomorpasienkartu.getText().toString(), BarcodeFormat.CODE_128, imageViewbarcodecm.getWidth(), imageViewbarcodecm.getHeight());
            Bitmap bitmap = Bitmap.createBitmap(imageViewbarcodecm.getWidth(),imageViewbarcodecm.getHeight(),Bitmap.Config.RGB_565);
            for (int i = 0; i<imageViewbarcodecm.getWidth(); i++){
                for (int j = 0; j<imageViewbarcodecm.getHeight(); j++){
                    bitmap.setPixel(i,j,bitMatrix.get(i,j)? Color.BLACK:Color.WHITE);
                }
            }
            imageViewbarcodecm.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}