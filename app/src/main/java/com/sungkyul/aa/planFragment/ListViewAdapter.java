package com.sungkyul.aa.planFragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sungkyul.aa.R;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>() ;

    // ListViewAdapter의 생성자
    public ListViewAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final int pos = position;
        final Context context = parent.getContext();
        ViewHolder holder;

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.plan_list, parent, false);


            ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.list_item_progress_bar) ;
            holder.progressBar = progressBar;
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.imageView1) ;
        TextView titleTextView = (TextView) convertView.findViewById(R.id.textView1) ;
        TextView currentTime = (TextView)convertView.findViewById(R.id.plan_txt_nowTime);
        TextView GoalTime = (TextView)convertView.findViewById(R.id.plan_txt_GoalTime);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ListViewItem listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        iconImageView.setImageDrawable(listViewItem.getIcon());
        titleTextView.setText(listViewItem.getTitle());
        holder.progressBar.setProgress(listViewItem.getProgressBar());


        String SStartHour;
        String SStartMinute;
        int StartTimeDate = Integer.parseInt(listViewItem.getCurrentTime());
        int StartHour = StartTimeDate/60;
        if(StartHour<10){
            SStartHour = 0 + "" + StartHour;
        }else{
            SStartHour = StartHour + "";
        }
        StartTimeDate %= 60;
        if(StartTimeDate<10){
            SStartMinute = 0 + "" + StartTimeDate;
        }else{
            SStartMinute = StartTimeDate + "";
        }
        currentTime.setText("current : " + SStartHour + "시" + SStartMinute + "분");

        String SGoalHour;
        String SGoalMinute;
        int GoalTimeDate = Integer.parseInt(listViewItem.getGoalTime());
        int GoalHour = GoalTimeDate/60;
        if(GoalHour<10){
            SGoalHour = 0 + "" + GoalHour;
        }else{
            SGoalHour = GoalHour + "";
        }
        GoalTimeDate %= 60;
        if(GoalTimeDate<10){
            SGoalMinute = 0 + "" + GoalTimeDate;
        }else{
            SGoalMinute = GoalTimeDate + "";
        }
        GoalTime.setText("Goal : "+ SGoalHour + "시" + SGoalMinute + "분");

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(Drawable icon, String title,String  currentTime, String GoalTime , int progressBar) {
        ListViewItem item = new ListViewItem();

        item.setIcon(icon);
        item.setTitle(title);
        item.setProgressBar(progressBar);
        item.setCurrentTime(currentTime);
        item.setGoalTime(GoalTime);

        listViewItemList.add(item);
    }


    class ViewHolder{
        ProgressBar progressBar;
    }
}