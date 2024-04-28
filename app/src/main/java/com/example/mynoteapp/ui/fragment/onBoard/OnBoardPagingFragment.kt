package com.example.mynoteapp.ui.fragment.onBoard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mynoteapp.R
import com.example.mynoteapp.databinding.FragmentOnBoardPagingBinding


class OnBoardPagingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardPagingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardPagingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() = with(binding) {
            when (requireArguments().getInt(ARG_ONBOARD_POSITION)) {
                0 -> {
                    lottie.setAnimation(R.raw.lottie1)
                    tvOn.text = "Очень удобный функционал"
                }
                1 -> {
                    tvOn.text = "Быстрый, качественный продукт"
                    lottie.setAnimation(R.raw.lottie3)
                }
                2 -> {
                    lottie.setAnimation(R.raw.lottie2)
                    tvOn.text = "Куча функций и интересных фишек"
                }
            }
        }


    companion object {
        const val ARG_ONBOARD_POSITION = "onBoard"

    }
}