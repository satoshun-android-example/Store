package com.github.satoshun.example.main.simple

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.dropbox.android.external.store4.*
import com.github.satoshun.example.R
import com.github.satoshun.example.databinding.SimpleFragBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

@ExperimentalCoroutinesApi
@FlowPreview
class SimpleFragment : Fragment(R.layout.simple_frag) {
  private lateinit var binding: SimpleFragBinding

  @OptIn(ExperimentalTime::class)
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding = SimpleFragBinding.bind(view)

    val store = StoreBuilder
      .from(
        fetcher = nonFlowValueFetcher<String, String> {
          println("called source $it")
          delay(500)
          println("finished source $it")
          "test"
        }
      )
      .cachePolicy(
        MemoryPolicy
          .builder()
          .setMemorySize(1024)
          .setExpireAfterWrite(10.toDuration(DurationUnit.MILLISECONDS))
          .build()
      )

    val key = "hoge"
    lifecycleScope.launch {
      store.build().stream(StoreRequest.cached(key, false)).collect {
        println("stream1 $it")
      }
    }

    lifecycleScope.launch {
      delay(1000)
      store.build().stream(StoreRequest.cached(key, false)).collect {
        println("stream2 $it")
      }
    }

    lifecycleScope.launch {
      delay(2000)
      val result = store.build().get(key)
      println("get $result")
    }
  }
}
