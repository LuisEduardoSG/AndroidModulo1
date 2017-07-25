package br.com.caelum.cadastro;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by android6920 on 25/07/17.
 */
//Ativ 9.3 Pag 133 - Override do metodo de recebimento de  notificações
public class SMSReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent data) {
                                            //chave para pegar o valor do getExtra (padrão da documentação)
        Object[] pdus = (Object []) data.getExtras().get("pdus");

        byte[] pdu = (byte[]) pdus[0] ;
                                    //PDU = Protocol Data Unity / cada pacote de no max 160 caracteres de sms
        SmsMessage sms = SmsMessage.createFromPdu(pdu);
        String telefone = sms.getDisplayOriginatingAddress();
        AlunoDAO dao = new AlunoDAO(context);
        Toast.makeText(context,"Msg de Aluno: " + sms.getMessageBody(),Toast.LENGTH_SHORT).show();
        if (dao.isAluno(telefone)){                     //pasta raw, utilizada para armazenar arquivos
                                                        // que não tenham formato definidos previamente
            MediaPlayer mp = MediaPlayer.create(context,R.raw.msg);
            mp.start();
            Toast.makeText(context,"Msg de Aluno: " + sms.getMessageBody(),Toast.LENGTH_SHORT).show();
        }

    }
}
