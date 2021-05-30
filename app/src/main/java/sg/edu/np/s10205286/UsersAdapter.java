package sg.edu.np.s10205286;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.lang.String;

public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {
    ArrayList<User> data;

    // Constructor
    public UsersAdapter(ArrayList<User> d){
        data = d;
    }

    // Must Override
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = null;

        if (viewType == 0) { // If last character is 7, viewType = 0
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.vh_biguser, parent, false);
        }
        else
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.vh_user, parent, false);

        return new UserViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User u = data.get(position);

        holder.uName.setText(u.getName());
        holder.description.setText(u.getDescription());
        holder.image.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                builder.setTitle("Profile")
                        .setMessage(holder.uName.getText())
                        .setCancelable(true)
                        .setNegativeButton("Close", null)
                        .setPositiveButton("View", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent movePage = new Intent(v.getContext(), MainActivity.class);
                                movePage.putExtra("id", position);

                                v.getContext().startActivity(movePage);
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        User u = data.get(position);

        if (u.name.endsWith("7"))
            return 0;
        return 1;
    }
}
