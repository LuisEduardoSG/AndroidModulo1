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


    private static final int VERSAO = 2;//alterado na Ativ 7.5 Pag124
    private static final String TABELA = "Alunos";
    private static final String DATABASE = "CadastroCaelum";
    private Context ctx;

    //construtor
    public AlunoDAO(Context context) {

        super(context, DATABASE, null, VERSAO);
        this.ctx = context;
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
                        " nota      REAL, " +
                        " caminhoFoto TEXT );";

        sqLiteDatabase.execSQL(ddl);
    }

    //alterado na Ativ 7.5 Pag124
    @Override                                            // v = versão
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int vAntiga, int vNova) {
        String sql  = "ALTER TABLE " + TABELA + " ADD COLUMN caminhoFoto TEXT;";
        sqLiteDatabase.execSQL(sql);


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
        //alterado na Ativ 7.5 Pag124
        values.put("caminhoFoto",aluno.getCaminhoFoto());

        // método de insert da classe SQLiteDatebase
        getWritableDatabase().insert(TABELA,null,values);
        //nullHack, garantia de preencher pelo menos um campo da linha(default)
    }


    public  List<Aluno> getLista(){
        List<Aluno> alunos = new ArrayList<Aluno>();
        SQLiteDatabase db = getReadableDatabase();


        String query = "SELECT * FROM " + TABELA;
        if (new PreferenciasDAO(ctx).isAlfabetica()){
            query += " ORDER BY nome ASC ";
        }

        Cursor c = getReadableDatabase().rawQuery(query + ";",null);




        while (c.moveToNext()){
            Aluno aluno = new Aluno();

            aluno.setId(c.getLong(c.getColumnIndex("id")));
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
            aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
            aluno.setSite(c.getString(c.getColumnIndex("site")));
            aluno.setNota(c.getDouble(c.getColumnIndex("nota")));
            //alterado na Ativ 7.5 Pag124
            aluno.setCaminhoFoto(c.getString(c.getColumnIndex("caminhoFoto")));

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
        //alterado na Ativ 7.5 Pag124
        values.put("caminhoFoto",aluno.getCaminhoFoto());

        String[] args = {aluno.getId().toString()};

        // método de insert da classe SQLiteDatebase
        getWritableDatabase().update(TABELA,values,"id=?",args);

    }

    public boolean isAluno(String telefone) {
        String [] paramentros = {telefone};

        //faz o select
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT telefone FROM " +
                TABELA + " WHERE telefone = ? ;", paramentros);

        int total = rawQuery.getCount();
        rawQuery.close();

        return total>0;
    }

}
