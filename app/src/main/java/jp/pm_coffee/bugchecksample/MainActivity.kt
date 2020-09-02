package jp.pm_coffee.bugchecksample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import jp.pm_coffee.bugchecksample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

	private val binding: ActivityMainBinding by lazy {
		return@lazy DataBindingUtil.bind<ActivityMainBinding>(findViewById<ViewGroup>(android.R.id.content)[0])!!
	}
	
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding.allDataOriginalTextView.text = "${AssetsMapper.instance.originalMap}"
		binding.allDataCastedTextView.text = "${AssetsMapper.instance.castedMap}"



		binding.getId3Button.setOnClickListener {
			val targetId = 3
			
			val originalMapData = AssetsMapper.instance.originalMap[targetId]
			val originalMapDataContains = AssetsMapper.instance.originalMap.contains(targetId)// <----- This result is odd.
			Log.d("button onClick", "originalMapDataContains = $originalMapDataContains   originalMapData = $originalMapData")

			binding.id3OriginalResultTextView.text = "contains = $originalMapDataContains\n$originalMapData"


			val castedMapData = AssetsMapper.instance.castedMap[targetId]
			val castedMapDataContains = AssetsMapper.instance.castedMap.contains(targetId)
			Log.d("button onClick", "castedMapDataContains = $castedMapDataContains   castedMapData = $castedMapData")

			binding.id3CastedResultTextView.text = "contains = $castedMapDataContains\n$castedMapData"
			
			
		}

	}
}