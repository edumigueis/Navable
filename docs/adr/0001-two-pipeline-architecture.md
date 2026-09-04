# ADR 0001: Two-Pipeline Architecture for Accessibility Data Delivery

## Status
Proposed

## Context
Navable requires two fundamentally different modes of data delivery:
1. **Discovery Mode**: Users browse the map globally, exploring businesses and accessibility features at their own pace. This requires high-density, rich information (names, descriptions, full metadata).
2. **Navigation Mode**: Users move through the environment actively. This requires low-latency, high-reliability, profile-aware alerts that trigger auditory and visual warnings without distracting the user or requiring heavy network interactions.

A single, unified data pipeline would struggle to balance the high-latency/high-payload needs of discovery with the low-latency/low-payload needs of navigation.

## Decision
We will implement two separate architectural pipelines for data delivery:

### 1. The Discovery Pipeline (Global/Unfiltered)
* **Focus**: Information density and exploration.
* **Mechanism**: Uses **Cluster-based aggregation** on the server side to handle high density.
* **Payload**: **Full Payload**. Includes all metadata, text descriptions, and timestamps.
* **Responsibility**: The server performs spatial queries and applies user-selected filters (e.g., "Show only wheelchair accessible").

### 2. The Navigation Pipeline (Personalized/Profile-Aware)
* **Focus**: Low-latency, high-reliability alerting.
* **Mechanism**: **Route-at-Start**. The client uploads the entire planned path (polyline) at the start of a trip.
* **Payload**: **Minimal Payload**. Contains only the critical alert triggers: `alert_id`, `type`, `location`, and `importance_score`.
* **Responsibility**: The server performs path-intersection (pre-trip) and proximity-based (during-trip) analysis.
* **Deviation Handling**: If the user deviates >60m from the planned path, the client re-uploads the new route to trigger a re-calculation.

## Consequences

### Positive
* **User Safety**: "Pre-trip" warnings are possible because the server knows the intended path.
* **Performance**: The Navigation pipeline is highly resilient to network fluctuations because the payload is minimal and the critical data is delivered "instantly."
* **Scalability**: The Discovery pipeline can use heavy clustering/aggregation without impacting the real-time performance of the Navigation engine.
* **Reliability**: The "Single-Payload" approach for alerts eliminates the "second-request" latency that could lead to missed warnings in low-connectivity areas.

### Negative
* **Architectural Complexity**: Maintaining two distinct pipelines increases the complexity of the backend codebase and testing requirements.
* **Data Redundancy**: The server must manage two different views of the same underlying "Observation" data.
* **Client Burden**: The client must handle both "Cluster Decoding" for discovery and "Path-Tracking/Deviation Detection" for navigation.

## Alternatives Considered
* **Unified Single Pipeline**: Rejected because the "Full Payload" required for discovery would introduce unacceptable latency and "alert fatigue" during active navigation, and would prevent the "pre-trip" warning feature.
* **Client-side Filtering Only**: Rejected because the volume of global accessibility data is too high for a mobile device to process without server-side clustering and filtering.
