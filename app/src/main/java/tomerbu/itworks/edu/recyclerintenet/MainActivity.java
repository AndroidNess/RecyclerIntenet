package tomerbu.itworks.edu.recyclerintenet;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import tomerbu.itworks.edu.recyclerintenet.models.Child;
import tomerbu.itworks.edu.recyclerintenet.models.Data_;
import tomerbu.itworks.edu.recyclerintenet.models.RedditFeed;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        startWebClient();
    }

    private void startWebClient() {

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        URL webUrl = new URL("https://www.reddit.com/r/funny/.json");
                        URLConnection con = webUrl.openConnection();
                        InputStream inputStream = con.getInputStream();
                        InputStreamReader reader = new InputStreamReader(inputStream);

                        Gson gsonDesierializer = new Gson();

                        RedditFeed reddit = gsonDesierializer.fromJson(reader, RedditFeed.class);
                        final List<Child> children = reddit.getData().getChildren();


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //find the ListView->init the adapter->set the adapter
                                ListView lvReddit = (ListView) findViewById(R.id.lvReddit);
                                RedditAdapter adapter = new RedditAdapter(children, getLayoutInflater(),getApplicationContext());
                                lvReddit.setAdapter(adapter);
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            t.start();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
