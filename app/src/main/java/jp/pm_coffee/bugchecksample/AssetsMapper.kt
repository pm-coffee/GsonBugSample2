package jp.pm_coffee.bugchecksample

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader

import android.util.Log
import java.io.Closeable
import java.util.concurrent.ConcurrentHashMap



class AssetsMapper private constructor() {

	companion object {
		val instance: AssetsMapper by lazy { AssetsMapper() }
	}

	val map = ConcurrentHashMap<Int, Data1>()

	init {
		loadAssets()
	}

	private fun loadAssets(){
		if(map.isEmpty()) {
			try {
				(JsonReader(MyApp.instance.assets.open("data.json").reader()) as Closeable)
					.use {
						val type = object : TypeToken<ConcurrentHashMap<Int, Data1>>() {}.type
						map.putAll(Gson().fromJson<Map<Int, Data1>>(it as JsonReader, type))
					}
			} catch (e: Exception) {
				Log.e("loadAssets()", "error", e)
			}
		}
	}

}

