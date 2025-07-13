package com.sample.app.here.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// https://gist.githubusercontent.com/russellbstephens/9e528b12fd1a45a7ff4e4691adcddf10/raw/5ec8ce76460e8f29c9b0f99f3bf3450b06696482/people.json
object ServiceDriver {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://gist.githubusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service: ParseService = retrofit.create(ParseService::class.java)
}