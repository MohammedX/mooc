package com.mooc.mooc;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Change with the root url
    private String URL_ROOT = "http://192.168.43.195/MOOC_Organizer/";
    private final String URL_API = "getCourses.php";
    private final String URL_IMG = "";

    private List<CoursesList> coursesLists = new ArrayList<>();
    private RecyclerView recyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private CoursesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private CoursesList coursesList;
    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.courses_by_swipe_refresh_layout);
        recyclerView = (RecyclerView) findViewById(R.id.courses_by_recycler_view);
        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);

        mAdapter = new CoursesAdapter(coursesLists);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // Refresh items
                mSwipeRefreshLayout.setRefreshing(true);
                coursesLists.clear();
                URL_ROOT = "http://"+editText.getText().toString()+"/MOOC_Organizer/";
                prepareCoursesData();
                recyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void prepareCoursesData() {
        StringRequest strReq = new StringRequest(Request.Method.POST, URL_ROOT + URL_API, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                if (!response.equals("nodata")) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int x = 0; x < jsonArray.length(); x++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(x);
                            coursesList = new CoursesList(jsonObject.getString("courseName"), jsonObject.getString("providerName"), jsonObject.getString("startDate") + " - " + jsonObject.getString("endDate"), jsonObject.getString("areaOfCourse"), jsonObject.getString("instructorName"), jsonObject.getString("url"), URL_ROOT + URL_IMG + jsonObject.getString("picture").replace("\\", "").replace(" ", "%20"));
                            coursesLists.add(coursesList);
                            mAdapter.notifyDataSetChanged();
                        }

                    } catch (JSONException e) {
                        // JSON error
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    textView.setVisibility(View.VISIBLE);
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(getApplicationContext().getPackageResourcePath(), "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(strReq);

    }
}
