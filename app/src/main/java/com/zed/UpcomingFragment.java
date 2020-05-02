package com.zed;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcomingFragment extends Fragment implements CustomAdapter.OnDataItemClickListener {

    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    String apiKey = "e16f9ec421f01f05db45a6d069d84d56";
    List<tmdbNowPlaying.Result> result;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_upcoming, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        JsonApi service = RetrofitService.createService().create(JsonApi.class);
        Call<tmdbNowPlaying> call = service.getUpcoming(apiKey, 1);
        call.enqueue(new Callback<tmdbNowPlaying>() {
            @Override
            public void onResponse(@NotNull Call<tmdbNowPlaying> call, @NotNull Response<tmdbNowPlaying> response) {
                Log.e("upcomingFragment", "response:200");
                tmdbNowPlaying tt = response.body();
                generateDataList(tt.getResults(),view);
            }

            @Override
            public void onFailure(@NotNull Call<tmdbNowPlaying> call, @NotNull Throwable t) {

            }
        });
    }

    private void generateDataList(List<tmdbNowPlaying.Result> results, View v) {
        result = results;
        recyclerView = v.findViewById(R.id.upcoming_recyclerView);
        adapter = new CustomAdapter(this.getContext(),results, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(int position) {
        //start an intent to the details activity here
        Log.e("here", "this is true");
        tmdbNowPlaying.Result curRes = result.get(position);
        Intent intent = new Intent(getActivity(), MovieDetailsActivity.class);
        intent.putExtra("title", curRes.getTitle() );
        intent.putExtra("overview", curRes.getOverview());
        intent.putExtra("year",curRes.getReleaseDate() );
        intent.putExtra("rating",curRes.getVoteAverage().toString() );
        intent.putExtra("adult", curRes.getAdult());
        intent.putExtra("poster_url", "https://image.tmdb.org/t/p/original"+ curRes.getPosterPath());
        startActivity(intent);
    }
}
