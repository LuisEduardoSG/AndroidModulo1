package br.com.caelum.cadastro;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by android6920 on 25/07/17.
 */
//Ativ 9.3 Pag 133 - Override do metodo de recebimento de  notificações
public class SMSReceiver extends BroadcastReceiver{

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent data) {
        Bundle bundle = data.getExtras();
                                            //chave para pegar o valor do getExtra (padrão da documentação)
        Object[] pdus = (Object []) data.getExtras().get("pdus");

        byte[] pdu = (byte[]) pdus[0] ;

        String format = (String) bundle.get("format");
                                    //PDU = Protocol Data Unity / cada pacote de no max 160 caracteres de sms
        SmsMessage sms = SmsMessage.createFromPdu(pdu,format);
        String telefone = sms.getDisplayOriginatingAddress();
        AlunoDAO dao = new AlunoDAO(context);

        if (dao.isAluno(telefone)){                     //pasta raw, utilizada para armazenar arquivos
                                                        // que não tenham formato definidos previamente
            MediaPlayer mp = MediaPlayer.create(context,R.raw.msg);
            mp.start();
            Toast.makeText(context,"Msg de Aluno: " + sms.getMessageBody(),Toast.LENGTH_SHORT).show();
        }

    }
}
