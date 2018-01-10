package io.github.joseseie.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import adapter.UsuarioAdapter;
import dao.UsuarioDAO;
import model.Usuario;

public class ListUsuariosActivity extends AppCompatActivity {

    private ListView lista;
    private List<Usuario> usuarioList;
    private UsuarioAdapter usuarioAdapter;
    private UsuarioDAO usuarioDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_usuarios);

        usuarioDAO = new UsuarioDAO(this);
        usuarioList = usuarioDAO.listarUsuarios();
        usuarioAdapter = new UsuarioAdapter(this, usuarioList);

        lista = (ListView) findViewById(R.id.lvUsuarios);
        lista.setAdapter(usuarioAdapter);

    }
}
