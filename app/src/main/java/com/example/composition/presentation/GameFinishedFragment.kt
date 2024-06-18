package com.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.composition.R
import com.example.composition.databinding.FragmentGameFinishedBinding
import com.example.composition.domain.entity.GameResult


class GameFinishedFragment : Fragment() {

    private val args by navArgs<GameFinishedFragmentArgs>()

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setResources()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    private fun setResources() {
        val percent =
            (args.gameResult.countOfRightAnswers / args.gameResult.countOfQuestions.toDouble() * 100).toInt()
        binding.tvRequiredAnswers.text = requireActivity().getString(
            R.string.required_score,
            args.gameResult.gameSettings.minCountOfRightAnswers.toString()
        )
        binding.tvScoreAnswers.text = requireActivity().getString(
            R.string.score_answers,
            args.gameResult.countOfRightAnswers.toString()
        )
        binding.tvRequiredPercentage.text = requireActivity().getString(
            R.string.required_percentage,
            args.gameResult.gameSettings.minPercentOfRightAnswers.toString()
        )
        binding.tvScorePercentage.text = requireActivity().getString(
            R.string.score_percentage,
            percent.toString()
        )
        if (args.gameResult.winner) {
            binding.emojiResult.setImageResource(R.drawable.ic_smile)
        } else {
            binding.emojiResult.setImageResource(R.drawable.ic_sad)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun retryGame() {
        findNavController().popBackStack()
    }

}