package com.example.flowerpot.ui.options

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.flowerpot.R
import kotlinx.android.synthetic.main.fragment_options.*


class OptionsFragment : Fragment() {

    private lateinit var optionsViewModel: OptionsViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        optionsViewModel =
                ViewModelProvider(this).get(OptionsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_options, container, false)
        /*
        val textView: TextView = root.findViewById(R.id.text_options)
        optionsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
         */
        val switch_arrosage:Switch = root.findViewById(R.id.switch_arrosage)
        switch_arrosage.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                switch_arrosage.text = "Automatique"
            }else{
                switch_arrosage.text = "Manuel"
            }
        }


        createNotificationChannel()

        val btn_notif: Button = root.findViewById(R.id.btn_notif)

        btn_notif.setOnClickListener {
            val notificationManager = requireActivity().getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
            val notification: Notification = Notification.Builder(requireContext())
                    .setContentTitle("FlowerPot Notification")
                    .setContentText("N'oubliez pas d'arroser votre plante!")
                    .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                    .setChannelId("my_channel_id")
                    .build()

            notificationManager.notify(100, notification)
        }

        return root
    }


    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notifications"
            val descriptionText = "Notifier utilisateur"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("NotifFlowerPot", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                    requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}