package io.github.joseseie.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import dao.UsuarioDAO;
import model.Usuario;
import util.Mensagem;

public class CadUsuarioActivity extends AppCompatActivity {

    private EditText edtNome, edtLogin, edtSenha;
    private UsuarioDAO usuarioDAO;
    private Usuario usuario;
    private int idusuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_usuario);

        usuarioDAO = new UsuarioDAO(this);

        edtNome         = (EditText) findViewById(R.id.usuario_edtNome);
        edtLogin        = (EditText) findViewById(R.id.usuario_edtLogin);
        edtSenha        = (EditText) findViewById(R.id.usuario_edtSenha);

    }

    @Override
    protected void onDestroy() {
        usuarioDAO.fechar();
        super.onDestroy();
    }

    private void cadastrar()
    {
        boolean validacao = true;

        String nome = edtNome.getText().toString();
        String login = edtNome.getText().toString();
        String senha = edtSenha.getText().toString();

        if (nome == null || nome.equals("") ) {
            validacao = false;
            edtNome.setError(getString(R.string.campo_obrigatorio));
        }

        if (login == null || login.equals("") ) {
            validacao = false;
            edtLogin.setError(getString(R.string.campo_obrigatorio));
        }

        if (senha == null || senha.equals("") ) {
            validacao = false;
            edtSenha.setError(getString(R.string.campo_obrigatorio));
        }

        if ( validacao )
        {
            usuario = new Usuario();
            usuario.setNome( nome );
            usuario.setLogin( login );
            usuario.setSenha( senha );

            if ( idusuario > 0 )
            {
                usuario.set_id( idusuario );
            }

            long resultado = usuarioDAO.salvarUsuario(usuario);

            if ( resultado != -1 )
            {
                if ( idusuario > 0 ) {
                    Mensagem.Msg(this, getString(R.string.mensagem_actializar));
                } else {
                    Mensagem.Msg(this, getString(R.string.mensagem_cadastro));
                }

                finish();
                startActivity( new Intent(this, MainActivity.class));
            }
            else
            {
                Mensagem.Msg(this, getString(R.string.mensagem_erro));
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cadastros, menu);

        if ( idusuario > 0 ) {
            menu.findItem(R.id.action_menu_excluir).setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch ( id ) {
            case R.id.action_menu_salvar:
                this.cadastrar();
                break;

            case R.id.action_menu_sair:
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
