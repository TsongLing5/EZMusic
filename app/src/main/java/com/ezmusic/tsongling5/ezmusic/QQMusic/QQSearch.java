package com.ezmusic.tsongling5.ezmusic.QQMusic;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by TsongLing5 on 2017/11/2.
 */

public class QQSearch {

    private static QQSearch mQQSearch=new QQSearch();

    private Call<QSearchResult> call;
    private GitHubService service;

    private QSearchResult mQSearchResult;


    private  QQSearch(){

    }
    public static QQSearch getInstance(){
        return mQQSearch;
    }
    public void searchSongs(String songs, final QResultListener listener){

        String word="天涯过客";
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://s.music.qq.com/")
                .build();
        service = retrofit.create(QQSearch.GitHubService.class);



//        String word=mSearchView.getQuery().toString();
        call = service.getWord(0,"num",1,1,0,"json","GB2312","utf-8",0,"jqminiframe.json",0,1,0,"sizer.newclient.next_song",word);
        call.enqueue(new Callback<QSearchResult>() {
            @Override
            public void onResponse(Call<QSearchResult> call, Response<QSearchResult> response) {
//                Message msg = new Message();
                QSearchResult user=response.body();
                listener.onSuccess(user);
                String song=user.getData().getSong().getList().get(0).getF();
                String id;
                id=song.substring(0,song.indexOf("|"));

                String testUrl="http://ws.stream.qqmusic.qq.com/"+id+".m4a?fromtag=46";


//                player.playUrl(testUrl);
//                            List<Song> songs=user.getData().getSong().getList().indexOf(0);
//                Toast.makeText(QQSearch.this,id,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<QSearchResult> call, Throwable throwable) {

            }
        });

    }




    public interface GitHubService {
        @GET("fcgi-bin/music_search_new_platform")
        Call<QSearchResult> getWord(@Query("t") int t, @Query("n")  String n, @Query("aggr") int aggr, @Query("cr") int cr, @Query("loginUin") int loginUin, @Query("format") String format, @Query("inCharset") String inCharset, @Query("outCharset") String outCharset, @Query("notice") int notice, @Query("platform") String platform, @Query("needNewCode") int needNewCode, @Query("p") int p, @Query("catZhida") int catZhida, @Query("remoteplace") String remoteplace, @Query("w") String w);
    }
}
