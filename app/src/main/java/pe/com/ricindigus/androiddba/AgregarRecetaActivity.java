package pe.com.ricindigus.androiddba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pe.com.ricindigus.androiddba.pojos.Receta;

public class AgregarRecetaActivity extends AppCompatActivity {
    private EditText edtId;
    private EditText edtNombre;
    private EditText edtPersonas;
    private EditText edtDescripcion;
    private EditText edtPreparacion;
    private EditText edtFav;
    private Button btnAgregar;
    private Receta receta;
    private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_receta);

        edtId = (EditText) findViewById(R.id.edtId);
        edtNombre = (EditText) findViewById(R.id.edtNombre);
        edtPersonas = (EditText) findViewById(R.id.edtPersonas);
        edtDescripcion = (EditText) findViewById(R.id.edtDescripcion);
        edtPreparacion = (EditText) findViewById(R.id.edtPreparacion);
        edtFav = (EditText) findViewById(R.id.edtFav);
        btnAgregar = (Button) findViewById(R.id.btnAgregar);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                receta = new Receta(edtId.getText().toString(),
                        edtNombre.getText().toString(),
                        Integer.parseInt(edtPersonas.getText().toString()),
                        edtDescripcion.getText().toString(),
                        edtPreparacion.getText().toString(),
                        "imagen.png",
                        Integer.parseInt(edtFav.getText().toString()));
                data = new Data(getApplicationContext());
                data.open();
                data.insertarReceta(receta);
                finish();
            }
        });
    }
}
