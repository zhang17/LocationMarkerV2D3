package com.first_teck.locationmarkerv2drawer;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zyqzh_000 on 2015/11/18.
 */
public class MenuItemAdapter extends ArrayAdapter<MenuItem>{

    private int resourceId;//

    public MenuItemAdapter(Context context, int textViewResourceId, List<MenuItem> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();

        MenuItem menuItem = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        //TextView item;
        if(menuItem instanceof Item1){
            //item = (TextView) view.findViewById(R.id.menu_item);
            //item.setVisibility(View.VISIBLE);
            //item.setEnabled(false);
            //item.setOnClickListener(null);
            //item.setFocusable(false);
            //item.setClickable(false);
            //item.setText(((Item1) menuItem).getName());

            viewHolder.myText = (TextView)view.findViewById(R.id.menu_item);
            viewHolder.myImage1 = (ImageView)view.findViewById(R.id.image_category);
            viewHolder.myImage2 = (ImageView)view.findViewById(R.id.blank_space);

            viewHolder.myText.setVisibility(View.VISIBLE);
            viewHolder.myText.setEnabled(false);
            viewHolder.myText.setOnClickListener(null);
            viewHolder.myText.setFocusable(false);
            viewHolder.myText.setClickable(false);
            viewHolder.myText.setText(((Item1) menuItem).getName());

            viewHolder.myImage1.getLayoutParams().width = 0;//the unit is pixel
            viewHolder.myImage2.getLayoutParams().width = 0;



           viewHolder.myDivider = (View)view.findViewById(R.id.divider1);
            viewHolder.myDivider.setVisibility(View.VISIBLE);

        }
        else if (menuItem instanceof Item2){
            //item = (TextView) view.findViewById(R.id.menu_item);
            //item.setVisibility(View.VISIBLE);
            //item.setText(((Item2) menuItem).getName());

            viewHolder.myText = (TextView)view.findViewById(R.id.menu_item);
            viewHolder.myImage1 = (ImageView)view.findViewById(R.id.image_category);

            viewHolder.myText.setVisibility(View.VISIBLE);
            viewHolder.myText.setText(((Item2) menuItem).getName());
            viewHolder.myText.setTextSize(15);
            viewHolder.myText.setTextColor(Color.BLACK);
            viewHolder.myImage1.setVisibility(View.VISIBLE);

            switch (((Item2) menuItem).getName()){

                case "SavedMarkersList":   viewHolder.myImage1.setImageResource(R.drawable.list);
                                                break;
                case "MapView":       viewHolder.myImage1.setImageResource(R.drawable.map);
                                        break;
                case "Entertaining" :viewHolder.myImage1.setImageResource(R.drawable.entertaining);
                                        break;
                case "Eating" :viewHolder.myImage1.setImageResource(R.drawable.eating);
                                     break;
                case "Living" :viewHolder.myImage1.setImageResource(R.drawable.living);
                                    break;
                case "Shopping" :viewHolder.myImage1.setImageResource(R.drawable.shopping);
                                    break;

            }

            viewHolder.myDivider = view.findViewById(R.id.divider2);//viewHolder.myDivider = (View)view.findViewById(R.id.divider2);
            viewHolder.myDivider.setVisibility(View.VISIBLE);
        }
        else if (menuItem instanceof ItemLocation){
            viewHolder.myText = (TextView)view.findViewById(R.id.text_location_name);
            viewHolder.myText.setText(((ItemLocation)menuItem).getLocationName());
            viewHolder.myText1 = (TextView)view.findViewById(R.id.text_last_visited_date);
            viewHolder.myText1.setText("Last Visited Time:" + "\n" + (((ItemLocation) menuItem).getLastVisitedDate()).toString());//这一步将millisecond的时间转化成date
            viewHolder.myText2 = (TextView)view.findViewById(R.id.text_saved_date);
            viewHolder.myText2.setText("Saved Time:"+"\n"+ ((ItemLocation) menuItem).getSavedDate().toString());//这一步将millisecond的时间转化成date
            viewHolder.myText3 = (TextView)view.findViewById(R.id.text_categories);
            viewHolder.myText3.setText("Categories:"+ ((ItemLocation) menuItem).getCategories());
            switch (((ItemLocation) menuItem).getCategories()){
                case "Entertaining": viewHolder.myText.setBackgroundColor(Color.parseColor("#e5e5ff"));//blue
                                    viewHolder.myText1.setBackgroundColor(Color.parseColor("#e5e5ff"));
                                    viewHolder.myText2.setBackgroundColor(Color.parseColor("#e5e5ff"));
                                    viewHolder.myText3.setBackgroundColor(Color.parseColor("#e5e5ff"));
                                    break;
                case "Eating": viewHolder.myText.setBackgroundColor(Color.parseColor("#eeffe5"));//green
                                    viewHolder.myText1.setBackgroundColor(Color.parseColor("#eeffe5"));
                                    viewHolder.myText2.setBackgroundColor(Color.parseColor("#eeffe5"));
                                    viewHolder.myText3.setBackgroundColor(Color.parseColor("#eeffe5"));
                                    break;
                case "Living": viewHolder.myText.setBackgroundColor(Color.parseColor("#e5ffff"));//light blue
                                    viewHolder.myText1.setBackgroundColor(Color.parseColor("#e5ffff"));
                                    viewHolder.myText2.setBackgroundColor(Color.parseColor("#e5ffff"));
                                    viewHolder.myText3.setBackgroundColor(Color.parseColor("#e5ffff"));
                                    break;
                case "Shopping": viewHolder.myText.setBackgroundColor(Color.parseColor("#f7e5ff"));//purple
                                    viewHolder.myText1.setBackgroundColor(Color.parseColor("#f7e5ff"));
                                    viewHolder.myText2.setBackgroundColor(Color.parseColor("#f7e5ff"));
                                    viewHolder.myText3.setBackgroundColor(Color.parseColor("#f7e5ff"));
                                    break;
            }
        }
        return view;

    }

    private static class ViewHolder{
        public TextView myText;
        public TextView myText1;
        public TextView myText2;
        public TextView myText3;
        public ImageView myImage1;
        public ImageView myImage2;
        public View myDivider;
    }
}