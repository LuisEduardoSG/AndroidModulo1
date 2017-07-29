package br.com.caelum.cadastro;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by android6920 on 28/07/17.
 */

public class PreferenciasDAO {

    private final SharedPreferences prefs;


    public PreferenciasDAO(Context ctx) {
        this.prefs = ctx.getSharedPreferences("opções", Context.MODE_PRIVATE);
    }


    public void salva(int ordem){
        boolean isAlfabetica = ordem == 0;

        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("alfabetica", isAlfabetica);
        //commit retorna true ou false;
        //editor.commit();
        editor.apply();
    }

    public int getOrdem() {
        return isAlfabetica() ? 0 : 1;
    }


    public boolean isAlfabetica(){
        return prefs.getBoolean("alfabetica",false);
    }
}
