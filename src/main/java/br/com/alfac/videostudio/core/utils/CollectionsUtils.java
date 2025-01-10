package br.com.alfac.videostudio.core.utils;

import java.util.Collection;
import java.util.Objects;

public final class CollectionsUtils {

    private CollectionsUtils() {
    }

    public static boolean vazio(Collection collection){

        return Objects.isNull(collection) || collection.isEmpty();

    }

    public static boolean naoVazio(Collection collection){

        return !vazio(collection);

    }
}