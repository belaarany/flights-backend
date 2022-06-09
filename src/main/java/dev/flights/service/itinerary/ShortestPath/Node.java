package dev.flights.service.itinerary.ShortestPath;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Node {
    @NonNull
    private String name;
    private Integer minWeight = null;
    private Integer maxWeight = null;
}
