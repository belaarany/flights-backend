package dev.flights.service.itinerary.ShortestPath;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Builder.Default;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class Node {
    @NonNull
    private String name;

    @Default
    private Double minWeight = null;
    
    @Default
    private Double maxWeight = null;
}
