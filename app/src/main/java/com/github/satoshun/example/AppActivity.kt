package com.github.satoshun.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnLayout
import androidx.core.view.doOnNextLayout
import androidx.core.view.doOnPreDraw
import androidx.lifecycle.lifecycleScope
import com.github.satoshun.example.databinding.AppActBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AppActivity : AppCompatActivity() {
  private lateinit var binding: AppActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = AppActBinding.inflate(layoutInflater)
    setContentView(binding.root)
    setSupportActionBar(binding.toolbar)

    test(1)

    lifecycleScope.launch {
      delay(500)
      test(2)
      test2(2)
    }

    // call
//    lifecycleScope.launch {
//      delay(1000)
//      binding.root.requestLayout()
//    }

    // call
//    lifecycleScope.launch {
//      delay(1000)
//      binding.toolbar.requestLayout()
//    }

    // not call test2 doOnNextLayout
    lifecycleScope.launch {
      delay(1000)
      binding.navHostFragment.requestLayout()
    }
  }

  private fun test(from: Int) {
    binding.root.doOnLayout {
      println("doOnLayout$from")
    }
    binding.root.doOnPreDraw {
      println("doOnPreDraw$from")
    }
    binding.root.doOnNextLayout {
      println("doOnNextLayout$from")
    }
  }

  private fun test2(from: Int) {
    binding.toolbar.doOnLayout {
      println("toolbar doOnLayout$from")
    }
    binding.toolbar.doOnPreDraw {
      println("toolbar doOnPreDraw$from")
    }
    binding.toolbar.doOnNextLayout {
      println("toolbar doOnNextLayout$from")
    }
  }
}
