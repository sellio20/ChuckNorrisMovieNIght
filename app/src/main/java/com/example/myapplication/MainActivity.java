package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        giveMeSomeMovies();
        giveMeAJoke();
    }

    private void giveMeSomeMovies() {
        final String[] chuckMovies = {"The Delta Force", "Lone Wolf McQuade", "Code of Silence",
                "Delta Force 2: The Colombian Connection", "Invasion USA", "Missing in Action",
                "Firewalker", "Missing in action 2: The Beginning", "Good Guys Wear Black",
                "The Cutter", "Braddock: Missing in Action III", "The Way of the Dragon",
                "An Eye for an Eye", "A Force of One", "The Octagon", "Hero and the Terror",
                "Forced Vengeance", "The President's Man", "Breaker! Breaker!", "Forest Warrior"};
        final double[] chuckMoviesRating = {5.6, 6.4, 6.1, 4.8, 5.4, 5.4, 5.0, 5.3, 5.1, 5.2, 4.8,
                7.3, 5.5, 5.1, 5.1, 5.2, 5.6, 4.9, 4.1, 3.5};

        final TextView MovieOne = (TextView) findViewById(R.id.Movie1);
        final TextView MovieTwo = (TextView) findViewById(R.id.Movie2);
        final TextView MovieThree = (TextView) findViewById(R.id.Movie3);
        final TextView MovieOneR = (TextView) findViewById(R.id.movieOne_Rating);
        final TextView MovieTwoR = (TextView) findViewById(R.id.movieTwo_Rating);
        final TextView MovieThreeR = (TextView) findViewById(R.id.movieThree_Rating);
        Button MakeMovies = (Button) findViewById(R.id.makeMovies);

        MakeMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double ran1 = Math.random();
                int random1 = (int) (ran1 * chuckMovies.length);
                double ran2 = Math.random();
                int random2 = (int) (ran2 * chuckMovies.length);
                double ran3 = Math.random();
                int random3 = (int) (ran3 * chuckMovies.length);
                MovieOne.setText(chuckMovies[random1]);
                MovieTwo.setText(chuckMovies[random2]);
                MovieThree.setText(chuckMovies[random3]);
                MovieOneR.setText(Double.toString(chuckMoviesRating[random1]));
                MovieTwoR.setText(Double.toString(chuckMoviesRating[random2]));
                MovieThreeR.setText(Double.toString(chuckMoviesRating[random3]));
            }
        });
    }

    private void giveMeAJoke() {
            HttpResponse<JsonNode> response = Unirest.get("https://matchilling-chuck-norris-jokes-v1.p.rapidapi.com/jokes/random")
                .header("X-RapidAPI-Host", "matchilling-chuck-norris-jokes-v1.p.rapidapi.com")
                .header("X-RapidAPI-Key", "2cfab13061msh32875b190d6bcbep1a88a5jsn98f7ff6281e5")
                .header("accept", "application/json").asJson();

        final String joke = response.getHeaders().get(4).toString();

        final TextView Joke = (TextView) findViewById(R.id.joketext);
        Button MakeJoke = (Button) findViewById(R.id.jokebutton);

        MakeJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Joke.setText(joke);
            }
        });
    }
}
