package com.bmk.data.network

import com.bmk.data.BuildConfig
import com.bmk.data.util.BASE_URL
import com.bmk.data.util.SHA
import okhttp3.CertificatePinner
import java.net.URL

object SSLCertificatePinnerImpl {
    fun getPinner(): CertificatePinner {
        if (BuildConfig.DEBUG) {
            return CertificatePinner.DEFAULT
        }
        return CertificatePinner.Builder()
            .add(URL(BASE_URL).host)
            .add(SHA)
            .build()
    }
}