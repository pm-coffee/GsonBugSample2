package jp.pm_coffee.bugchecksample

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader

import android.util.Log
import java.io.Closeable
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.concurrent.ConcurrentHashMap



class AssetsMapper private constructor() {

	companion object {
		val instance: AssetsMapper by lazy { AssetsMapper() }
	}

	val originalMap = ConcurrentHashMap<Int, Data1>()
	val castedMap : Map<Int, Data1>

	init {
		loadAssets()
		castedMap = originalMap
	}

	private fun loadAssets(){
		if(originalMap.isEmpty()) {
			try {
				(JsonReader(MyApp.instance.assets.open("data.json").reader(StandardCharsets.UTF_8)) as Closeable)
					.use {
						val rootElement = JsonParser.parseReader(it as JsonReader)
						val listType = object : TypeToken<ConcurrentHashMap<Int, Data1>>() {}.type

						originalMap.putAll(Gson().fromJson<Map<Int, Data1>>(rootElement, listType))
					}


			} catch (e: Exception) {
				Log.e("loadAssets()", "error", e)
			}
		}
	}

}

