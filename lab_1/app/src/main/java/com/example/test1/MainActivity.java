package com.example.test1;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = "46572886-5d3434d96676538417847a04d"; //
    private static final String BASE_URL = "https://pixabay.com/";

    private EditText queryEditText;
    private Button searchButton;
    private ImageView imageView;
    private ProgressBar progressBar;
    private Button likeButton, dislikeButton;

    private ImageApi imageApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queryEditText = findViewById(R.id.queryEditText);
        searchButton = findViewById(R.id.searchButton);
        imageView = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progressBar);
        likeButton = findViewById(R.id.likeButton);
        dislikeButton = findViewById(R.id.dislikeButton);

        // Инициализация Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        imageApi = retrofit.create(ImageApi.class);

        // Устанавливаем слушатель для кнопки поиска
        searchButton.setOnClickListener(v -> {
            String query = queryEditText.getText().toString().trim();
            if (!query.isEmpty()) {
                searchImages(query);
            } else {
                Toast.makeText(MainActivity.this, "Введите запрос для поиска", Toast.LENGTH_SHORT).show();
            }
        });

        // События для кнопок "Лайк" и "Дизлайк"
        likeButton.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Вы поставили лайк!", Toast.LENGTH_SHORT).show();
        });

        dislikeButton.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Вы поставили дизлайк!", Toast.LENGTH_SHORT).show();
        });
    }

    private void searchImages(String query) {
        progressBar.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.GONE);

        Call<ImageResponse> call = imageApi.searchImages(API_KEY, query, "photo");
        call.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                progressBar.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);

                if (response.isSuccessful() && response.body() != null) {
                    List<ImageResult> images = response.body().getHits();
                    if (!images.isEmpty()) {
                        String imageUrl = images.get(0).getLargeImageURL();
                        Picasso.get().load(imageUrl).into(imageView);
                    } else {
                        Toast.makeText(MainActivity.this, "Изображения не найдены", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Ошибка загрузки данных", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Ошибка соединения", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
