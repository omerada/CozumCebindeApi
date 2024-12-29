package com.omerada.cozumcebinde.core.utils;

import java.util.Collection;

public class GeneralUtils {
    public static <T> boolean valueNullOrEmpty(final T value) {
        return value == null ||
                (value instanceof String && ((String) ((String) value).trim()).isEmpty()) ||
                (value instanceof Collection && ((Collection<?>) value).isEmpty()) ||
                (value instanceof byte[] && ((byte[]) value).length == 0);
    }

    public static  String convertYoutubeLink(String url){
        if (url == null || url.isEmpty())
            return null;
        if (url.contains("embed")){
            return url;
        }
        //
        https://youtu.be/zP-bcTvTlDk?si=NBBKv0aY57qsdxNz

        if (url.contains("si")){
            String [] arr = url.split("\\?");
            String str = arr[0];
            String [] arr2 = str.split("/");
            String videoId = arr2[3];
            return "https://www.youtube.com/embed/"+videoId;
        }

        String [] arr = url.split("=");
        String videoId = arr[1];
        return "https://www.youtube.com/embed/"+videoId;
    }

}
