package hr.tvz.android_menza

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class DetailFragment : Fragment() {
    private lateinit var nazivMenze: TextView
    private lateinit var adresaMenze: TextView
    private lateinit var meniMenze: TextView
    private lateinit var radnoVrijemeMenze: TextView
    private lateinit var infoMenze: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_detail, container, false)
        nazivMenze  =view.findViewById<View>(R.id.nazivMenzeDetail)as TextView
        adresaMenze =view.findViewById<View>(R.id.adresaMenzeDetail  )as TextView
        radnoVrijemeMenze =view.findViewById<View>(R.id.radnoVrijemeDetail)as TextView
        infoMenze =view.findViewById<View>(R.id.infoMenzeDetail)as TextView
        meniMenze =view.findViewById<View>(R.id.meniMenzeDetail)as TextView


        val bundle = arguments
        val naziv = bundle!!.getString("naziv")
        val adresa = bundle!!.getString("adresa")
        val info = bundle!!.getString("info")
        val radnoVrijeme = bundle!!.getString("radnoVrijeme")
        val meni = bundle!!.getString("meni")


        nazivMenze.text = naziv
        adresaMenze.text = adresa
        radnoVrijemeMenze.text = radnoVrijeme
        infoMenze.text = info
        meniMenze.text = meni


        return view
    }


}