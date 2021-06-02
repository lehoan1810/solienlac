package com.hoanle.solienlaccc.fragments.hocphi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoanle.solienlaccc.R;
import com.hoanle.solienlaccc.fragments.xemdiem.XemdiemMon;
import com.hoanle.solienlaccc.fragments.xemdiem.XemdiemMonAdapter;

import java.util.List;

public class XemHocPhiAdapter  extends BaseAdapter {
    private Context context;
    private int layout;
    private List<XemHocPhi> xemHocPhiList;

    public XemHocPhiAdapter(Context context, int layout, List<XemHocPhi> xemHocPhiList) {
        this.context = context;
        this.layout = layout;
        this.xemHocPhiList = xemHocPhiList;
    }

    @Override
    public int getCount() {
        return xemHocPhiList.size();
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

        TextView txtmonhoc, txtHocPhi;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHorder viewHorder;
        if(convertView==null)
        {
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(layout,null);
            viewHorder=new ViewHorder();
            viewHorder.txtmonhoc    = convertView.findViewById(R.id.tv_hpMon);
            viewHorder.txtHocPhi    = convertView.findViewById(R.id.tv_hpTien);
            convertView.setTag(viewHorder);
        }
        else{
            viewHorder= (ViewHorder) convertView.getTag();
        }
        XemHocPhi xemHocPhi= xemHocPhiList.get(position);
        viewHorder.txtmonhoc.setText(xemHocPhi.getTenMon());
        viewHorder.txtHocPhi.setText(xemHocPhi.getHocphi());
        //viewHorder.img.setImageResource(xemHocPhi.getHinh());
        return convertView;
    }
}
