package pe.pcs.desafioarquitectura.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val originalUrl = request.url

        val newUrl = originalUrl.newBuilder()
            .addQueryParameter("api_key", "83adb31856dbba8cf444a45ed3efa82a")
            .build()

        val newRequest = request.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}