package com.citiustech.data.network

import com.citiustech.data.BuildConfig
import okhttp3.CertificatePinner

class SSLCertificatePinnerImpl: SSLCertificatePinner {

    private val HOST_NAME="jsonplaceholder.typicode.com"

    override fun getPinner(): CertificatePinner {
        if (BuildConfig.DEBUG) {
            return CertificatePinner.DEFAULT
        }
        return CertificatePinner.Builder()
            .add(HOST_NAME)
            .add("sha256/AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=")
            .build()
    }
}