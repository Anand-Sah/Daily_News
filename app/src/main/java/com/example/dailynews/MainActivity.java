package com.example.dailynews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    News_AdapterClass newsAdapterClass;
    ArrayList<Item_GetterSetter> newsList;
    RequestQueue requestQueue;
    String indianNewsURL, globalNewsURL;
    ImageView imageView;
    TextView textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViews();
        textView.setBackgroundColor(Color.parseColor("#BBD4E3"));
        setJSONArray(indianNewsURL,"The Times of India");
    }

    private void setJSONArray(String url, final String sourceName) {
        newsList = new ArrayList<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        recyclerView.setBackgroundColor(Color.parseColor("#BBD4E3"));
                        recyclerView.setVisibility(View.VISIBLE);
                        imageView.setVisibility(View.INVISIBLE);

                        try {
                            JSONArray jsonArray = response.getJSONArray("articles");

                            for(int i=0; i< jsonArray.length(); i++){

                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                JSONObject source = jsonObject.getJSONObject("source");
                                String name = source.getString("name");

                                if(name.equals(sourceName)) {
                                    String title = jsonObject.getString("title");
                                    String description = jsonObject.getString("description");
                                    String urlToImage = jsonObject.getString("urlToImage");
                                    String pageURL = jsonObject.getString("url");
                                    String publishedAt = jsonObject.getString("publishedAt");

                                    String date = publishedAt.substring(8,10)+"/"+publishedAt.substring(5,7)+"/"+publishedAt.substring(0,4);
                                    String time = publishedAt.substring(11,16);

                                    newsList.add(new Item_GetterSetter(title, description, pageURL, urlToImage, date, time));
                                }
                            }
                            // if the API does not contain any news from a specific source, show "no_result_found" imageView.
                            if(newsList.size()==0){
                                imageView.setVisibility(View.VISIBLE);
                                recyclerView.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                                imageView.setBackground(getResources().getDrawable(R.drawable.no_result_found));
                            }
                            newsAdapterClass = new News_AdapterClass(MainActivity.this, newsList);
                            recyclerView.setAdapter(newsAdapterClass);

                            // move to NewsDetails activity with the url of the clicked news
                            newsAdapterClass.OnCardViewClicked(new News_AdapterClass.GetClickedPosition() {
                                @Override
                                public void getPos(int pos) {
                                    Item_GetterSetter item = newsList.get(pos);
                                    Intent intent = new Intent(MainActivity.this, NewsDetails.class);
                                    intent.putExtra("PageURL", item.getPageURL());
                                    startActivity(intent);
                                }
                            });

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // if there is no internet connection, show "No Internet" vector.
                imageView.setVisibility(View.VISIBLE);
                imageView.setBackground(getResources().getDrawable(R.drawable.no_network));
                recyclerView.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this, "No Internet", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }


    private void setViews() {
        indianNewsURL = "https://newsapi.org/v2/top-headlines?country=in&apiKey=4cebb10b4a1f4d0bbf5891a94d07c365&pageSize=100";
        globalNewsURL = "https://newsapi.org/v2/top-headlines?q=all&apiKey=4cebb10b4a1f4d0bbf5891a94d07c365&pageSize=100";
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        requestQueue = Volley.newRequestQueue(this);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView); textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3); textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5); textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7); textView8 = findViewById(R.id.textView8);
        textView9 = findViewById(R.id.textView9); textView10 = findViewById(R.id.textView10);

    }
    public void TimesOfIndia(View view){
        unSelectPreviousButton();
        textView.setBackgroundColor(Color.parseColor("#BBD4E3"));
        setJSONArray(indianNewsURL,"The Times of India");
    }

    public void CBCNews(View view){
        unSelectPreviousButton();
        textView2.setBackgroundColor(Color.parseColor("#BBD4E3"));
        setJSONArray(globalNewsURL,"CBC News");
    }

    public void NDTVNews(View view){
        unSelectPreviousButton();
        textView3.setBackgroundColor(Color.parseColor("#BBD4E3"));
        setJSONArray(indianNewsURL,"NDTV News");
    }

    public void FinancialTimes(View view){
        unSelectPreviousButton();
        textView4.setBackgroundColor(Color.parseColor("#BBD4E3"));
        setJSONArray(globalNewsURL,"Financial Times");
    }

    public void TheHindu(View view){
        unSelectPreviousButton();
        textView5.setBackgroundColor(Color.parseColor("#BBD4E3"));
        setJSONArray(indianNewsURL,"The Hindu");
    }

    public void HindustanTimes(View view){
        unSelectPreviousButton();
        textView6.setBackgroundColor(Color.parseColor("#BBD4E3"));
        setJSONArray(indianNewsURL,"Hindustan Times");
    }

    public void News18(View view){
        unSelectPreviousButton();
        textView7.setBackgroundColor(Color.parseColor("#BBD4E3"));
        setJSONArray(indianNewsURL,"News18");
    }

    public void Buzzfeed(View view){
        unSelectPreviousButton();
        textView8.setBackgroundColor(Color.parseColor("#BBD4E3"));
        setJSONArray(globalNewsURL,"Buzzfeed");
    }

    public void FoxNews(View view){
        unSelectPreviousButton();
        textView9.setBackgroundColor(Color.parseColor("#BBD4E3"));
        setJSONArray(globalNewsURL,"Fox News");
    }

    public void NBCNews(View view){
        unSelectPreviousButton();
        textView10.setBackgroundColor(Color.parseColor("#BBD4E3"));
        setJSONArray(globalNewsURL,"NBC News");
    }


    private void unSelectPreviousButton() {
        textView.setBackgroundColor(Color.parseColor("#FFFFFFFF")); textView2.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        textView3.setBackgroundColor(Color.parseColor("#FFFFFFFF")); textView4.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        textView5.setBackgroundColor(Color.parseColor("#FFFFFFFF")); textView6.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        textView7.setBackgroundColor(Color.parseColor("#FFFFFFFF")); textView8.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        textView9.setBackgroundColor(Color.parseColor("#FFFFFFFF")); textView10.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
    }
}
