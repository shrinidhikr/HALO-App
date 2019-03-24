package lohith.com.halo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private StorageReference videoRef;
    private Uri videouri;
    private static final int request_code = 101;
    TextView t1,t2,t3,t4;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    Intent intent = new Intent(MainActivity.this,video.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_notifications:
                    Intent intent1 = new Intent(MainActivity.this,History.class);
                    startActivity(intent1);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        t1 = (TextView)findViewById(R.id.textView2);
        t2 = (TextView)findViewById(R.id.textView5);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Patients");
        DatabaseReference itemsRef = rootRef.child("Lohith");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("kess", dataSnapshot.getValue().toString());
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(ds.getKey().toString().equals("age"))
                        t2.setText(ds.getValue().toString());
                    else if(ds.getKey().toString().equals("name"))
                        t1.setText(ds.getValue().toString());

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        itemsRef.addListenerForSingleValueEvent(eventListener);
    }

}