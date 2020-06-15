package com.github.satoshun.example.main.simple

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.github.satoshun.example.R
import com.github.satoshun.example.databinding.SimpleFragBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject
import kotlin.time.ExperimentalTime

@AndroidEntryPoint
@ExperimentalCoroutinesApi
@FlowPreview
class SimpleFragment4 : Fragment(R.layout.simple_frag) {
  private lateinit var binding: SimpleFragBinding

  @Inject lateinit var repository: SimpleRepository

  @OptIn(ExperimentalTime::class)
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding = SimpleFragBinding.bind(view)
  }
}
