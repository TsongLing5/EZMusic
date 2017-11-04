package com.ezmusic.tsongling5.ezmusic;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ezmusic.tsongling5.ezmusic.QListView.QItem;
import com.ezmusic.tsongling5.ezmusic.QListView.QListAdapter;
import com.ezmusic.tsongling5.ezmusic.QQMusic.QQSearch;
import com.ezmusic.tsongling5.ezmusic.QQMusic.QResultListener;
import com.ezmusic.tsongling5.ezmusic.QQMusic.QSearchResult;

import java.util.ArrayList;
import java.util.Vector;

public class SearchActivity extends AppCompatActivity {


//    private GitHubService service;
//    private Call<QSearchResult> call;

    private QQSearch mQQSearch;
    private java.util.List<QItem> Qlist=new ArrayList<QItem>();

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("");
        setSupportActionBar(toolbar);



        mContext = this;

        mQQSearch=QQSearch.getInstance();
        mQQSearch.searchSongs("天涯过客", new QResultListener() {
            @Override
            public void onSuccess(QSearchResult result) {
                String song=result.getData().getSong().getList().get(0).getF();
                String id;
                id=song.substring(0,song.indexOf("|"));

                String testUrl="http://ws.stream.qqmusic.qq.com/"+id+".m4a?fromtag=46";
                Toast.makeText(SearchActivity.this,testUrl,Toast.LENGTH_SHORT).show();
            }
        });

//        String word="天涯过客";
//        Retrofit retrofit = new Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl("http://s.music.qq.com/")
//                .build();
//        service = retrofit.create(GitHubService.class);
//
//
//
////        String word=mSearchView.getQuery().toString();
//        call = service.getWord(0,"num",1,1,0,"json","GB2312","utf-8",0,"jqminiframe.json",0,1,0,"sizer.newclient.next_song",word);
//        call.enqueue(new Callback<QSearchResult>() {
//            @Override
//            public void onResponse(Call<QSearchResult> call, Response<QSearchResult> response) {
////                Message msg = new Message();
//                QSearchResult user=response.body();
//                String song=user.getData().getSong().getList().get(0).getF();
//                String id;
//                id=song.substring(0,song.indexOf("|"));
//
//                String testUrl="http://ws.stream.qqmusic.qq.com/"+id+".m4a?fromtag=46";
////                player.playUrl(testUrl);
////                            List<Song> songs=user.getData().getSong().getList().indexOf(0);
//                Toast.makeText(SearchActivity.this,id,Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onFailure(Call<QSearchResult> call, Throwable throwable) {
//
//            }
//        });



        ListView Qlistview = new ListView(mContext);
        ListView listview2 = new ListView(mContext);
        ListView listview3 = new ListView(mContext);

        Vector<View> pages = new Vector<View>();


        QItem q=new QItem("七里香","周杰伦","七里香");
        Qlist.add(q);
        QItem p=new QItem("天涯过客","周杰伦","七里香");
        Qlist.add(p);



        pages.add(Qlistview);
        pages.add(listview2);
        pages.add(listview3);

        ViewPager vp = (ViewPager) findViewById(R.id.viewpagerSearch);
        vp.setAdapter(new CustomPagerAdapter(mContext, pages));

//        Qlistview.setAdapter(new ArrayAdapter<String>(mContext,
//                android.R.layout.simple_list_item_1, new String[] { "A1", "B1",
//                "C1", "D1" }));
        QListAdapter QAdapter=new QListAdapter(mContext,R.layout.qq_listview_item,Qlist);
        Qlistview.setAdapter(QAdapter);
        listview2.setAdapter(new ArrayAdapter<String>(mContext,
                android.R.layout.simple_list_item_1, new String[] { "A2", "B2",
                "C2", "D2" }));
        listview3.setAdapter(new ArrayAdapter<String>(mContext,
                android.R.layout.simple_list_item_1, new String[] { "A3", "B3",
                "C3", "D3" }));


    }



//    public interface GitHubService {
//        @GET("fcgi-bin/music_search_new_platform")
//        Call<QSearchResult> getWord(@Query("t") int t, @Query("n")  String n, @Query("aggr") int aggr, @Query("cr") int cr, @Query("loginUin") int loginUin, @Query("format") String format, @Query("inCharset") String inCharset, @Query("outCharset") String outCharset, @Query("notice") int notice, @Query("platform") String platform, @Query("needNewCode") int needNewCode, @Query("p") int p, @Query("catZhida") int catZhida, @Query("remoteplace") String remoteplace, @Query("w") String w);
//    }

}
