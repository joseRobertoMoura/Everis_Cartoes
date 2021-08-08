package com.example.everis_cartoes.ui.cartoes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.everis_cartoes.R
import com.example.everis_cartoes.data.model.CartoesModel
import com.example.everis_cartoes.ui.home.HomeActivity
import com.example.everis_cartoes.ui.login.LoginActionView
import com.example.everis_cartoes.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartoesFragment : Fragment() {

    private lateinit var rvCartoes:RecyclerView
    private val viewModel by viewModel<CartoesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cartoes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as Context

        initViewModel(view, activity)
    }

    private fun initViewModel(view: View, activity: Context) {
        viewModel.cartoesActionView.observe(viewLifecycleOwner,{state ->
            when(state){
                is CartoesActionView.SuccessCall -> {
                    if (!state.listCartoes.isNullOrEmpty()){
                        setAdapter(view,activity,state.listCartoes)
                    }
                }

                is CartoesActionView.ErrorCall -> {
                    Toast.makeText(activity,state.error, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    companion object {
        fun newInstance() = CartoesFragment()
    }

    private fun setAdapter(view: View,activity: Context, list: List<CartoesModel>){
        rvCartoes = view.findViewById(R.id.rv_cartoes_list)
        rvCartoes.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        rvCartoes.adapter = CartoesAdapter(list)
    }
}