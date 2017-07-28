package br.com.caelum.cadastro;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Created by android6920 on 27/07/17.
 */

public class Localizador {

    private Geocoder geo;

    public Localizador(Context ctx) {
        this.geo = new Geocoder(ctx);
    }

    public LatLng GetCoordenadas(String enderecoAlu){
        try {

            List<Address> enderecos = geo.getFromLocationName(enderecoAlu,1);

            if (enderecos.isEmpty()){
                Address ende = enderecos.get(0);
                LatLng coord = new LatLng(ende.getLatitude(),ende.getLongitude());
                return coord;
            }

            return null;

        } catch (IOException e) {
            return null;
        }
    }
}
