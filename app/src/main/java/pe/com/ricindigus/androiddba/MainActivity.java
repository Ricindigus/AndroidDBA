package pe.com.ricindigus.androiddba;

import android.animation.RectEvaluator;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import pe.com.ricindigus.androiddba.apadters.RecetaAdapter;
import pe.com.ricindigus.androiddba.pojos.Receta;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerRecetas;
    private ArrayList<Receta> recetas;
    RecetaAdapter recetaAdapter;
    Data data;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerRecetas = (RecyclerView) findViewById(R.id.recyclerRecetas);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        createData();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerRecetas.setLayoutManager(linearLayoutManager);

//        recetaAdapter = new RecetaAdapter(this, recetas);
//        recyclerRecetas.setAdapter(recetaAdapter);
        update();

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int posicion = viewHolder.getAdapterPosition();
                RecetaAdapter recetaAdapter = (RecetaAdapter) recyclerRecetas.getAdapter();
                String value = recetaAdapter.getRecetas().get(posicion).getNombre();
                data.deleteItem(value);
                update();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerRecetas);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AgregarRecetaActivity.class);
                startActivity(intent);
            }
        });
    }

    public void createData(){
        recetas = new ArrayList<Receta>();
        recetas.add(new Receta("1","Sandwich",2,"Pan con jamon,queso,tomate,pepino"
                ,"Asi es como se cocina un sandwich","imagen.png",0));
        recetas.add(new Receta("2","Huevos",1,"Huevos con jamon,queso,tomate,pepino"
                ,"Asi es como se cocina un huevo","imagen.png",0));
        recetas.add(new Receta("3","Hotkays",5,"pan con miel,queso,toamte,pepino"
                ,"Asi es como se cocina un sandwich","imagen.png",0));
        recetas.add(new Receta("4","Pescado",2,"Pan con jamon,queso,tomate,pepino"
                ,"Asi es como se cocina un sandwich","imagen.png",0));
        recetas.add(new Receta("5","Donas",1,"Huevos con jamon,queso,tomate,pepino"
                ,"Asi es como se cocina un huevo","imagen.png",0));
        recetas.add(new Receta("6","Papas",5,"pan con miel,queso,toamte,pepino"
                ,"Asi es como se cocina un sandwich","imagen.png",0));
        recetas.add(new Receta("7","Burrito",1,"Huevos con jamon,queso,tomate,pepino"
                ,"Asi es como se cocina un huevo","imagen.png",0));
        recetas.add(new Receta("8","Tacos",5,"pan con miel,queso,toamte,pepino"
                ,"Asi es como se cocina un sandwich","imagen.png",0));

        data = new Data(this);
        data.open();
        data.InsertarRecetas(recetas);
    }

    public ArrayList<Receta> getData(){
        ArrayList<Receta> recetas = new ArrayList<Receta>();
        return data.getAll();
    }

    public void update(){
        recetas = getData();
        recetaAdapter = new RecetaAdapter(this, recetas);
        recyclerRecetas.setAdapter(recetaAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        update();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.favs:
                recetaAdapter = new RecetaAdapter(this, data.getFavs());
                recyclerRecetas.setAdapter(recetaAdapter);
                return true;
            case R.id.personas:
                recetaAdapter = new RecetaAdapter(this, data.getPersonas(2));
                recyclerRecetas.setAdapter(recetaAdapter);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
