package redrocketz.project.com.appmvvm.mvvm2.common;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.math.BigDecimal;


/**
 * Response holder provided to the UI
 */
public class Response {

    public Status status;

    @Nullable
    public BigDecimal data;

    @Nullable
    public String result;

    @Nullable
    public Throwable error;

    private Response(Status status, @Nullable BigDecimal data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public Response(Status status, String result) {
        this.status = status;
        this.result = result;
    }

    public static Response loading() {
        return new Response(Status.LOADING, null, null);
    }

    public static Response success(@NonNull BigDecimal data) {
        return new Response(Status.SUCCESS, data, null);
    }

    public static Response success(@NonNull String data) {
        return new Response(Status.SUCCESS, data);
    }

    public static Response error(@NonNull Throwable error) {
        return new Response(Status.ERROR, null, error);
    }
}
