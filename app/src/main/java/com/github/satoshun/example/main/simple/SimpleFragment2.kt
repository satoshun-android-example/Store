package com.github.satoshun.example.main.simple

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.dropbox.android.external.store4.MemoryPolicy
import com.dropbox.android.external.store4.StoreBuilder
import com.dropbox.android.external.store4.StoreRequest
import com.dropbox.android.external.store4.get
import com.github.satoshun.example.R
import com.github.satoshun.example.databinding.SimpleFragBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
@FlowPreview
class SimpleFragment2 : Fragment(R.layout.simple_frag) {
  private lateinit var binding: SimpleFragBinding

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding = SimpleFragBinding.bind(view)

    var count = 0
    val store = StoreBuilder
      .fromNonFlow<String, String> {
        println("called source: ${count++} $it")
        delay(500)
        println("finished source $it")
        "test"
      }
      .cachePolicy(
        MemoryPolicy.builder()
          .setMemorySize(1024)
          .setExpireAfterTimeUnit(TimeUnit.MILLISECONDS)
          .setExpireAfterWrite(400)
          .build()
      )
      .build()

    val key = "hoge"
    lifecycleScope.launch {
      store.stream(StoreRequest.cached(key, false)).collect {
        println("stream1 $it")
      }
    }

    lifecycleScope.launch {
      store.stream(StoreRequest.cached(key, false)).collect {
        println("stream2 $it")
      }
    }

    lifecycleScope.launch {
      delay(300)
      store.stream(StoreRequest.cached(key, false)).collect {
        println("stream3 $it")
      }
    }

    lifecycleScope.launch {
      delay(1000)
      store.stream(StoreRequest.cached(key, false)).collect {
        println("stream4 $it")
      }
    }

    lifecycleScope.launch {
      delay(2000)
      val result = store.get(key)
      println("get $result")
    }
  }
}
