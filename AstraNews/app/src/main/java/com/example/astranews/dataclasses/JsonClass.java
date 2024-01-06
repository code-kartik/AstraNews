package com.example.astranews.dataclasses;

import com.squareup.moshi.Json;

public class JsonClass {
    @Json(name="title") String title;
    @Json(name="summary") String summary;
    @Json(name="published_at") String publishDate;
    @Json(name="image_url") String imageUrl;
    @Json(name="url") String articleUrl;
}
