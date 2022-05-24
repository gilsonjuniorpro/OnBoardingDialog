package onboardingdialog.ca.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import onboardingdialog.ca.adapter.OnBoardingAdapter
import onboardingdialog.ca.databinding.FragmentOnBoardingBinding
import onboardingdialog.ca.viewmodel.OnBoardingViewModel


class OnBoardingFragment : Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var empowerAdapter: OnBoardingAdapter
    private val viewModel: OnBoardingViewModel by viewModels()
    lateinit var binding: FragmentOnBoardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            linearLayoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            initAdapter()

            viewModel.listContent()
    }

    private fun initAdapter() {
        empowerAdapter = OnBoardingAdapter()

        with(binding.feedRecycler) {
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
            adapter = empowerAdapter
            smoothScrollToPosition(0)

            val pager = PagerSnapHelper()
            pager.attachToRecyclerView(this)
        }

        viewModel.content.observe(viewLifecycleOwner) { list ->
            empowerAdapter.submitList(list)

            binding.indicatorView.apply {
                visibility = View.VISIBLE
                setRecyclerView(
                    binding.feedRecycler, linearLayoutManager, list.size,
                    linearLayoutManager.findLastVisibleItemPosition()
                )
            }
        }
    }
}
