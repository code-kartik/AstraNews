package com.example.astranews.dataclasses;

import com.squareup.moshi.Json;

public class JsonClass {
    @Json(name="title") public String newsTitle;
    @Json(name="summary") public String newsSummary;
    @Json(name="published_at") public String publishDate;
    @Json(name="image_url") public String imageUrl;
    @Json(name="url") public String articleUrl;
}
