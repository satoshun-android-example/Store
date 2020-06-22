package com.github.satoshun.example.main.simple

import com.dropbox.android.external.store4.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.ExperimentalTime
import kotlin.time.seconds

@ExperimentalTime
@ExperimentalCoroutinesApi
@FlowPreview
@Singleton
class SimpleRepository @Inject constructor() {
  private val store =
    StoreBuilder
      .from<String, Simple>(
        fetcher = nonFlowValueFetcher<String, Simple> { key ->
          delay(2000)
          Simple(id = key, name = "$key $key")
        }
      )
      .cachePolicy(
        MemoryPolicy.builder()
          .setExpireAfterWrite(60.seconds)
          .build()
      )
      .build()

  suspend fun get(id: String): Simple {
    return store.get(id)
  }

  suspend fun fresh(id: String): Simple {
    return store.fresh(id)
  }
}

data class Simple(
  val id: String,
  val name: String
)
