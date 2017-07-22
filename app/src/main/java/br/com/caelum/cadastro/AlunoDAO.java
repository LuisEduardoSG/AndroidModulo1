package br.com.caelum.cadastro;

import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by android6920 on 20/07/17.
 */

 class AlunoDAO extends SQLiteOpenHelper{
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
                " CREATE TABLE "  + TABELA +
                " (id INTEGER PRIMARY KEY, " +
                " nome      TEXT NOT NULL, " +
                " telefone  TEXT, " +
                " endereco  TEXT, " +
                " site      TEXT, " +
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
                                            //nullHack, garantia de preencher pelo menos um campo da linha(default)
    }


    public  List<Aluno> getLista(){
        List<Aluno> alunos = new ArrayList<Aluno>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = getReadableDatabase().rawQuery("SELECT * FROM " + TABELA + ";",null);

        while (c.moveToNext()){
            Aluno aluno = new Aluno();

            aluno.setId(c.getLong(c.getColumnIndex("id")));
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
            aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
            aluno.setSite(c.getString(c.getColumnIndex("site")));
            aluno.setNota(c.getDouble(c.getColumnIndex("nota")));

            alunos.add(aluno);
        }

        c.close();
        return alunos;
    }


    public void deletar(Aluno alunoSelec) {
        //deleta da base, o aluno
        getWritableDatabase().delete(TABELA,"id = ?", new String[] {alunoSelec.getId().toString()});
    }

    public void altera(Aluno aluno) {
        //instancia o content
        ContentValues values = new ContentValues();

        //preenche o content
        values.put("nome"       ,aluno.getNome());
        values.put("telefone"   ,aluno.getTelefone());
        values.put("endereco"   ,aluno.getEndereco());
        values.put("site"       ,aluno.getSite());
        values.put("nota"       ,aluno.getNota());

        String[] args = {aluno.getId().toString()};

        // método de insert da classe SQLiteDatebase
        getWritableDatabase().update(TABELA,values,"id=?",args);

    }
}
