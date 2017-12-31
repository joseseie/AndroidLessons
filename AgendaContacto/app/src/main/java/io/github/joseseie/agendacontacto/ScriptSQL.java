package io.github.joseseie.agendacontacto;

/**
 * Created by Jose Seie on 12/31/2017.
 */

public class ScriptSQL {

    public static String getCreateContato()
    {

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("CREATE TABLE IF NOT EXISTS CONTATO (");
        sqlBuilder.append("ID               INTEGER NOT  NULL ");
        sqlBuilder.append("                 PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("TELEFONE         VARCHAR(14), ");
        sqlBuilder.append("TIPOTELEFONE     VARCHAR(1), ");
        sqlBuilder.append("EMAIL            VARCHAR(255), ");
        sqlBuilder.append("TIPOEMAIL        VARCHAR(1), ");
        sqlBuilder.append("ENDERECO         VARCHAR(255), ");
        sqlBuilder.append("TIPOENDERECO     VARCHAR(1), ");
        sqlBuilder.append("DATASESPECIAIS   DATE, ");
        sqlBuilder.append("TIPODATAESPECIAIS VARCHAR(1), ");
        sqlBuilder.append("GRUPOS           VARCHAR(255), ");
        sqlBuilder.append(" ); ");

        return sqlBuilder.toString();
    }



}
