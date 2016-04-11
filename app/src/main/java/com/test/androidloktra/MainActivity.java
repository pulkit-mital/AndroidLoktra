package com.test.androidloktra;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.test.androidloktra.adapter.RecyclerAdapter;
import com.test.androidloktra.model.Commits;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private RecyclerAdapter recyclerAdapter;
    private LinearLayoutManager recyclerViewLayoutManager;
    private static String TAG = MainActivity.class.getSimpleName();
    private ArrayList<Commits> commitList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        loadingData();

    }

    /**
     * Loading data from the Github Api parsing it in to ArrayList.
     */

    public void loadingData() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setMessage("Loading Data...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://api.github.com/repos/rails/rails/commits", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                commitList = new ArrayList<>();

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String sha = jsonObject.getString("sha");
                        String commiterName = jsonObject.getJSONObject("commit").getJSONObject("committer").getString("name");
                        String message = jsonObject.getJSONObject("commit").getString("message");
                        commitList.add(new Commits(commiterName, sha, message));

                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }

                }

                initializeRecyclerView(commitList);
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage());
                Toast.makeText(MainActivity.this, "Unable to load data", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

        App.getVolleyRequestQueue().add(jsonArrayRequest);

    }

    /**
     * initializing RecyclerAdapter and setting data in recycler view.
     *
     * @param commitList
     */
    private void initializeRecyclerView(ArrayList<Commits> commitList) {
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            recyclerViewLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(recyclerViewLayoutManager);
            recyclerAdapter = new RecyclerAdapter(this, commitList);
            recyclerView.setAdapter(recyclerAdapter);
        }
    }

}
