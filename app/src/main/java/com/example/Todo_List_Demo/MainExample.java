//package com.example.firebasedemo;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.EventListener;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.FirebaseFirestoreException;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import com.google.firebase.firestore.QuerySnapshot;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import javax.annotation.Nullable;
//
//public class MainExample {
//    Button LogOut, Add;
//    EditText Name;
//    android.widget.ListView ListView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        LogOut = (Button) findViewById(R.id.LogOut);
//    Name = (EditText) findViewById(R.id.Name);
//    Add = (Button) findViewById(R.id.Add);
//    ListView = (ListView) findViewById(R.id.ListView);
//        LogOut.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            FirebaseAuth.getInstance().signOut();
//            Toast.makeText(MainActivity.this, "LogOut", Toast.LENGTH_LONG).show();
//            startActivity(new Intent(MainActivity.this, StartActivity.class));
//        }
//    });
//    //add data to database
////     //   FirebaseDatabase.getInstance().getReference().child("ProgrammingKnowladge").child("Android").setValue("abcd");
//
//
//    //add Multiple data in a database
////        HashMap<String,Object> map = new HashMap<>();
////        map.put("Name","Kashika");
////        map.put("Email","KashikaKhatri08@gmail.com");
////        FirebaseDatabase.getInstance().getReference().child("ProgrammingKnowladge").child("MultiplaeValues").updateChildren(map);
//
//        FirebaseDatabase.getInstance().getReference().child("ProgrammingKnowladge").child("Android").setValue("abcd");
////Take data from a view and add to the database
//        Add.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            String Text_name = Name.getText().toString();
//            if (Text_name.isEmpty()) {
//                Toast.makeText(MainActivity.this, "No Name Entered", Toast.LENGTH_LONG).show();
//            } else {
//
//                //FirebaseDatabase.getInstance().getReference().child("ProgrammingKnowladge").push().child("Name").setValue(Text_name);
//                FirebaseDatabase.getInstance().getReference().child("Languages").child("Name").setValue(Text_name);
//
//            }
//        }
//    });
//    //Retrive get data from database
////          FirebaseDatabase.getInstance().getReference().child("Languages").child("n1").setValue("Java");
////        FirebaseDatabase.getInstance().getReference().child("Languages").child("n2").setValue("Kotlin");
////        FirebaseDatabase.getInstance().getReference().child("Languages").child("n3").setValue("Flutter");
////        FirebaseDatabase.getInstance().getReference().child("Languages").child("n4").setValue("React Native");
//
//    //        final ArrayList<String> list = new ArrayList<>();
////        final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.item_list, list);
////        ListView.setAdapter(adapter);
////        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Languages");
////        reference.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                list.clear();
////                for (DataSnapshot snapShort : dataSnapshot.getChildren()) {
////                    list.add(snapShort.getValue().toString());
////                }
////                adapter.notifyDataSetChanged();
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError databaseError) {
////
////            }
////        });
////
////    }
//    // retrive data of multiple branches of a tree in a database
//    HashMap<String, Object> map1 = new HashMap<>();
//        map1.put("email", "KashikaKhatri08@gmail.com");
//        map1.put("name", "kashika");
//
//        FirebaseDatabase.getInstance().getReference().child("Information").child("Branch1").updateChildren(map1);
//
//
//    HashMap<String, Object> map2 = new HashMap<>();
//        map2.put("email", "KashikaKhatri98@gmail.com");
//        map2.put("name", "Chinu");
//
//        FirebaseDatabase.getInstance().getReference().child("Information").child("Branch2").updateChildren(map2);
//
//    HashMap<String, Object> map3 = new HashMap<>();
//        map3.put("email", "KashikaKhatri67@gmail.com");
//        map3.put("name", "Kashi");
//
//        FirebaseDatabase.getInstance().getReference().child("Information").child("Branch3").updateChildren(map3);
//
//
//
//    final ArrayList<String> list = new ArrayList<>();
//    final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.item_list,R.id.label,list);
//        ListView.setAdapter(adapter);
//    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Information");
//        reference.addValueEventListener(new ValueEventListener() {
//        @Override
//        public void onDataChange (@NonNull DataSnapshot dataSnapshot){
//            list.clear();
//            for (DataSnapshot snapShort : dataSnapshot.getChildren()) {
//                Information info =snapShort.getValue(Information.class);
//                String Txt =info.getName()+":"+info.getEmail();
//                list.add(Txt);
//            }
//            adapter.notifyDataSetChanged();
//        }
//
//        @Override
//        public void onCancelled (@NonNull DatabaseError databaseError){
//
//        }
//    });
//
//    //save data in cloud
////        FirebaseFirestore db =FirebaseFirestore.getInstance();
////        Map<String,Object> city = new HashMap<>();
////        city.put("name","Jamshedpur");
////        city.put("state","Jharkhand");
////        city.put("country","India");
////
////        db.collection("Cities").document("JSR").set(city).addOnCompleteListener(new OnCompleteListener<Void>() {
////            @Override
////            public void onComplete(@NonNull Task<Void> task) {
////                if(task.isSuccessful()){
////
////                    Toast.makeText(MainActivity.this,"values added",Toast.LENGTH_LONG).show();
////
////
////
////                }
////            }
////        });
//    //add value in document of a cloud
////        FirebaseFirestore db =FirebaseFirestore.getInstance();
////        Map<String,Object> data = new HashMap<>();
////        data.put("capital",false);
////        db.collection("Cities").document("JSR").set(data, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
////            @Override
////            public void onComplete(@NonNull Task<Void> task) {
////                if(task.isSuccessful()){
////
////                    Toast.makeText(MainActivity.this,"Merge values added",Toast.LENGTH_LONG).show();
////
////
////
////                }
////            }
////        });
////             generate random key of a collection
////              FirebaseFirestore db =FirebaseFirestore.getInstance();
////        Map<String,Object> data = new HashMap<>();
////        data.put("Name","Tokiyo");
////        data.put("Capital","Japan");
////        db.collection("Cities").add(data).addOnCompleteListener(new  OnCompleteListener<DocumentReference>() {
////            @Override
////            public void onComplete(@NonNull Task<DocumentReference> task) {
////                if(task.isSuccessful()){
////
////                    Toast.makeText(MainActivity.this,"values added successfully",Toast.LENGTH_LONG).show();
//////
//////
//////
////                }
////            }
////        });
////Update
//    DocumentReference ref=FirebaseFirestore.getInstance().collection("Cities").document("JSR");
//        ref.update("capital",true);
//    //retrive
//    DocumentReference Docref=FirebaseFirestore.getInstance().collection("Cities").document("JSR");
//        Docref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//        @Override
//        public void onComplete(@NonNull Task< DocumentSnapshot > task) {
//            if(task.isSuccessful()){
//                DocumentSnapshot doc=task.getResult();
//                if(doc.exists()){
//                    Log.d("Document",doc.getData().toString());
//
//
//                }
//                else{
//                    Log.d("Document","No Data");
//                }
////                    Toast.makeText(MainActivity.this,"values added successfully",Toast.LENGTH_LONG).show();
//
//
//
//            }
//        }
//    });
//        FirebaseFirestore.getInstance().collection("Cities").whereEqualTo("capital",true).get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//        @Override
//        public void onComplete(@NonNull Task< QuerySnapshot > task) {
//            if(task.isSuccessful()){
//                for(QueryDocumentSnapshot doc :task.getResult()){
//                    Log.d("Document",doc.getId()+"->"+doc.getData());
//                }
//            }
//        }
//    });
//    //realTimeListener
//        FirebaseFirestore.getInstance().collection("Cities").document("JSR").addSnapshotListener(new EventListener<DocumentSnapshot>() {
//        @Override
//        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
//
//        }
//    });
//
//}
