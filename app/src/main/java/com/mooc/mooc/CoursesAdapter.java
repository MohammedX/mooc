package com.mooc.mooc;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mx911 on 4/22/2016.
 */
public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.ViewHolder> {

    private List<CoursesList> CoursesLists;

    public CoursesAdapter(List<CoursesList> CoursesLists) {
        this.CoursesLists = CoursesLists;
    }

    @Override
    public CoursesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_list_view, null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CoursesAdapter.ViewHolder holder, int position) {
        final int pos = position;
        final CoursesList coursesList = CoursesLists.get(pos);

        holder.courseName.setText(coursesList.getCourseName());
        holder.providerName.setText(coursesList.getProviderName());
        holder.Date.setText(coursesList.getDate());
//        holder.areaOfCourse.setText(CoursesList.getAreaOfCourse());
        holder.instructorName.setText(coursesList.getInstructorName());

        Context pictureContext = holder.picture.getContext();
        Picasso.with(pictureContext).load(coursesList.getPicture()).into(holder.picture);

        final Context urlContext = holder.url.getContext();
        holder.url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = coursesList.getUrl();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                urlContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return CoursesLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView courseName,providerName,Date,areaOfCourse,instructorName;
        ImageView picture;
        Button url;

        public ViewHolder(View itemView) {
            super(itemView);

            courseName = (TextView) itemView.findViewById(R.id.course_name);
            providerName = (TextView) itemView.findViewById(R.id.provider_name);
            Date = (TextView) itemView.findViewById(R.id.list_date);
//            areaOfCourse = (TextView) itemView.findViewById(R.id.course_name);
            instructorName = (TextView) itemView.findViewById(R.id.instructor_name);

            picture = (ImageView) itemView.findViewById(R.id.list_image);

            url = (Button) itemView.findViewById(R.id.list_url);


        }
    }
}
