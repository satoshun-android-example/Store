package com.github.satoshun.example.main.simple

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.dropbox.android.external.store4.*
import com.github.satoshun.example.R
import com.github.satoshun.example.databinding.SimpleFragBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

// error handling
@AndroidEntryPoint
@ExperimentalTime
class SimpleFragment4 : Fragment(R.layout.simple_frag) {
  private lateinit var binding: SimpleFragBinding

  @Inject
  lateinit var repository: SimpleRepository

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding = SimpleFragBinding.bind(view)

    var isError = true
    val storeBuilder = StoreBuilder
      .from(
        fetcher = Fetcher.of { key: String ->
          println("call fetcher")
          delay(1000)
          if (isError) {
            isError = false
            throw IOException("error")
          }
          "success"
        }
      )
      .cachePolicy(
        MemoryPolicy
          .builder()
          .setMemorySize(1024)
          .setExpireAfterWrite(10.toDuration(DurationUnit.SECONDS))
          .build()
      )

    val store = storeBuilder.build()

    val key = "hoge"
    lifecycleScope.launch {
      store.stream(StoreRequest.cached(key, false)).collect {
        println("stream1 $it")
      }
    }

    lifecycleScope.launch {
      delay(3000)
      println("data1 ${store.get(key)}")
      println("data2 ${store.get(key)}")
    }

    binding.button.setOnClickListener {
      lifecycleScope.launch {
        println("button ${store.get(key)}")
      }
    }
  }
}
