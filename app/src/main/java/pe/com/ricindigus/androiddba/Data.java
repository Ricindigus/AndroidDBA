package pe.com.ricindigus.androiddba;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import pe.com.ricindigus.androiddba.helpers.DBHelper;
import pe.com.ricindigus.androiddba.pojos.Receta;

/**
 * Created by RICARDO on 29/07/2017.
 */

public class Data {
    Context contexto;
    SQLiteOpenHelper sqLiteOpenHelper;
    SQLiteDatabase sqLiteDatabase;

    public Data(Context contexto) {
        this.contexto = contexto;
        sqLiteOpenHelper = new DBHelper(contexto);
        sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
    }

    public void open(){
        sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
    }

    public void close(){
        sqLiteOpenHelper.close();
    }

    public long getNumeroItems(){
        return DatabaseUtils.queryNumEntries(sqLiteDatabase,SQLConstantes.tableRecetas);
    }

    public void insertarReceta(Receta receta){
        ContentValues contentValues = receta.toValues();
        sqLiteDatabase.insert(SQLConstantes.tableRecetas,null,contentValues);
    }

    public void InsertarRecetas(ArrayList<Receta> recetas){
        long items = getNumeroItems();
        if(items == 0){
            for (Receta receta: recetas) {
                try {
                    insertarReceta(receta);
                }catch (SQLiteException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public ArrayList<Receta> getAll(){
        ArrayList<Receta> recetas = new ArrayList<Receta>();
        Cursor cursor = sqLiteDatabase.query(SQLConstantes.tableRecetas,
                SQLConstantes.ALL_COLUMNS,null,null,null,null,null);
        while(cursor.moveToNext()){
            Receta receta = new Receta();
            receta.setId(cursor.getString(cursor.getColumnIndex(SQLConstantes.COLUMNA_ID)));
            receta.setNombre(cursor.getString(cursor.getColumnIndex(SQLConstantes.COLUMNA_NOMBRE)));
            receta.setPersonas(cursor.getInt(cursor.getColumnIndex(SQLConstantes.COLUMNA_PERSONAS)));
            receta.setDescripcion(cursor.getString(cursor.getColumnIndex(SQLConstantes.COLUMNA_DESCRIPCION)));
            receta.setPreparacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.COLUMNA_PREPARACION)));
            receta.setImage(cursor.getString(cursor.getColumnIndex(SQLConstantes.COLUMNA_IMAGEN)));
            receta.setFav(cursor.getInt(cursor.getColumnIndex(SQLConstantes.COLUMNA_FAV)));
            recetas.add(receta);
        }
        return recetas;
    }

    public ArrayList<Receta> getFavs(){
        ArrayList<Receta> recetas = new ArrayList<Receta>();
        String[] whereArgs = new String[]{String.valueOf(1)};
        Cursor cursor = sqLiteDatabase.query(SQLConstantes.tableRecetas,
                SQLConstantes.ALL_COLUMNS,SQLConstantes.WHERE_CLAUSE_FAV,whereArgs,null,null,null);
        while(cursor.moveToNext()){
            Receta receta = new Receta();
            receta.setId(cursor.getString(cursor.getColumnIndex(SQLConstantes.COLUMNA_ID)));
            receta.setNombre(cursor.getString(cursor.getColumnIndex(SQLConstantes.COLUMNA_NOMBRE)));
            receta.setPersonas(cursor.getInt(cursor.getColumnIndex(SQLConstantes.COLUMNA_PERSONAS)));
            receta.setDescripcion(cursor.getString(cursor.getColumnIndex(SQLConstantes.COLUMNA_DESCRIPCION)));
            receta.setPreparacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.COLUMNA_PREPARACION)));
            receta.setImage(cursor.getString(cursor.getColumnIndex(SQLConstantes.COLUMNA_IMAGEN)));
            receta.setFav(cursor.getInt(cursor.getColumnIndex(SQLConstantes.COLUMNA_FAV)));
            recetas.add(receta);
        }
        return recetas;
    }

    public ArrayList<Receta> getPersonas(int p){
        ArrayList<Receta> recetas = new ArrayList<Receta>();
        String[] whereArgs = new String[]{String.valueOf(p)};
        Cursor cursor = sqLiteDatabase.query(SQLConstantes.tableRecetas,
                SQLConstantes.ALL_COLUMNS,SQLConstantes.WHERE_CLAUSE_PERSONAS,whereArgs,null,null,null);
        while(cursor.moveToNext()){
            Receta receta = new Receta();
            receta.setId(cursor.getString(cursor.getColumnIndex(SQLConstantes.COLUMNA_ID)));
            receta.setNombre(cursor.getString(cursor.getColumnIndex(SQLConstantes.COLUMNA_NOMBRE)));
            receta.setPersonas(cursor.getInt(cursor.getColumnIndex(SQLConstantes.COLUMNA_PERSONAS)));
            receta.setDescripcion(cursor.getString(cursor.getColumnIndex(SQLConstantes.COLUMNA_DESCRIPCION)));
            receta.setPreparacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.COLUMNA_PREPARACION)));
            receta.setImage(cursor.getString(cursor.getColumnIndex(SQLConstantes.COLUMNA_IMAGEN)));
            receta.setFav(cursor.getInt(cursor.getColumnIndex(SQLConstantes.COLUMNA_FAV)));
            recetas.add(receta);
        }
        return recetas;
    }

    public void deleteItem(String nombre){
        String[] whereArgs = new String[]{String.valueOf(nombre)};
        sqLiteDatabase.delete(SQLConstantes.tableRecetas,SQLConstantes.WHERE_CLAUSE_NOMBRE,whereArgs);
    }


}
