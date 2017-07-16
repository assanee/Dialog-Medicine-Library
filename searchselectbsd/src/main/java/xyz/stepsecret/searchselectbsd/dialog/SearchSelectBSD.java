package xyz.stepsecret.searchselectbsd.dialog;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import xyz.stepsecret.searchselectbsd.R;
import xyz.stepsecret.searchselectbsd.adapter.SearchSelectAdapter;
import xyz.stepsecret.searchselectbsd.model.SearchSelectModel;
import xyz.stepsecret.searchselectbsd.rcvdecoration.GridSpacingItemDecoration;

public class SearchSelectBSD extends BottomSheetDialogFragment {

    private SearchSelectAdapter adapter;
    private List<SearchSelectModel> searchSelectModels;
    private EditText edt_medicine;
    private static SearchCallback listener = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.medicine_select_bsd_fragment, container, false);

        RecyclerView recyclerView = v.findViewById(R.id.rcv_medicine);
        edt_medicine =  v.findViewById(R.id.edt_medicine);

        edt_medicine.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (adapter != null) adapter.getFilter().filter(s);
            }
        });

        searchSelectModels = new ArrayList<>();
        adapter = new SearchSelectAdapter(this, getContext(), searchSelectModels);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(1), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        listener.onCreateComplete(this);

        return v;
    }

    public void SetItem(String[] item)
    {
        searchSelectModels.clear();
        adapter.notifyDataSetChanged();

        SearchSelectModel medicineListItem;

        for (String aMedicine_name : item) {
            medicineListItem = new SearchSelectModel();
            medicineListItem.setMedicineName(aMedicine_name);
            searchSelectModels.add(medicineListItem);

        }

        adapter.notifyDataSetChanged();
    }
    public void SetHint(String text)
    {
        edt_medicine.setHint(text);
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public void setClientCallback(SearchCallback listener){
        SearchSelectBSD.listener = listener;
    }

    public interface SearchCallback {
        void onSelect(String message);
        void onCreateComplete(SearchSelectBSD searchSelectBSD);
    }

    public void SetMedicine(String name)
    {
        listener.onSelect(name);
        this.dismiss();
    }
}
