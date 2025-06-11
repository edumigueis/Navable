package com.unicamp.navable_api.persistance.support;

public interface GeoLocationSupport {
    // Constants for commonly used values
    double DEFAULT_SEARCH_RADIUS_KM = 1.0;
    
    // Haversine formula for Ocorrencia entity
    String OCORRENCIA_HAVERSINE_DISTANCE = """
        (6371 * acos(
            cos(radians(:latitude)) * cos(radians(o.latitude)) *
            cos(radians(o.longitude) - radians(:longitude)) +
            sin(radians(:latitude)) * sin(radians(o.latitude))
        )) <= :distance
    """;
    
    // Haversine formula for Estabelecimento entity
    String ESTABELECIMENTO_HAVERSINE_DISTANCE = """
        (6371 * acos(
            cos(radians(:latitude)) * cos(radians(e.latitude)) *
            cos(radians(e.longitude) - radians(:longitude)) +
            sin(radians(:latitude)) * sin(radians(e.latitude))
        )) <= :distance
    """;
}