package com.beginner.example3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.beginner.example3.database.RedditDAO;
import com.beginner.example3.model.Listing;
import com.beginner.example3.model.Post;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public final String REDDIT_URL = "https://www.reddit.com/r/all.json?limit=10";
    private RecyclerView mrecyclerView;

//    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mrecyclerView=findViewById(R.id.recyclerListview);
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());
        //Retrieve the queue from the connectionmanager class
//        RequestQueue queue = ConnectionManager.getInstance(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, REDDIT_URL,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        Listing mylist=new Gson().fromJson(response,Listing.class);
                        List<Post> postList=mylist.getPostlist();
                        RedditAdapter Adapter=new RedditAdapter(postList,getApplicationContext());
                        RedditDAO.getsInstance().storePosts(MainActivity.this,postList);
                        mrecyclerView.setAdapter(Adapter);
//                        Toast.makeText(MainActivity.this,mylist.getPostlist().size(),Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                                List<Post> postList=RedditDAO.getsInstance().getPostfromDB(MainActivity.this);
                RedditAdapter Adapter=new RedditAdapter(postList,getApplicationContext());
                mrecyclerView.setAdapter(Adapter);

            }
        });
        // Add the request to the RequestQueue.
        ConnectionManager.getInstance(this).add(stringRequest);
//        OkHttpClient client = new OkHttpClient();
//        Request request= new Request.Builder().url(REDDIT_URL).build();
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override/
//            public void onResponse(Call call, Response response) throws IOException {
//                final String myResponse = response.body().string();
//
//                MainActivity.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(MainActivity.this,myResponse,Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//
//            }
//        });
//        MyAsyncTask mytask=new MyAsyncTask();
////        mytask.execute("1st task","2nd task");
//            mytask.execute();
//    }

//    private class MyAsyncTask extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... strings) {
//            OkHttpClient client = new OkHttpClient();
//            Request request = new Request.Builder()
//                    .url(REDDIT_URL)
//                    .build();
//
//            Response response = null;
//            try {
//                client.newCall(request).execute();
//                return response.body().string();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//

////            String Firsttask=strings[0];
//            HttpClient httpClient=new DefaultHttpClient();
//            HttpGet httpGet=new HttpGet(REDDIT_URL);
//            try {
//                HttpResponse response=httpClient.execute(httpGet,new BasicHttpContext());
//                InputStream inputStream=response.getEntity().getContent();
//                result=InputStreamtoString(inputStream);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
////            return Firsttask;
//            return result;
        }
//        private String InputStreamtoString(InputStream inputStream){
//            String line="";
//            StringBuilder total= new StringBuilder();
//            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
//            try{while ((line= br.readLine())!=null)
//            {total.append(line);}}
//            catch (IOException e)
//            {e.printStackTrace();}
//
//            return total.toString();

//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
//
//        }
//
//
//    }
}


