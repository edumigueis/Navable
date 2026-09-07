# Navable: Real-Time Accessibility Specification

## Problem Statement

Urban navigation is significantly harder for individuals with mobility or sensory needs because standard maps are static. Critical accessibility information—such as a broken elevator in a subway station or temporary construction blocking a sidewalk—is transient. By the time this information is manually updated in a database, the user has already encountered the barrier, leading to frustration, wasted time, and loss of mobility.

## Solution

Navable provides a real and dynamic "accessibility layer" on top of standard navigation. It leverages community-driven, real-time "Observations" (incident reports) that are subject to a continuous lifecycle of upvoting and downvoting. By using a forward-projecting buffer on active routes, the system proactively alerts users to upcoming barriers, ensuring they can reroute before they encounter an obstacle.

## User Stories

1. As an accessibility-focused user, I want to see real-time barriers on my map, so that I can avoid inaccessible routes.
2. As an accessibility-focused user, I want to receive an alert when a new barrier is detected on my path.
3. As a user, I want to see the reliability of a review based on the reviewer's reputation.
4. As a user, I want to see the trust level of an establishment based on its historical accessibility ratings.
5. As a user, I want to see a clear distinction between a permanent facility and a temporary obstruction.
6. As a user, I want to see the current status of a path (clear vs. obstructed) at a glance.
7. As a user, I want to be able to rely on the accuracy of the information provided by trusted community members.
8. As a user, I want to navigate through a city with confidence, knowing that the accessibility data is up-to-date.

## Implementation Details

### Domain Models
- **User**: Represents a person using the app, with a reputation/trust score.
- **Establishment**: A physical location (e.g., cafe, station) with an accessibility rating.
- **Observation (Incident)**: A temporal event (e.g., sidewalk construction) at a specific coordinate.
- **Path**: A sequence of coordinates representing a route.

### Core Logic
- **Alert Engine**: Monitors the intersection of active `Paths` and new `Observations`.
- **Reputation Engine**: Updates `User` trust scores based on the accuracy of their reported observations.
- **Rating Engine**: Aggregates `Establishment` accessibility ratings based on user reviews and historical data.

### Technical Constraints
- **Spatial Querying**: Efficiently finding intersections between paths and points in space.
- **Concurrency**: Handling high-frequency updates to observations and path data.
- **Latency**: Ensuring alerts are delivered in near real-time as new observations are ingested.
