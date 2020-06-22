package com.github.satoshun.example.main.file

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.github.satoshun.example.R
import com.github.satoshun.example.databinding.SimpleFragBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.ExperimentalTime

@AndroidEntryPoint
@ExperimentalCoroutinesApi
@FlowPreview
@ExperimentalTime
class FileFragment : Fragment(R.layout.simple_frag) {
  private lateinit var binding: SimpleFragBinding

  @Inject
  lateinit var repository: FileRepository

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding = SimpleFragBinding.bind(view)

    lifecycleScope.launch {
      val data = repository.get()
      println(data)
    }
    lifecycleScope.launch {
      val data = repository.get()
      println(data)
    }

    binding.button.setOnClickListener {
      lifecycleScope.launch {
        val data = repository.get()
        println(data)
      }
    }
  }
}
