package com.kate.lesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kate.lesson.databinding.FragmentTodayBinding
import com.kate.lesson.model.Rank
import com.kate.lesson.model.RankTime

/**
 * Created by Kate on 2022/9/12.
 */
class TodayFragment : Fragment() {

  private val viewModel: RankViewModel by viewModels()

  private var _binding: FragmentTodayBinding? = null

  private val binding get() = _binding!!

  private val onClickListener: (Rank) -> Unit = {
    val action = TodayFragmentDirections.actionTodayFragmentToProfileFragment(it)
    binding.root.findNavController().navigate(action)
  }

  private val adapter = RankAdapter(onClickListener)

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = FragmentTodayBinding.inflate(inflater, container, false)
    val view = binding.root
    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.recycler.layoutManager = LinearLayoutManager(context)
    binding.recycler.adapter = adapter

    viewModel.ranks.observe(viewLifecycleOwner) {
      adapter.submitList(it)
    }
    viewModel.getRank(RankTime.TODAY)

  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}