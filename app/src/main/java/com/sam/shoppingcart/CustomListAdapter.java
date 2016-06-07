package com.sam.shoppingcart;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by SAM on 6/6/2016.
 */
public class CustomListAdapter extends ArrayAdapter<Model> {

    private Context mContext;
    int resource;
    private ArrayList<Model> mListData = new ArrayList<Model>();



    public CustomListAdapter(Context mContext, int resource, ArrayList<Model> mListData) {
        super(mContext, resource, mListData);
        this.resource = resource;
        this.mContext = mContext;
        this.mListData = mListData;
    }

    public void setListData(ArrayList<Model> mListData) {
        this.mListData = mListData;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View v = convertView;
        final ViewHolder holder;
        if (v == null) {
            holder = new ViewHolder();

            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();

                v = inflater.inflate(resource, parent, false);

                holder.name = (TextView) v.findViewById(R.id.name);
                holder.rate = (TextView) v.findViewById(R.id.rate);
                holder.add = (Button) v.findViewById(R.id.add);
                holder.remove = (Button) v.findViewById(R.id.remove);


            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }




        final Model item = mListData.get(position);

            holder.name.setText(item.getName());
            holder.rate.setText(item.getRate());
            holder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   SQLiteHandler db = new SQLiteHandler(getContext());

                    if(db.ifExists(item)){


                        Toast.makeText(getContext(), "Already Present", Toast.LENGTH_SHORT).show();

                    }else{
                        db.addItems(holder.name.getText().toString(), holder.rate.getText().toString());
                        Toast.makeText(getContext(), "Added", Toast.LENGTH_SHORT).show();
                    }


                }
            });

            holder.remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SQLiteHandler db = new SQLiteHandler(getContext());
                    db.deleteItem(item);
                    Toast.makeText(getContext(), "Removed", Toast.LENGTH_SHORT).show();

                }
            });

        return v;
    }

    class ViewHolder {

        TextView name;
        TextView rate;
        Button add;
        Button remove;
    }

}

