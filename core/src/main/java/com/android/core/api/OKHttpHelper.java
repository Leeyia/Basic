package com.android.core.api;

import com.android.core.MainApp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author: 蜡笔小新
 * @date: 2016-07-05 17:23
 * @GitHub: https://github.com/meikoz
 */
public class OKHttpHelper {

    private static SSLSocketFactory socketFactory;

    public static OkHttpClient create() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        File cacheFile = new File(MainApp.getInstance().getCacheDir(), "OkHttpCache");
        Cache cache = new Cache(cacheFile,  10 * 1024 * 1024); //100Mb

        OkHttpClient okHttpClient = null;
        try {
            okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(8000, TimeUnit.MILLISECONDS)
                    .connectTimeout(8000, TimeUnit.MILLISECONDS)
                    .addInterceptor(interceptor)
                    .addNetworkInterceptor(new HttpCacheInterceptor())
                    .cache(cache)
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    .sslSocketFactory(setCertificates(MainApp.getInstance().getAssets().open("https.cer")))
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return okHttpClient;
    }

    public static SSLSocketFactory setCertificates(InputStream... certificates) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates) {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));
                try {
                    if (certificate != null)
                        certificate.close();
                } catch (IOException e) {
                }
            }
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(null,
                    trustManagerFactory.getTrustManagers(),
                    new SecureRandom()
            );
            socketFactory = sslContext.getSocketFactory();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return socketFactory;
    }
}
