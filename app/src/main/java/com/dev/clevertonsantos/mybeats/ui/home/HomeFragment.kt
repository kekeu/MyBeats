package com.dev.clevertonsantos.mybeats.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.clevertonsantos.mybeats.R
import com.dev.clevertonsantos.mybeats.data.model.Headphone
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private val homeAdapter = HomeAdapter(::onItemClicked)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(homeRecyclerHeadphones) {
            layoutManager = LinearLayoutManager(activity,
                    RecyclerView.VERTICAL,false)
            setHasFixedSize(true)
            adapter = homeAdapter
        }

        homeButtonAdd.setOnClickListener {
            viewModel.getHeadphones()
        }
        viewModel.headphonesLiveData.observe(viewLifecycleOwner, {
            it?.let { headphones ->
                homeAdapter.addItens(headphones)
            }
        })
        viewModel.viewFlipperLiveData.observe(viewLifecycleOwner, {
            it?.let { viewFlipper ->
                homeViewFlipper.displayedChild = viewFlipper.first
                viewFlipper.second?.let { errorMessage ->
                    homeTextViewError.text = errorMessage
                }
            }
        })
        viewModel.getHeadphones()
    }

    private fun onItemClicked(headphone: Headphone) {
        val valuesDirections = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                height = headphone.height, name = headphone.name, autonomy = headphone.autonomy,
                capture = headphone.capture, charge = headphone.charge, image = headphone.image,
                connection = headphone.connection, compatibility = headphone.compatibility)
        findNavController().navigate(valuesDirections)
    }
}