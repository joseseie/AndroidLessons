package io.github.joseseie.todolist;

import android.content.DialogInterface;
import android.content.Intent;
import android.app.AlertDialog;;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import adapter.UsuarioAdapter;
import dao.UsuarioDAO;
import model.Usuario;
import util.Mensagem;

public class ListUsuariosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, DialogInterface.OnClickListener{

    private ListView lista;
    private List<Usuario> usuarioList;
    private UsuarioAdapter usuarioAdapter;
    private UsuarioDAO usuarioDAO;

    private AlertDialog alertDialog, alertConfirmacao;
    private int idPosicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_usuarios);

        alertDialog         = Mensagem.criarAlertDialog(this);
        alertConfirmacao    = Mensagem.criarDialogConfirmacao(this);

        usuarioDAO = new UsuarioDAO(this);
        usuarioList = usuarioDAO.listarUsuarios();
        usuarioAdapter = new UsuarioAdapter(this, usuarioList);

        lista = (ListView) findViewById(R.id.lvUsuarios);
        lista.setAdapter(usuarioAdapter);

        lista.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_usuarios, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if ( id == R.id.action_cadastrar_usuarios ) {
            startActivity( new Intent(this, CadUsuarioActivity.class) );
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(DialogInterface dialogInterface, int swich) {
        int id = usuarioList.get( idPosicao ).get_id();

        switch (swich) {
            case 0:
                Intent intent = new Intent(this, CadUsuarioActivity.class);
                intent.putExtra("USUARIO_ID", id);
                startActivity( intent );
            case 1:
                alertConfirmacao.show();
                break;

            case DialogInterface.BUTTON_POSITIVE:
                usuarioList.remove( idPosicao );
                usuarioDAO.removerUsuario( id );
                lista.invalidateViews(); //actualiza a lista
                break;
            case  DialogInterface.BUTTON_NEGATIVE:
                alertConfirmacao.dismiss();
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        idPosicao = position;
        alertDialog.show();
    }
}
