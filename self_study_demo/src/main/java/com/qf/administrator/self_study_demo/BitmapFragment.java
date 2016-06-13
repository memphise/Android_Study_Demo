package com.qf.administrator.self_study_demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BitmapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BitmapFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private BitmapCallBack bitmapCallBack;
    private Integer[] bitmaps;
    private List<String> list;
    private ListView listView;

    public BitmapFragment(){
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof BitmapCallBack){
            bitmapCallBack = (BitmapCallBack) context;
        }

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment BitmapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BitmapFragment newInstance(String param1){
        BitmapFragment fragment = new BitmapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
        bitmaps = new Integer[]{R.mipmap.wuming,R.mipmap.wuming1,R.mipmap.wuming2,R.mipmap.wuming3,R.mipmap.wuming4};
        list = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            list.add("wuming"+i);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bitmap, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.listView_bitmap);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),bitmaps[position]);
                bitmapCallBack.callBack(bitmap);
            }
        });

    }

    interface BitmapCallBack{
        void callBack(Bitmap bitmap);
    }
}
