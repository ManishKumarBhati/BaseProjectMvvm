package com.citiustech.data.network

import com.citiustech.data.BuildConfig
import com.citiustech.data.util.BASE_URL
import com.citiustech.data.util.SHA
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