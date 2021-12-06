package uz.jsoft.iprofrag

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.jsoft.iprofrag.databinding.FragmentBonusBinding


class BonusFragment : Fragment() {

    /*   private var _binding: FragmentBonusBinding? = null
       private var binding = _binding ?: throw NullPointerException("null binding")
   */
    private var _binding: FragmentBonusBinding? = null
    private val binding: FragmentBonusBinding
        get() = _binding ?: throw NullPointerException("View wasn't created")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBonusBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
//            tvSimple.text = "${arguments?.getInt("count")} ${arguments?.getString("name")}"
            val fireDate = arguments?.getString("dateOfBurning")

            val time = fireDate?.replace("-", ".")
            val resultDate = time?.split("T")?.get(0)

            bonusText.text = "${arguments?.getInt("currentQuantity")} бонусов"
            bonusNeed.text = "${arguments?.getInt("burningQuantity")} бонусов"
            bonusDate.text = "$resultDate сгорит"
        }


    }


    companion object {
        fun newInstance(
            currentQuantity: Int,
            burningQuantity: Int,
            dateOfBurning: String
        ): BonusFragment {
            val bundle = Bundle()
            bundle.putInt("currentQuantity", currentQuantity)
            bundle.putInt("burningQuantity", burningQuantity)
            bundle.putString("dateOfBurning", dateOfBurning)
            val frag = BonusFragment()
            frag.arguments = bundle
            return frag
        }
    }


}