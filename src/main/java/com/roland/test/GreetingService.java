package com.roland.test;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@Service
public class GreetingService {
    public void addGreeting(Greeting greeting) throws IOException, ExecutionException, InterruptedException {

        System.out.println("Insert Greeting");
        DocumentReference ref = retrieveDB().collection("Greeting").document(String.valueOf(greeting.getId()));
        ApiFuture<WriteResult> result = ref.set(greeting);

        System.out.println("Update time : " + result.get().getUpdateTime());
    }

    public void updateGreeting(Greeting greeting){
        System.out.println("Update Greeting");
        DocumentReference ref = retrieveDB().collection("Greeting").document(String.valueOf(greeting.getId()));
        ApiFuture<WriteResult> result = ref.set(greeting);

        try {
            System.out.println("Update time : " + result.get().getUpdateTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Greeting> retrieveGreetings(){
        Map<String, Greeting> greetings = new HashMap<>();
        ApiFuture<QuerySnapshot> query = retrieveDB().collection("Greeting").get();

        QuerySnapshot querySnapshot = null;
        try {
            querySnapshot = query.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for(QueryDocumentSnapshot document : documents){
            greetings.put(document.getId(), document.toObject(Greeting.class));
        }

        return greetings;
    }

    public Greeting retrieveById(String  id){
        DocumentReference ref = retrieveDB().collection("Greeting").document(id);
        ApiFuture<DocumentSnapshot> future = ref.get();
        try {
            DocumentSnapshot document = future.get();
            return document.toObject(Greeting.class);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void deleteGreeting(String greetingId){
        System.out.println("Delete Greeting");

        ApiFuture<WriteResult> result  = retrieveDB().collection("Greeting").document(greetingId).delete();
        try {
            System.out.println(result.get().getUpdateTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    public Firestore retrieveDB(){
        try{
            FirestoreOptions firestoreOptions =
                    FirestoreOptions.getDefaultInstance().toBuilder()
                            .setProjectId("sapient-symbol-261121")
                            .setCredentials(GoogleCredentials.getApplicationDefault())
                            .build();
            Firestore db = firestoreOptions.getService();

            return db;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
         return null;
    }
}
