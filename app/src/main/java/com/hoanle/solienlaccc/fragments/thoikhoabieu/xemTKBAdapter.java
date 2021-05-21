package com.hoanle.solienlaccc.fragments.thoikhoabieu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoanle.solienlaccc.R;

import java.util.List;

public class xemTKBAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<xemTKB> xemTKBList;

    public xemTKBAdapter(Context context, int layout, List<xemTKB> xemTKBList) {
        this.context = context;
        this.layout = layout;
        this.xemTKBList = xemTKBList;
    }

    @Override
    public int getCount() {
        return xemTKBList.size();
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
        TextView txtthu;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHorder viewHorder;
        if(convertView==null)
        {
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(layout,null);
            viewHorder=new ViewHorder();
            viewHorder.txtthu= convertView.findViewById(R.id.txtThu);
            viewHorder.img=convertView.findViewById(R.id.imgtkb);
            convertView.setTag(viewHorder);
        }
        else{
            viewHorder= (ViewHorder) convertView.getTag();
        }
        xemTKB thoikhoabieu= xemTKBList.get(position);
        viewHorder.txtthu.setText(thoikhoabieu.getThu());
        viewHorder.img.setImageResource(thoikhoabieu.getHinh());
        return convertView;
    }
}
