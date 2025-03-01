package com.example.mealplanner.network;

import android.util.Log;
import android.widget.Toast;

import com.example.mealplanner.model.recipes.Recipe;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class FirestoreDataSource {
    private FirebaseFirestore db;

    public FirestoreDataSource() {
        db = FirebaseFirestore.getInstance();
    }

    public Completable saveFavoriteRecipe(String userId, String recipeId, Recipe recipe) {
        return Completable.create(emitter ->
                db.collection("users")
                        .document(userId)
                        .collection("favorites")
                        .document(recipeId)
                        .set(recipe)
                        .addOnSuccessListener(success -> Log.i("TAG", "Recipe saved successfully: "))
                        .addOnFailureListener(error -> Log.e("TAG", "Failed to save!: "))
        );
    }

    public Single<Recipe> getFavoriteRecipe(String userId, String recipeId) {
        return Single.create(emitter ->
                db.collection("users")
                        .document(userId)
                        .collection("favorites")
                        .document(recipeId)
                        .get()
                        .addOnSuccessListener(documentSnapshot -> {
                            if (documentSnapshot.exists()) {
                                Recipe recipe = documentSnapshot.toObject(Recipe.class);
                                if (recipe != null) {
                                    emitter.onSuccess(recipe);
                                }
                            }
                        })
                        .addOnFailureListener(emitter::onError)
        );
    }
}
