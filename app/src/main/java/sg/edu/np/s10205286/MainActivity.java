package sg.edu.np.s10205286;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DBHandler db = new DBHandler(MainActivity.this, "UserDB", null, 1);
    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent receiveUser = getIntent();
        int index = receiveUser.getIntExtra("id", 0);
        currentUser = db.locateUser(index);

        // Set Text
        TextView name = findViewById(R.id.username);
        name.setText(currentUser.name);

        TextView desc = findViewById(R.id.user_description);
        desc.setText(currentUser.description);

        Button fllw_btn = findViewById(R.id.btnFollow);

        // Set Text if followed / not followed
        fllw_btn.setText(currentUser.isFollowed() ? "Unfollow" : "Follow");

        fllw_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If click unfollow
                if (currentUser.isFollowed()) {
                    fllw_btn.setText("Follow");

                    Toast toast = Toast.makeText(MainActivity.this, "Unfollowed!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else { // If click follow
                    fllw_btn.setText("Unfollow");

                    Toast toast = Toast.makeText(MainActivity.this, "Followed!", Toast.LENGTH_SHORT);
                    toast.show();
                }

                currentUser.setFollowed(!currentUser.isFollowed());
                db.updateUser(currentUser);
            }
        });

        Log.d("debug", "create");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("debug", "start");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("debug", "stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("debug", "destroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("debug", "pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("debug", "resume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("debug", "restart");
    }
}