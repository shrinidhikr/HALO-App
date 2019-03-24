package lohith.com.halo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class History extends AppCompatActivity {
TextView t6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        t6 = (TextView)findViewById(R.id.textView6);
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Patients");
        DatabaseReference itemsRef = rootRef.child("Lohith").child("Time_Stamp");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("kess", dataSnapshot.getValue().toString());
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        t6.setText(ds.getKey().toString()+"       "+ds.getValue().toString()+"\n");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        itemsRef.addListenerForSingleValueEvent(eventListener);
    }
}
