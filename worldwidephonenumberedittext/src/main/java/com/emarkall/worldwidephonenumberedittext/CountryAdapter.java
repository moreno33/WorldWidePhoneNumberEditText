package com.emarkall.worldwidephonenumberedittext;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.emarkall.worldwidephonenumberedittext.CountryList.COUNTRIES;
import static com.emarkall.worldwidephonenumberedittext.CountryListActivity.COUNTRY_EXTRA;

/**
 * This is the adapter
 * for the country list
 * @author Moreno Jn Baptiste
 */
public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CustomViewHolder> {


    private Context mContext;

    private List<Country> countries= COUNTRIES;


    public CountryAdapter (Context context){
        mContext= context;
    }
    @NonNull
    @Override
    public CountryAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(mContext).inflate(R.layout.country_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.CustomViewHolder holder, int position) {
        final Country country= countries.get(holder.getAdapterPosition());

        holder.imgFlag.setImageResource(country.getResId(mContext));
        holder.txtCountryName.setText(country.getDisplayName());

        holder.rltParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                When a person click on an elemento fo the list, en intent will be returned.
                 */
                Intent intent= new Intent();
                intent.putExtra(COUNTRY_EXTRA, country);
                ((Activity)mContext).setResult(RESULT_OK, intent);
                ((Activity)mContext).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    //This is a custom viewHolder for every single row
    public class CustomViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFlag;
        private TextView txtCountryName;
        private RelativeLayout rltParent;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            rltParent= itemView.findViewById(R.id.rlt_parent);
            imgFlag= itemView.findViewById(R.id.img_flag);
            txtCountryName= itemView.findViewById(R.id.txt_country_name);
        }
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
