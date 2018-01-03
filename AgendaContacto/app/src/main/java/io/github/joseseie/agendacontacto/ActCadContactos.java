 package io.github.joseseie.agendacontacto;

import android.app.DatePickerDialog;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import io.github.joseseie.agendacontacto.database.DataBase;
import io.github.joseseie.agendacontacto.dominio.RepositorioContato;
import io.github.joseseie.agendacontacto.dominio.entidades.Contato;

 public class ActCadContactos extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtEmail;
    private EditText edtTelefone;
    private EditText edtEndereco;
    private EditText edtDatasEspeciais;
    private EditText edtGrupos;

    private Spinner spnTipoEmail;
    private Spinner spnTipoTelefone;
    private Spinner spnTipoEndereco;
    private Spinner spnTipoDatasEspeciais;


    private ArrayAdapter<String> adpTipoEmail;
    private ArrayAdapter<String> adpTipoTelefone;
    private ArrayAdapter<String> adpTipoEndereco;
    private ArrayAdapter<String> adpTipoDatasEspeciais;

     private DataBase dataBase;
     private SQLiteDatabase conn;
     private RepositorioContato repositorioContato;
     private Contato contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cad_contactos);

        contato  = new Contato();

        edtNome     = (EditText) findViewById(R.id.edtNome);
        edtEmail     = (EditText) findViewById(R.id.edtEmail);
        edtTelefone     = (EditText) findViewById(R.id.edtTelefone);
        edtEndereco     = (EditText) findViewById(R.id.edtEndereco);
        edtDatasEspeciais     = (EditText) findViewById(R.id.edtDatasEspeciais);
        edtGrupos     = (EditText) findViewById(R.id.edtGrupos);

        spnTipoEmail = (Spinner) findViewById(R.id.spnTipoEmail);
        spnTipoTelefone = (Spinner) findViewById(R.id.spnTipoTelefone);
        spnTipoEndereco = (Spinner) findViewById(R.id.spnTipoEndereco);
        spnTipoDatasEspeciais = (Spinner) findViewById(R.id.spnDatasEspeciais);

        adpTipoEmail = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        adpTipoEmail.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adpTipoTelefone = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        adpTipoTelefone.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adpTipoEndereco = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        adpTipoEndereco.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adpTipoDatasEspeciais = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        adpTipoDatasEspeciais.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spnTipoEmail.setAdapter(adpTipoEmail);
        spnTipoTelefone.setAdapter(adpTipoTelefone);
        spnTipoDatasEspeciais.setAdapter(adpTipoDatasEspeciais);
        spnTipoEndereco.setAdapter(adpTipoEndereco);


        adpTipoEmail.add("Casa");
        adpTipoEmail.add("Trabalho");
        adpTipoEmail.add("Outros");

        adpTipoTelefone.add("Celular");
        adpTipoTelefone.add("Trabalho");
        adpTipoTelefone.add("Casa");
        adpTipoTelefone.add("Principal");
        adpTipoTelefone.add("Fax Trabalho");
        adpTipoTelefone.add("Pagar");
        adpTipoTelefone.add("Outros");

        adpTipoEndereco.add("casa");
        adpTipoEndereco.add("Trabalho");
        adpTipoEndereco.add("Outros");

        adpTipoDatasEspeciais.add("Aniversario");
        adpTipoDatasEspeciais.add("Data comemorativa");
        adpTipoDatasEspeciais.add("Outros");

        //Evento clique no campo de texto.
        edtDatasEspeciais.setOnClickListener( new ExibeDataListener() );
        edtDatasEspeciais.setOnFocusChangeListener( new ExibeDataListener() );


        Bundle bundle = getIntent().getExtras();

        if(bundle != null && (bundle.containsKey("CONTATO")))
        {
            contato = (Contato) bundle.getSerializable("CONTATO");
            this.preencheDados();
        }
        else contato = new Contato();

        try {

            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            repositorioContato = new RepositorioContato(conn);


        } catch (SQLException ex) {

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setNeutralButton("Ok",null);
            dlg.setMessage("Erro ao criar o banco: " + ex.getMessage());
            dlg.show();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_act_cad_contactos, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.mni_acao1:

                if(contato.getId() == 0)
                {
                    inserir();
                }
                break;

            case R.id.mni_acao2: break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void preencheDados()
    {
        edtNome.setText( contato.getNome() );
        edtTelefone.setText( contato.getTelefone() );
        spnTipoTelefone.setSelection( Integer.parseInt(contato.getTipoTelefone()) );
        edtEmail.setText( contato.getEmail() );
        spnTipoEmail.setSelection( Integer.parseInt(contato.getTipoEmail()) );
        edtEndereco.setText( contato.getEndereco() );
        spnTipoEndereco.setSelection( Integer.parseInt(contato.getTipoEndereco()) );

        DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
        String df = format.format( contato.getDatasEspeciais() );

        edtDatasEspeciais.setText( df );
        spnTipoDatasEspeciais.setSelection( Integer.parseInt( contato.getTipoDatasEspeciais() ));

        edtGrupos.setText( contato.getGrupos() );

    }


    private void inserir()
    {

        try {


            contato.setNome(edtNome.getText().toString());
            contato.setTelefone(edtTelefone.getText().toString());
            contato.setEmail(edtEmail.getText().toString());
            contato.setEndereco(edtEndereco.getText().toString());

            contato.setGrupos( edtGrupos.getText().toString() );

            contato.setTipoTelefone( String.valueOf( spnTipoTelefone.getSelectedItemPosition()) );
            contato.setTipoEmail( String.valueOf( spnTipoEmail.getSelectedItemPosition()) );
            contato.setTipoEndereco( String.valueOf( spnTipoEndereco.getSelectedItemPosition()) );
            contato.setTipoDatasEspeciais( String.valueOf( spnTipoDatasEspeciais.getSelectedItemPosition()) );

            repositorioContato.inserir(contato);

        } catch (Exception ex) {

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setNeutralButton("Ok",null);
            dlg.setMessage("Erro ao inserir os dados: " + ex.getMessage());
            dlg.show();

        }

    }


    private void exibeData()
    {
        Calendar calendar = Calendar.getInstance();

        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dlg = new DatePickerDialog(this,new SelecionaDataListener(),ano,mes,dia);
        dlg.show();

    }

    private class ExibeDataListener implements View.OnClickListener, View.OnFocusChangeListener {

        @Override
        public void onClick(View view) {
            exibeData();
        }

        @Override
        public void onFocusChange(View view, boolean hasFocus) {
            if(hasFocus) exibeData();
        }
    }

     private class SelecionaDataListener implements DatePickerDialog.OnDateSetListener {

         @Override
         public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

             Calendar calendar = Calendar.getInstance();

             calendar.set(year,monthOfYear,dayOfMonth);

             Date date = calendar.getTime();

             DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
             String df = format.format(date);

             edtDatasEspeciais.setText(df);

             contato.setDatasEspeciais(date);

         }
     }



}
