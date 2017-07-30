package pe.com.ricindigus.androiddba.apadters;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pe.com.ricindigus.androiddba.R;
import pe.com.ricindigus.androiddba.pojos.Receta;


/**
 * Created by RICARDO on 29/07/2017.
 */

public class RecetaAdapter extends RecyclerView.Adapter<RecetaAdapter.ViewHolder>{
    Context contexto;
    ArrayList<Receta> recetas;

    public RecetaAdapter(Context contexto, ArrayList<Receta> recetas) {
        this.contexto = contexto;
        this.recetas = recetas;
    }

    public ArrayList<Receta> getRecetas() {
        return recetas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receta_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nombre.setText(recetas.get(position).getNombre());
        holder.personas.setText(String.valueOf(recetas.get(position).getPersonas()));
        holder.descripcion.setText(recetas.get(position).getDescripcion());
        holder.preparacion.setText(recetas.get(position).getPreparacion());
        if(recetas.get(position).getFav() == 1){
            holder.imgStar.setImageResource(R.drawable.star_favorito);
        }else{
            holder.imgStar.setImageResource(R.drawable.star_normal);
        }
    }

    @Override
    public int getItemCount() {
        return recetas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardview;
        TextView nombre;
        TextView personas;
        TextView descripcion;
        TextView preparacion;
        ImageView imagen, imgStar;
        public ViewHolder(View itemView) {
            super(itemView);
            cardview = (CardView) itemView.findViewById(R.id.cardview);
            nombre = (TextView) itemView.findViewById(R.id.txtNombre);
            personas = (TextView) itemView.findViewById(R.id.txtPersonas);
            descripcion = (TextView) itemView.findViewById(R.id.txtDescripcion);
            preparacion = (TextView) itemView.findViewById(R.id.txtPreparacion);
            imagen = (ImageView) itemView.findViewById(R.id.imagen);
            imgStar = (ImageView) itemView.findViewById(R.id.imgStar);
        }
    }
}
