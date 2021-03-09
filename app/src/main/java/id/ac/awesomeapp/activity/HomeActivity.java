package id.ac.awesomeapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.ac.awesomeapp.R;
import id.ac.awesomeapp.adapter.ImageAdapter;
import id.ac.awesomeapp.model.ImageModel;
import id.ac.awesomeapp.viewmodels.HomeViewModel;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageAdapter imageAdapter;
    List<ImageModel> imageModels;
    TextView title;
    LinearLayout changeToGrid, changeToList;
    HomeViewModel model;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        model = new ViewModelProvider(this).get(HomeViewModel.class);
        initVariable();
        setData();
        fetchImage();
    }

    private void setData(){
        changeToGrid = findViewById(R.id.changeToGrid);
        changeToList = findViewById(R.id.changeToList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }
    private void initVariable(){
        recyclerView = findViewById(R.id.listImage);
        imageModels = new ArrayList<>();
        imageAdapter = new ImageAdapter(this, imageModels);
        title = findViewById(R.id.Title);
        title.setText(model.Title);
        recyclerView.setAdapter(imageAdapter);
    }
    public void changeLayout(View view) {
        model.ChangeGrid();
    }

    public void fetchImage(){
        String url = "https://api.pexels.com/v1/curated?page=1&per_page=80";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("photos");
                    int lenght = jsonArray.length();

                    for(int a=0 ; a<lenght; a++){
                        JSONObject object = jsonArray.getJSONObject(a);

                        int Id = object.getInt("id");
                        String Photographer = object.getString("photographer");
                        String Photographer_url = object.getString("photographer_url");
                        JSONObject objectImage = object.getJSONObject("src");

                        String UrlPhoto = objectImage.getString("medium");

                        ImageModel imageModel = new ImageModel(Photographer,Photographer_url, Id, UrlPhoto);
                        imageModels.add(imageModel);
                    }
                    imageAdapter.notifyDataSetChanged();
                }
                catch (JSONException e)
                {
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization","563492ad6f917000010000016b9fbfbca55d4551aa0f1fa904cb2e29");
                return super.getHeaders();
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }
}
