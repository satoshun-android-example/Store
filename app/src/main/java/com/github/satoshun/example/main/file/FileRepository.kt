package com.github.satoshun.example.main.file

import android.content.Context
import com.dropbox.android.external.fs3.FileSystemPersister
import com.dropbox.android.external.fs3.PathResolver
import com.dropbox.android.external.fs3.filesystem.FileSystem
import com.dropbox.android.external.fs3.filesystem.FileSystemFactory
import com.dropbox.android.external.store4.*
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import okio.Buffer
import java.util.concurrent.atomic.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.ExperimentalTime
import kotlin.time.seconds

@Singleton
@ExperimentalTime
class FileRepository @Inject constructor(
  @ApplicationContext private val context: Context
) {
  private val count = AtomicInteger(0)

  private val fileSystem: FileSystem = FileSystemFactory.create(context.cacheDir)
  private val pathResolver = object : PathResolver<Unit> {
    override fun resolve(key: Unit) = "file_test.json"
  }
  private val fileSystemPersister
    get() = FileSystemPersister.create(fileSystem, pathResolver)

  private val moshi = Moshi.Builder().build()
  private val adapter = moshi.adapter(Simple::class.java)

  private val store = StoreBuilder.from(
    fetcher = Fetcher.of {
      delay(2000)
      Simple(count = count.addAndGet(1), name = "$it")
    },
    sourceOfTruth = SourceOfTruth.of(
      nonFlowReader = {
        runCatching {
          val source = fileSystemPersister.read(Unit)
          source?.let { adapter.fromJson(it) }
        }.getOrNull()
      },
      writer = { key: Unit, input: Simple ->
        val buffer = Buffer()
        withContext(Dispatchers.IO) {
          adapter.toJson(buffer, input)
        }
        fileSystemPersister.write(key, buffer)
      },
      delete = {
        println("delete")
      },
      deleteAll = {
        println("deleteAll")
      }
    )
  )
    .cachePolicy(MemoryPolicy.builder<Any, Any>().setExpireAfterWrite(10.seconds).build())
    .build()

  suspend fun get(): Simple {
    return store.get(Unit)
  }
}

@JsonClass(generateAdapter = true)
data class Simple(
  val count: Int,
  val name: String
)
