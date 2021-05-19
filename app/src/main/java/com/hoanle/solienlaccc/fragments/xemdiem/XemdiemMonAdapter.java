package com.hoanle.solienlaccc.fragments.xemdiem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoanle.solienlaccc.R;

import java.util.List;

public class XemdiemMonAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<XemdiemMon> xemdiemMonList;

    public XemdiemMonAdapter(Context context, int layout, List<XemdiemMon> xemdiemMonList) {
        this.context = context;
        this.layout = layout;
        this.xemdiemMonList = xemdiemMonList;
    }

    @Override
    public int getCount() {
        return xemdiemMonList.size();
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
        XemdiemMon xemdiemMons= xemdiemMonList.get(position);
        viewHorder.txtmonhoc.setText(xemdiemMons.getTenMon());
        viewHorder.img.setImageResource(xemdiemMons.getHinh());
        return convertView;
    }
}
