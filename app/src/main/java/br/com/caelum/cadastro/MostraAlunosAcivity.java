package br.com.caelum.cadastro;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MostraAlunosAcivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra_alunos_acivity);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        //obtem a instancia do google maps
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    //onde se trabalha com o map
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.getLista();
        dao.close();

        Localizador local = new Localizador(this);

        for (Aluno aluno : alunos) {
            LatLng coordAluno = local.GetCoordenadas(aluno.getEndereco());

            if (coordAluno != null){
                MarkerOptions mkOptions = new MarkerOptions()
                        .title(aluno.getNome()) // nome do pin
                        .snippet(aluno.getEndereco()) // descriçaõ do pin
                        .position(coordAluno); // posição do pin

                mMap.addMarker(mkOptions);
            }

        }





        // Add a marker in Sydney and move the camera
   /*     LatLng campinas = new LatLng(-23, -47);
        //adiciona um pin
        mMap.addMarker(new MarkerOptions().position(campinas).title("Marker in Campinas"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(campinas));*/

    }




}
