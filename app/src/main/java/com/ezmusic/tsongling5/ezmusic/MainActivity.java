package com.ezmusic.tsongling5.ezmusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.ezmusic.tsongling5.ezmusic.QQMusic.QSearchResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {




    /**************************
    *
    * Other parameter
    *
    *
    * */

    private ImageButton mSearchButton;

    /**
     *
     *
     * Search
     *
     *
     * */

    private SearchView mSearchView;


    /**
     *
     *
     * */
    private GitHubService service;
    private Call<QSearchResult> call;


    /*
    *
    * player
    *
    * */

    private String testUrl="http://ws.stream.qqmusic.qq.com/101787866.m4a?fromtag=46";         //天涯过客

    private ImageView play;
    private Player player;
    private SeekBar skbProgress;
    private boolean playFlag=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

//        toolbar.navig

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setImageResource(R.drawable.player_lrc);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        mSearchButton=(ImageButton)findViewById(R.id.search_button);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,SearchActivity.class);
                startActivity(i);
            }
        });



/***************************
 *
 *
 *
 *
 *
 * */


        String word="天涯过客";
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://s.music.qq.com/")
                .build();
        service = retrofit.create(GitHubService.class);

        /**
         *
         *
         *
         *
         * */

        mSearchView=(SearchView)findViewById(R.id.searchView) ;
        mSearchView.setIconifiedByDefault(true);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setInputType(10001);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                String word=mSearchView.getQuery().toString();
                call = service.getWord(0,"num",1,1,0,"json","GB2312","utf-8",0,"jqminiframe.json",0,1,0,"sizer.newclient.next_song",word);
                call.enqueue(new Callback<QSearchResult>() {
                    @Override
                    public void onResponse(Call<QSearchResult> call, Response<QSearchResult> response) {
//                Message msg = new Message();
                        QSearchResult user=response.body();
                        String song=user.getData().getSong().getList().get(0).getF();
                        String id;
                        id=song.substring(0,song.indexOf("|"));

                        String testUrl="http://ws.stream.qqmusic.qq.com/"+id+".m4a?fromtag=46";
                        player.playUrl(testUrl);
//                            List<Song> songs=user.getData().getSong().getList().indexOf(0);
                        Toast.makeText(MainActivity.this,id,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<QSearchResult> call, Throwable throwable) {

                    }
                });
                Toast.makeText(MainActivity.this, "submit", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });



//        抽屉初始化
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();


//        导航栏初始化
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);





        /*
        *
        *
        *
        * */

        skbProgress = (SeekBar) this.findViewById(R.id.skbProgress);
        skbProgress.setOnSeekBarChangeListener(new SeekBarChangeEvent());

        player=new Player(skbProgress);
        play=(ImageView)findViewById(R.id.imageView_play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(playFlag) {
                    playFlag=false;
                    play.setImageResource(R.drawable.player_pause);
                    Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                    player.playUrl(testUrl);
//                    player.play();
                }
                else
                {
                    play.setImageResource(R.drawable.player_play);
                    playFlag=true;
                    player.pause();
                }
            }
        });




        /**
         *
         *
         *
         * */

//        http://s.music.qq.com/fcgi-bin/music_search_new_platform?t=0&n=${num}&aggr=1&cr=1&loginUin=0&format=json&inCharset=GB2312&outCharset=utf-8&notice=0&platform=jqminiframe.json&needNewCode=0&p=1&catZhida=0&remoteplace=sizer.newclient.next_song&w=${%E5%A4%A9%E6%B6%AF%E8%BF%87%E5%AE%A2}








    }



    class SeekBarChangeEvent implements SeekBar.OnSeekBarChangeListener {
        int progress;

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            // 原本是(progress/seekBar.getMax())*player.mediaPlayer.getDuration()
            this.progress = progress * player.mediaPlayer.getDuration()
                    / seekBar.getMax();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // seekTo()的参数是相对与影片时间的数字，而不是与seekBar.getMax()相对的数字
            player.mediaPlayer.seekTo(progress);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    http://s.music.qq.com/fcgi-bin/music_search_new_platform?t=0&n=${num}&aggr=1&cr=1&loginUin=0&format=json&inCharset=GB2312&outCharset=utf-8&notice=0&platform=jqminiframe.json&needNewCode=0&p=1&catZhida=0&remoteplace=sizer.newclient.next_song&w={name}



    public interface GitHubService {
        @GET("fcgi-bin/music_search_new_platform")
        Call<QSearchResult> getWord(@Query("t") int t,@Query("n")  String n,@Query("aggr") int aggr,@Query("cr") int cr,@Query("loginUin") int loginUin,@Query("format") String format,@Query("inCharset") String inCharset,@Query("outCharset") String outCharset,@Query("notice") int notice,@Query("platform") String platform,@Query("needNewCode") int needNewCode,@Query("p") int p,@Query("catZhida") int catZhida,@Query("remoteplace") String remoteplace,@Query("w") String w);
    }
}
