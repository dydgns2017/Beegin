package com.sungkyul.aa.Fragment;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.sungkyul.aa.AddWorkActivity;
import com.sungkyul.aa.R;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private final String dbName = "webnautes";
    private final String tableName = "person";
    private String names[];
    {
        names = new String[]{"Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb", "Ice Cream Sandwich", "Jelly Bean", "Kitkat"};
    }

    private final String images[];
    {
        images = new String[]{"Android 1.5", "Android 1.6", "Android 2.0", "Android 2.2", "Android 2.3", "Android  3.0", "Android  4.0", "Android  4.1", "Android  4.4"};
    }
    ArrayList<HashMap<String, String>> personList;
    ListView list;
    Button btnAddActivity;
    private static final String TAG_NAME = "name";
    private static final String TAG_IMAGE ="image";
    public static final int sub = 1001;
    SQLiteDatabase sampleDB = null;
/*    ListAdapter adapter;*/

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, null);

        btnAddActivity = (Button) view.findViewById(R.id.btnAddActivity);
        btnAddActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), AddWorkActivity.class);
                startActivityForResult(intent, sub);
                getActivity().finish();
            }
        });

        list = (ListView) view.findViewById(R.id.listView);
        personList = new ArrayList<HashMap<String, String>>();

        ArrayAdapter Adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, names);

        ListView listview = (ListView) view.findViewById(R.id.listView);
        listview.setAdapter(Adapter);

        return view;



       /* try {

            sampleDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);

            //테이블이 존재하지 않으면 새로 생성합니다.
            sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " + tableName
                    + " (name VARCHAR(20), phone VARCHAR(20) );");

            //테이블이 존재하는 경우 기존 데이터를 지우기 위해서 사용합니다.
            sampleDB.execSQL("DELETE FROM " + tableName  );

            //새로운 데이터를 테이블에 집어넣습니다..
            for (int i=0; i<names.length; i++ ) {
                sampleDB.execSQL("INSERT INTO " + tableName
                        + " (name, phone)  Values ('" + names[i] + "', '" + images[i]+"');");
            }

            Intent intent = getIntent();
            if(intent != null) {
                Option option = (Option) intent.getSerializableExtra("class");

                sampleDB.execSQL("INSERT INTO " + tableName
                        + " (name, phone)  Values ('" + option.getName() + "', '" + option.getAddr() +"');");
            }


            sampleDB.close();

        } catch (SQLiteException se) {
            Toast.makeText(getActivity().getApplicationContext(),  se.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("", se.getMessage());


        }
        showList();
        return inflater.inflate(R.layout.fragment_home, container, false);
    }*/

  /*  protected void showList(){
        try {
            SQLiteDatabase ReadDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);
            //SELECT문을 사용하여 테이블에 있는 데이터를 가져옵니다..
            Cursor c = ReadDB.rawQuery("SELECT * FROM " + tableName, null);

            if (c != null) {
                if (c.moveToFirst()) {
                    do {
                        //테이블에서 두개의 컬럼값을 가져와서
                        String Name = c.getString(c.getColumnIndex("name"));
                        String image = c.getString(c.getColumnIndex("phone"));
                        //HashMap에 넣습니다.
                        HashMap<String,String> persons = new HashMap<String,String>();
                        persons.put(TAG_NAME,Name);
                        persons.put(TAG_IMAGE, image);
                        //ArrayList에 추가합니다..
                        personList.add(persons);
                    } while (c.moveToNext());
                }
            }
            ReadDB.close();
            //새로운 apapter를 생성하여 데이터를 넣은 후..
            adapter = new SimpleAdapter(
                    this, personList, R.layout.list_item,
                    new String[]{TAG_NAME,TAG_IMAGE},
                    new int[]{ R.id.name, R.id.phone}
            );
            //화면에 보여주기 위해 Listview에 연결합니다.
            list.setAdapter(adapter);
        } catch (SQLiteException se) {
            Toast.makeText(getActivity().getApplicationContext(),  se.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("",  se.getMessage());
        }
    }*/


    }

}
