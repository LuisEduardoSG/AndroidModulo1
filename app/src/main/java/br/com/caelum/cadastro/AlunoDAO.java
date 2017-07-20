package br.com.caelum.cadastro;

import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by android6920 on 20/07/17.
 */

public class AlunoDAO extends SQLiteOpenHelper{
    //constantes que serão usadas várias vezes, evitando
    // repetição dos teus valores no corpo da classe
    private static final int VERSAO = 1;
    private static final String TABELA = "Alunos";
    private static final String DATABASE = "CadastroCaelum";

    //construtor
    public AlunoDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }
    //criando o banco
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String ddl =
                " CREATE TABLE"  + TABELA +
                " ( id INTEGER PRIMARY KEY, " +
                " nome      TEXT NOT NULL, " +
                " telefone  TEXT " +
                " endereco  TEXT " +
                " site      TEXT " +
                " nota      REAL );";

        sqLiteDatabase.execSQL(ddl);
    }


    @Override                                            // v = versão
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int vAntiga, int vNova) {
        String sql  = "DROP TABLE IF EXISTS " + TABELA ;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);

    }

    //armazenar as informações do aluno num obj do tipo ContentValues
    // que auxilia no insert no banco
    public void insere(Aluno aluno){
        //instancia o content
        ContentValues values = new ContentValues();

        //preenche o content
        values.put("nome"       ,aluno.getNome());
        values.put("telefone"   ,aluno.getTelefone());
        values.put("endereco"   ,aluno.getEndereco());
        values.put("site"       ,aluno.getSite());
        values.put("nota"       ,aluno.getNota());

        // método de insert da classe SQLiteDatebase
        getWritableDatabase().insert(TABELA,null,values);

    }
}
