package com.zed;

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

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NowPlayingFragment extends Fragment {

    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    String apiKey = "e16f9ec421f01f05db45a6d069d84d56";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now_playing, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        JsonApi service = RetrofitService.createService().create(JsonApi.class);
        Call<tmdbNowPlaying> call = service.getNowPlaying(apiKey, 1);
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
        recyclerView = v.findViewById(R.id.nowplaying_recyclerView);
        adapter = new CustomAdapter(this.getContext(),results);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}
