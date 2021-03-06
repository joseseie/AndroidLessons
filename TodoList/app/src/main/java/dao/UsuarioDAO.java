package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import model.Usuario;

/**
 * Created by Jose Seie on 1/10/2018.
 */

public class UsuarioDAO {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public UsuarioDAO(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    private SQLiteDatabase getDatabase()
    {
        if( database ==  null ) {
            database = databaseHelper.getWritableDatabase();
        }
        return database;
    }

    private Usuario criarUsuario(Cursor cursor)
    {
        Usuario model = new Usuario(
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Usuarios._ID)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Usuarios.NOME)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Usuarios.LOGIN)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Usuarios.SENHA))
        );
        return model;
    }

    public List<Usuario> listarUsuarios()
    {
        Cursor cursor = getDatabase().query(DatabaseHelper.Usuarios.TABELA,
                        DatabaseHelper.Usuarios.COLUNAS, null,null,null,null,null);

        List<Usuario> usuarios = new ArrayList<>();

        while (cursor.moveToNext()) {
            Usuario model = criarUsuario(cursor);
            usuarios.add(model);
        }
        cursor.close();
        return usuarios;
    }

    public long salvarUsuario(Usuario usuario)
    {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.Usuarios.NOME, usuario.getNome());
        values.put(DatabaseHelper.Usuarios.LOGIN, usuario.getLogin());
        values.put(DatabaseHelper.Usuarios.SENHA, usuario.getSenha());
        values.put(DatabaseHelper.Usuarios.CREATED_AT, usuario.getCreated_at());

        if ( usuario.get_id() != null )
            return getDatabase().update(DatabaseHelper.Usuarios.TABELA, values,
                    "_id = ?", new String[]{ usuario.get_id().toString() });

        return getDatabase().insert(DatabaseHelper.Usuarios.TABELA,
                null, values);
    }

    public boolean removerUsuario(int id) {
        return getDatabase().delete(DatabaseHelper.Usuarios.TABELA,
                "_id = ?", new String[]{ Integer.toString( id )}) > 0;
    }

    public Usuario buscarUsuarioPorId(int id)
    {
        Cursor cursor = getDatabase().query(DatabaseHelper.Usuarios.TABELA,
                DatabaseHelper.Usuarios.COLUNAS, "_id = ?", new String[]{ Integer.toString( id )}, null, null, null);

        if ( cursor.moveToNext()) {
            Usuario model = criarUsuario(cursor);
            cursor.close();
            return model;
        }

        return null;
    }

    public boolean logar(String usuario, String senha)
    {
        Cursor cursor = getDatabase().query(DatabaseHelper.Usuarios.TABELA,
                null,"LOGIN = ? AND SENHA = ?", new String[]{
                    usuario, senha
                },null,null,null);

        if ( cursor.moveToFirst()) return  true;

        return false;
    }

    public void fechar()
    {
        databaseHelper.close();
        database = null;
    }


}
