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

		binding.allDataTextView.text = "${AssetsMapper.instance.map}"

		binding.getId3Button.setOnClickListener {
			val targetId = 3
			
			val data = AssetsMapper.instance.map[targetId]
			val contains = AssetsMapper.instance.map.containsKey(targetId)
			Log.d("button onClick", "contains = $contains   data = $data")

			binding.id3ResultTextView.text = "contains = $contains\n$data"
			
			try {
				AssetsMapper.instance.map.forEach {
					Log.d("button onClick", "key = ${it.key}, value = ${it.value}") //Crashes on this line only if you installed the APK manually
				}
			} catch (e: Exception){
				Log.e("button onClick", "error", e)
			}
		}

	}
}