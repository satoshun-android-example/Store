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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

@ExperimentalCoroutinesApi
@FlowPreview
class SimpleFragment3 : Fragment(R.layout.simple_frag) {
  private lateinit var binding: SimpleFragBinding

  @OptIn(ExperimentalTime::class)
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding = SimpleFragBinding.bind(view)

    var currentItem: List<String>? = null
    val sourceOfTruth = SourceOfTruth.fromNonFlow<Unit, List<String>, List<String>>(
      reader = { currentItem },
      writer = { _, input ->
        currentItem = input
      },
      delete = {
        currentItem = null
      },
      deleteAll = {
        currentItem = null
      }
    )

    var count = 0
    val store = StoreBuilder
      .from(
        fetcher = nonFlowValueFetcher<Unit, List<String>> {
          println("called source: ${count++} $it")
          delay(500)
          println("finished source: $it")
          listOf("test")
        },
        sourceOfTruth = sourceOfTruth
      )
      .cachePolicy(
        MemoryPolicy
          .builder()
          .setMemorySize(1024)
          .setExpireAfterWrite(10.toDuration(DurationUnit.MILLISECONDS))
          .build()
      )
      .build()

    val key = Unit
    lifecycleScope.launch {
      store.stream(StoreRequest.cached(key, false)).collect {
        println("stream1 $it")
      }
    }

    lifecycleScope.launch {
      delay(2000)
      // no call again
      val result = store.get(key)
//      val result = store.fresh(key)
      println("get1 $result")
    }

    lifecycleScope.launch {
      delay(3000)
      val value = sourceOfTruth.reader(key).first()!!
      sourceOfTruth.write(key, value + "added")
    }

    lifecycleScope.launch {
      delay(4000)
      // no call again
      val result = store.get(key)
//      val result = store.fresh(key)
      println("get2 $result")
    }
  }
}
