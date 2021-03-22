package com.dev.clevertonsantos.mybeats.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.dev.clevertonsantos.mybeats.R
import com.dev.clevertonsantos.mybeats.extensions.load
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailsImage.load(args.image)
        detailsDescription.text = args.name
        detailsConectionValue.text = args.connection
        detailsCompatibilityValue.text = args.compatibility
        detailsChargeValue.text = args.charge
        detailsAutonomyValue.text = args.autonomy
        detailsHeightValue.text = args.height
        detailsCaptureValue.text = args.capture
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }

}