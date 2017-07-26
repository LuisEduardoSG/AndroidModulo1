package br.com.caelum.cadastro;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by android6920 on 24/07/17.
 */

public class ListaAlunosAdapter extends BaseAdapter{

    private List<Aluno> alunos;
    private Activity activity;

    public ListaAlunosAdapter(List<Aluno> alunos, Activity act ) {
        this.activity = act;
        this.alunos = alunos;
    }
    // total de itens da lista
    @Override
    public int getCount() {
        // pega o tamanho da lista
        return alunos.size();
    }
    //retorna um objeto
    @Override
    public Object getItem(int i) {
        //retorna o aluno na posição i
        return alunos.get(i);
    }
    //pega o id do objeto
    @Override
    public long getItemId(int i) {
        //retorna o id do aluno daquela posição i
        return alunos.get(i).getId();
    }

    @Override                       // v-- Melhorando a performance, nula da primeira vez, e a cada rolagem da view, há uma reutilização
    public View getView(int posicao, View view, ViewGroup parent) {
        // pega um aluno, da lista de alunos de acordo com a posição
        Aluno aluno = alunos.get(posicao);

        //inicializa a view
        view = activity.getLayoutInflater().inflate(
                 R.layout.item, parent, false);
        view.setBackgroundColor(Color.LTGRAY);
    /*    if (posicao % 2 == 0){
            view.setBackgroundColor(activity.getResources().
                    getColor(R.color.linha_par, activity.getTheme()));
        }else{
            view.setBackgroundColor(activity.getResources().
                    getColor(R.color.linha_par, activity.getTheme()));
        }*/


        //preenche o text view do nome
        TextView nome = (TextView) view.findViewById(R.id.nome);
        nome.setText(aluno.getNome());

        //preenche o imageview
        Bitmap bmFoto = null;
        if (aluno.getCaminhoFoto() != null){
            bmFoto = BitmapFactory.decodeFile(aluno.getCaminhoFoto());
            //reduzindo o tamanho da foto                      //filtro ou não na foto
            bmFoto = Bitmap.createScaledBitmap(bmFoto, 200,200,true);
        }else{
            //adiocionando foto default caso não tenha nenhuma
            //decodeResource recebe a lista de resources  e a imagem específica
            bmFoto = BitmapFactory.decodeResource(
                    activity.getResources(),R.drawable.ic_no_image);
            bmFoto = Bitmap.createScaledBitmap(bmFoto,200,200,true);
        }
        ImageView foto = (ImageView) view.findViewById(R.id.img);
        //setando a imagem na view
        foto.setImageBitmap(bmFoto);

        return view;
    }

}
