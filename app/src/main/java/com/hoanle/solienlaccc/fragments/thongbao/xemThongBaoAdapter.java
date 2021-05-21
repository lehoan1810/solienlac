package com.hoanle.solienlaccc.fragments.thongbao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoanle.solienlaccc.R;
import com.hoanle.solienlaccc.fragments.thoikhoabieu.xemTKB;
import com.hoanle.solienlaccc.fragments.thongbao.xemThongBao;

import java.util.List;

public class xemThongBaoAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<xemThongBao> xemThongBaoList;

    public xemThongBaoAdapter(Context context, int layout, List<xemThongBao> xemThongBaoList) {
        this.context = context;
        this.layout = layout;
        this.xemThongBaoList = xemThongBaoList;
    }

    @Override
    public int getCount() {
        return xemThongBaoList.size();
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
        TextView txtthongbao;
        TextView txtthoigian;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHorder viewHorder;
        if(convertView==null)
        {
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(layout,null);
            viewHorder=new ViewHorder();
            viewHorder.txtthongbao= convertView.findViewById(R.id.txtthongbao);
            viewHorder.txtthoigian= convertView.findViewById(R.id.txtthoigian);
            viewHorder.img=convertView.findViewById(R.id.imgthongbao);
            convertView.setTag(viewHorder);
        }
        else{
            viewHorder= (ViewHorder) convertView.getTag();
        }
        xemThongBao thongbao= xemThongBaoList.get(position);
        viewHorder.txtthongbao.setText(thongbao.getThongBao());
        viewHorder.txtthoigian.setText(thongbao.getThoiGian());
        viewHorder.img.setImageResource(thongbao.getHinh());
        return convertView;
    }
}
