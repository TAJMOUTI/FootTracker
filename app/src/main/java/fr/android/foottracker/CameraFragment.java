package fr.android.foottracker;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CameraFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CameraFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    //Constante
    private static final int RETOUR_PRENDRE_PHOTO = 1;

    //Propriétés
    private Button btnOpenCamera;
    private ImageView imageShowPicture;
    private String photoPath = null;
    private Button btnSavePicture;
    private Bitmap bitmap;
    ActivityResultLauncher<Intent> activityResultLauncher;

    public CameraFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CameraFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CameraFragment newInstance(String param1, String param2) {
        CameraFragment fragment = new CameraFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("on create");

        // Create lanucher variable inside onAttach or onCreate or global
  /*       activityResultLauncher = registerForActivityResult(
               new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RETOUR_OK && result.getData() != null) {

                            Bundle bundle = result.getData().getExtras();
                            Bitmap bitmap = (Bitmap) bundle.get("data");
                            imageShowPicture.setImageBitmap(bitmap);
                            // your operation....
                        }
                    }
                });*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Retourne les elements graphiques
        System.out.println("on create view");

        View view = inflater.inflate(R.layout.fragment_camera, container, false);
        btnOpenCamera = (Button) view.findViewById(R.id.btnOpenCamera);
        btnOpenCamera.setOnClickListener(this::takePicture);
        imageShowPicture = (ImageView) view.findViewById(R.id.imageShowPicture);
        btnSavePicture = (Button) view.findViewById(R.id.btnSavePicture);
        btnSavePicture.setOnClickListener(this::savePicture);

        return view;
    }

    /**
     * Evenement quand on click sur le bouton save
     */
    public void savePicture(View view){
        System.out.println("save a Picture Before");
        //Enregistrer la Photo
        MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, "nom image", "description");
    }

    /**
     * Evenement quand on click sur le bouton open cam
     */
    public void takePicture(View view){
        System.out.println("take a Picture Before");
        //creer un intent pour ouvrir une fenetre et prendre la photo
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // test pour controle que l'intent peut etre géré
        System.out.println("getActivity().getPackageManager()");
        System.out.println(getActivity().getPackageManager());
        System.out.println("INTENT getActivity().getPackageManager()");
        System.out.println(intent.resolveActivity(getActivity().getPackageManager()));

        if(intent.resolveActivity(getActivity().getPackageManager()) != null){
            System.out.println("INTENT NOT NULL");
            //créer un nom de fichier unique
            String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File photoDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            System.out.println("photo dir" + photoDir);

            try {
                File photoFile = File.createTempFile("photo" + time, ".jpg", photoDir);
                System.out.println("photo file" + photoFile);

                // enregistrerle chemin complet
                photoPath = photoFile.getAbsolutePath();
                //créer l'URI
                Uri photoUri = FileProvider.getUriForFile(getActivity().getApplicationContext(), getActivity().getPackageName()+".provider" , photoFile);
                System.out.println("photo photoUri" + photoUri);

                //Transfert Uri vers l'intent pour enregistrement photo dans fichier temporaire
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                //ouvrir le fragment par rapport a l'intent
                //activityResultLauncher.launch(intent);
                startActivityForResult(intent, RETOUR_PRENDRE_PHOTO);
                System.out.println("take a Picture After");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("On activity Result");
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RETOUR_PRENDRE_PHOTO && resultCode == Activity.RESULT_OK){
            bitmap = BitmapFactory.decodeFile(photoPath);
            imageShowPicture.setImageBitmap(bitmap);
        }

    }
}