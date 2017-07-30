package pe.com.ricindigus.androiddba.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import pe.com.ricindigus.androiddba.SQLConstantes;

/**
 * Created by RICARDO on 29/07/2017.
 */

public class DBHelper extends SQLiteOpenHelper{
    public static final int DB_VERSION = 1;
    public DBHelper(Context context) {
        super(context, SQLConstantes.DB, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_RECETAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQLConstantes.SQL_DELETE);
        onCreate(sqLiteDatabase);
    }
}
