package com.bmk.baseproject.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.bmk.baseproject.R

fun Context.showNotification(title: String?, body: String?) {
    val notificationID = 0
    val channelId = "notify_001"
    val builder = NotificationCompat.Builder(this, channelId)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle(title)
        .setContentText(body)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            channelId,
            "Channel title",
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(channel)
        builder.setChannelId(channelId)
    }
    notificationManager.notify(notificationID, builder.build());
}

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun  Context.isOnline(): Boolean {

    val connectivityMgr = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

    val allNetworks: Array<Network> = connectivityMgr.allNetworks // added in API 21 (Lollipop)

    for (network in allNetworks) {
        val networkCapabilities = connectivityMgr.getNetworkCapabilities(network)
        return (networkCapabilities!!.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) &&
                (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                        || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)))
    }

return false
}

