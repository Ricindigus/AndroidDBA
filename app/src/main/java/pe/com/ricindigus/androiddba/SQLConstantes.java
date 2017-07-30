package pe.com.ricindigus.androiddba;

/**
 * Created by RICARDO on 29/07/2017.
 */

public class SQLConstantes {

    //DB
    public static final String DB = "bdrecetas.db";

    //TABLAS
    public static final String tableRecetas = "recetas";

    //COLUMNAS
    public static final String COLUMNA_ID = "id";
    public static final String COLUMNA_NOMBRE = "nombre";
    public static final String COLUMNA_PERSONAS = "personas";
    public static final String COLUMNA_DESCRIPCION = "descripcion";
    public static final String COLUMNA_PREPARACION = "preparacion";
    public static final String COLUMNA_IMAGEN = "imagen";
    public static final String COLUMNA_FAV = "fav";

    //QUERY
    public static final String SQL_CREATE_TABLA_RECETAS =
            "CREATE TABLE " + tableRecetas + "(" +
                    COLUMNA_ID + " TEXT PRIMARY KEY," +
                    COLUMNA_NOMBRE + " TEXT," +
                    COLUMNA_PERSONAS + " TEXT," +
                    COLUMNA_DESCRIPCION + " TEXT," +
                    COLUMNA_PREPARACION + " TEXT," +
                    COLUMNA_IMAGEN + " TEXT," +
                    COLUMNA_FAV + " INT" + ");"
            ;

    public static final String WHERE_CLAUSE_NOMBRE = "nombre=?";
    public static final String WHERE_CLAUSE_FAV = "fav=?";
    public static final String WHERE_CLAUSE_PERSONAS = "personas=?";



    public static final String SQL_DELETE =
            "DROP TABLE " + tableRecetas;

    public static final String[] ALL_COLUMNS = {
            COLUMNA_ID,COLUMNA_NOMBRE,COLUMNA_PERSONAS,
            COLUMNA_DESCRIPCION,COLUMNA_PREPARACION,COLUMNA_IMAGEN,
            COLUMNA_FAV
    };
}
