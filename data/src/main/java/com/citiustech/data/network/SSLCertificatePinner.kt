package com.citiustech.data.network

import okhttp3.CertificatePinner

interface SSLCertificatePinner {
    fun getPinner():CertificatePinner
}