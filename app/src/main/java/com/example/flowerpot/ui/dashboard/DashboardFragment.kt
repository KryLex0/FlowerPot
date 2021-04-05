package com.example.flowerpot.ui.dashboard

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.flowerpot.AppDatabase.Companion.get

import com.example.flowerpot.R
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.DataPointInterface
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_dashboard.*



class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val graph_temp:GraphView = root.findViewById(R.id.graph_temp)
        val graph_hum:GraphView = root.findViewById(R.id.graph_hum)
        val graph_lum:GraphView = root.findViewById(R.id.graph_lum)
        val spinner_type_graphe:Spinner = root.findViewById(R.id.spinner_type_graphe)

        //val allGraph = arrayOf(graph_temp, graph_hum, graph_lum)
        val allTemp = get(activity?.applicationContext as Application).donneeDao().getAllTemperature()
        val allHum = get(activity?.applicationContext as Application).donneeDao().getAllHumidite()
        val allLum = get(activity?.applicationContext as Application).donneeDao().getAllLuminosite()

        ////////////////////

        spinner_type_graphe.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                when (spinner_type_graphe.selectedItem.toString()){
                    "Température" -> {
                        graph_temp.visibility = View.VISIBLE
                        graph_hum.visibility = View.GONE
                        graph_lum.visibility = View.GONE

                        val seriesT = LineGraphSeries(arrayOf())
                        var i = 0
                        while(i < allTemp.size){
                            seriesT.appendData(DataPoint(i.toDouble(), allTemp[i]), false, 100)
                            i += 1
                        }
                        drawGraph(graph_temp, seriesT, allTemp.size)

                    }
                    "Humidité" -> {
                        graph_temp.visibility = View.GONE
                        graph_hum.visibility = View.VISIBLE
                        graph_lum.visibility = View.GONE

                        val seriesH = LineGraphSeries(arrayOf())
                        var i = 0
                        while(i < allHum.size){
                            seriesH.appendData(DataPoint(i.toDouble(), allHum[i]), false, 100)
                            i += 1
                        }
                        drawGraph(graph_hum, seriesH, allHum.size)
                    }
                    "Luminosité" -> {
                        graph_temp.visibility = View.GONE
                        graph_hum.visibility = View.GONE
                        graph_lum.visibility = View.VISIBLE

                        val seriesL = LineGraphSeries(arrayOf())
                        var i = 0
                        while(i < allLum.size){
                            seriesL.appendData(DataPoint(i.toDouble(), allLum[i].toDouble()), false, 100)
                            i += 1
                        }
                        drawGraph(graph_lum, seriesL, allLum.size)
                    }

                }
                //difficultyHighscore = spinner_difficulty_highscore.selectedItem.toString()
                //dataScorePlayer(difficultyHighscore)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }



//////////////////

        val lastTemp = get(activity?.applicationContext as Application).donneeDao().getLastDonnee()
        val last_temp:TextView = root.findViewById(R.id.last_temp)
        val last_hum:TextView = root.findViewById(R.id.last_hum)
        val last_lum:TextView = root.findViewById(R.id.last_lum)
        last_temp.text = "Température: ${lastTemp.temperature} °C"
        last_hum.text = "Humidité: ${lastTemp.humidite} %"
        last_lum.text = "Luminosité: ${lastTemp.luminosite} lx"
        
        return root


    }

    private fun drawGraph(graph: GraphView, seriesN: LineGraphSeries<DataPointInterface>, sizeGraph: Int
    ){
        graph.addSeries(seriesN)

        val maxId = get(activity?.applicationContext as Application).donneeDao().getMaxId()
        graph.viewport.setMaxX(maxId.toDouble())
        graph.viewport.isXAxisBoundsManual = true
        graph.gridLabelRenderer.numHorizontalLabels = 3

        graph.gridLabelRenderer.setHumanRounding(false)
    }
}