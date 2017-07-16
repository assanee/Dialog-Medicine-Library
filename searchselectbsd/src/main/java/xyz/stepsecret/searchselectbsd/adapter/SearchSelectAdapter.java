package xyz.stepsecret.searchselectbsd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import xyz.stepsecret.searchselectbsd.R;
import xyz.stepsecret.searchselectbsd.dialog.SearchSelectBSD;
import xyz.stepsecret.searchselectbsd.model.SearchSelectModel;


public class SearchSelectAdapter extends RecyclerView.Adapter<SearchSelectAdapter.MyViewHolder> implements Filterable {

    private List<SearchSelectModel> searchSelectModels;
    private List<SearchSelectModel> mArrayList;
    private Context mContext;
    private SearchSelectBSD searchSelectBSD;

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_medicine_name;
        private LinearLayout ln_medicine;
        private int position_medicine;

        MyViewHolder(View view) {
            super(view);

            tv_medicine_name = (TextView) view.findViewById(R.id.tv_medicine_name);
            ln_medicine = (LinearLayout) view.findViewById(R.id.ln_medicine);

            ln_medicine.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    searchSelectBSD.SetMedicine(searchSelectModels.get(position_medicine).getMedicineName());
                }
            });

        }
    }

    public SearchSelectAdapter(SearchSelectBSD searchSelectBSD, Context context, List<SearchSelectModel> searchSelectModels) {

        this.searchSelectBSD = searchSelectBSD;
        this.searchSelectModels = searchSelectModels;
        this.mArrayList = searchSelectModels;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.medicine_name_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
        SearchSelectModel searchSelectModel = searchSelectModels.get(position);

        holder.position_medicine = holder.getAdapterPosition();
        holder.tv_medicine_name.setText(searchSelectModel.getMedicineName());

    }

    @Override
    public int getItemCount() {
        return searchSelectModels.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    searchSelectModels = mArrayList;
                } else {

                    List<SearchSelectModel> filteredList = new ArrayList<>();

                    for (SearchSelectModel searchSelectModel : mArrayList) {

                        if (searchSelectModel.getMedicineName().toLowerCase().contains(charString) ) {

                            filteredList.add(searchSelectModel);
                        }
                    }

                    searchSelectModels = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = searchSelectModels;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                searchSelectModels = (ArrayList<SearchSelectModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
