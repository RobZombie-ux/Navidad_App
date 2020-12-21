package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLite_OpenHelper extends SQLiteOpenHelper {
    public SQLite_OpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table usuarios (_ID integer primary key autoincrement, " +
                "Nombre text, Correo text, Password text);";
        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //METODO OPER_BD
    public void abrir(){
        this.getReadableDatabase();
    }

    //METODO CLOSE_BD
    public void cerrar(){
        this.close();
    }

    //METODO INSERT
    public void insertarRegistro(String nom, String cor, String pas){
        ContentValues valores = new ContentValues();
        valores.put("Nombre", nom);
        valores.put("Correo", cor);
        valores.put("Password", pas);

        //Insertar
        this.getWritableDatabase().insert("usuarios", null, valores);
    }

    //METODO VALIDAR USUARIO
    public Cursor ConsultarUsuario(String cor, String pas) throws SQLException{
        Cursor mcursor = null;
        mcursor = this.getReadableDatabase().query("usuarios",new String[]{"_ID",
                "Nombre","Correo","Password"},"Correo like '"+cor+"' " +
                "and Password like '"+pas+"'", null,null,null,null);

        return mcursor;
    }



}
