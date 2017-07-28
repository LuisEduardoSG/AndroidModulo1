package br.com.caelum.cadastro;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by android6920 on 28/07/17.
 */
                            //Passo 2 CallBack de Conexão        Passo 4 receber a Localização
public class GPS implements GoogleApiClient.ConnectionCallbacks, LocationListener{
                            //fim passo 2                       fim passo 4
    //faz a conexão com os serviços do google
    private GoogleApiClient client;
    private MostraAlunosAcivity activity;

    //passo 1 Criar a Conexão  com o Location Service
    public GPS(MostraAlunosAcivity act) {
        client = new GoogleApiClient.Builder(act)
                .addApi(LocationServices.API)
                .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) this)
                .build();
        client.connect();

        this.activity = act;
    }


    //fim passo1


    //Passo 3 - Setando Parâmetros de configuração
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //CONFIGURAÇÕES PRINCIPAIS PARA O GPS
        //1. configurar a frequencia de atualização (medida em milisegundos) setInterval
        //2. Prioridade de Precisão (High_Accuracy), consumo (Low_Battery)  eo balanceado (Balanced) setPriority
            // nas permissões: ACCESS_FINE_LOCAITON = HIGH ACCURAY | ACCESS_COARSE_LOCATION  = LOW BATTERY
        //3. Descolamento mínimo ( medido em metros) setSmallestDisplacement
        LocationRequest requestLocal = LocationRequest.create()
                .setInterval(5000)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setSmallestDisplacement(10);

        //Pa
        LocationServices.FusedLocationApi.requestLocationUpdates(client,requestLocal,this);



    }
    // FIM passo 3
    @Override
    public void onConnectionSuspended(int i) {

    }

    public void onLocationChanged (Location locate){
        activity.centralizaNo(new LatLng(locate.getLatitude(),locate.getLongitude()));
    }


}
