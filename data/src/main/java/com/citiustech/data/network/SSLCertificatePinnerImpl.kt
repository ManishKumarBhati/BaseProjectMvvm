package com.citiustech.data.network

import com.citiustech.data.Base_Url
import com.citiustech.data.BuildConfig
import okhttp3.CertificatePinner
import java.net.URL

object SSLCertificatePinnerImpl {
    fun getPinner(): CertificatePinner {
        if (BuildConfig.DEBUG) {
            return CertificatePinner.DEFAULT
        }
        return CertificatePinner.Builder()
            .add(URL(Base_Url).host)
            .add("sha256/AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=")
            .build()
    }
}