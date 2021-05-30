package sg.edu.np.s10205286;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    public TextView uName, description;
    public ImageView image;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);

        uName = itemView.findViewById(R.id.userNameList);
        description = itemView.findViewById(R.id.userDescriptionList);
        image = itemView.findViewById(R.id.img_profile);
    }
}
