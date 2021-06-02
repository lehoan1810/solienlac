package com.hoanle.solienlaccc.fragments.qlmon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoanle.solienlaccc.R;
import com.hoanle.solienlaccc.fragments.xemdiem.XemdiemMon;

import java.util.List;

public class XemMonAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<XemMon> xemMonList;

    public XemMonAdapter(Context context, int layout, List<XemMon> xemMonList) {
        this.context = context;
        this.layout = layout;
        this.xemMonList = xemMonList;
    }

    @Override
    public int getCount() {
        return xemMonList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class ViewHorder{
        ImageView img;
        TextView txtmonhoc;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHorder viewHorder;
        if(convertView==null)
        {
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(layout,null);
            viewHorder=new ViewHorder();
            viewHorder.txtmonhoc= convertView.findViewById(R.id.txtTenMonhoc);
            viewHorder.img=convertView.findViewById(R.id.imgmonhoc);
            convertView.setTag(viewHorder);
        }
        else{
            viewHorder= (ViewHorder) convertView.getTag();
        }
        XemMon xemMons= xemMonList.get(position);
        viewHorder.txtmonhoc.setText(xemMons.getTenMon());
        viewHorder.img.setImageResource(xemMons.getHinh());
        return convertView;
    }
}
