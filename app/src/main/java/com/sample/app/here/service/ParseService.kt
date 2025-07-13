package com.sample.app.here.service

import com.sample.app.here.model.Data
import retrofit2.Call
import retrofit2.http.GET

//https://gist.githubusercontent.com/russellbstephens/9e528b12fd1a45a7ff4e4691adcddf10/raw/5ec8ce76460e8f29c9b0f99f3bf3450b06696482/people.json
interface ParseService {
    @GET("russellbstephens/9e528b12fd1a45a7ff4e4691adcddf10/raw/5ec8ce76460e8f29c9b0f99f3bf3450b06696482/people.json")
    fun getPeople(): Call<Data>

}