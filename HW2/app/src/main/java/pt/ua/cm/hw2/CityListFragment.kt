package pt.ua.cm.hw2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import pt.ua.cm.hw2.databinding.FragmentCityListBinding

class CityListFragment : Fragment() {

    private val cityViewModel: CityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentCityListBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCityListBinding.bind(view)
        val slidingPaneLayout = binding.slidingPaneLayout
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            CityListOnBackPressedCallBack(slidingPaneLayout)
        )

        // Initialize the adapter and set it to the RecyclerView.
        val adapter = CityAdapter {
            // Update the user selected sport as the current sport in the shared view-model
            // This will automatically update the dual pane content
            cityViewModel.updateCurrentCity(it)

            binding.slidingPaneLayout.openPane()
        }
        binding.recyclerView.adapter = adapter
        adapter.submitList(cityViewModel.cityData)
    }
}

class CityListOnBackPressedCallBack(private val slidingPaneLayout: SlidingPaneLayout)
    : OnBackPressedCallback(slidingPaneLayout.isSlideable && slidingPaneLayout.isOpen),
    SlidingPaneLayout.PanelSlideListener {
    override fun handleOnBackPressed() {
        slidingPaneLayout.closePane()
    }

    override fun onPanelSlide(panel: View, slideOffset: Float) { }

    override fun onPanelOpened(panel: View) {
        isEnabled = true
    }

    override fun onPanelClosed(panel: View) {
        isEnabled = false
    }

    init {
        slidingPaneLayout.addPanelSlideListener(this)
    }


}