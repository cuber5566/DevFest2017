package com.cuber.devfest.data.source.api.intercepter;

import android.support.annotation.NonNull;

import com.atplus.api.NetworkConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    private static final String AUTHORIZATION = "Ocp-Apim-Subscription-Key";
    private static final String TOKEN_PREFIX = "";
    private static final String TOKEN1 = "ee1f4c957b914ef9a81a816e82c26cb0";
    private static final String TOKEN2 = "07498ca49e3d4d03bdf1a8ef8aca7e40";
    private static final String TOKEN3 = "001b50aefb4643a691add5629440cecd";

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request originalRequest = chain.request();

        if (chain.request().url().toString().contains("public-restricted")) {
            return chain.proceed(originalRequest);
        }

        if (chain.request().url().toString().contains(NetworkConfig.BASE_URL)) {

            int random = (int) (System.currentTimeMillis() % 3);
            return chain.proceed(originalRequest.newBuilder()
                    .header(AUTHORIZATION, TOKEN_PREFIX + (random == 0 ? TOKEN1 : random == 2 ? TOKEN2 : TOKEN3))
                    .build());
        }

        return chain.proceed(originalRequest);
    }
}
