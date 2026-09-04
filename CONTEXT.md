# Navable: Project Context

## Mission
Navable is an urban accessibility platform designed to enhance social inclusion by providing real-time, location-aware accessibility information.

## Core Concepts

### User Personas
* **Accessibility-Focused Users:** Individuals with varying mobility or sensory needs who rely on accurate, real-time accessibility data for navigation.
* **General Users:** Individuals who may use the app to aid others or to understand the accessibility of locations.

### Core Entities
* **User:** An authenticated individual with a profile and accessibility preferences.
* **Establishment:** A physical location (e.g., a shop, park, or station) with known accessibility features.
* **Observation (Observation/Report):** A user-contributed report regarding the accessibility of a location or route.
* **Route:** A planned path from point A to point B, evaluated for accessibility.

## System Architecture

### Data Models

#### User Profile
* **Preferences:** Specific accessibility needs (e.g., wheelchair access, sensory-friendly).
* **Authentication:** Credentials for accessing personalized data and contributing reports.

#### Establishment
* **Identity:** Name, address, and category.
* **Accessibility Features:** A collection of known features (e.
* **User-Reported Status:** The current accessibility state based on recent observations.

#### Observation (The "Report")
* **Type:** Categorization (e.g., barrier, feature, alert).
* **Location:** GPS coordinates.
* **Timestamp:** When the observation was made.
* **Evidence:** (Optional) Photos or descriptive text.
* **Status:** Pending, Verified, or Resolved.

### Functional Pillars

#### 1. Discovery & Navigation (The "Map")
* **Real-time Accessibility Mapping:** Visualizing the accessibility of streets and establishments.
* **Accessible Route Planning:** Calculating routes that accommodate specific user preferences.
* **Dynamic Alerting:** Providing real-time alerts for new accessibility barriers or features.

#### 2. Contribution & Community (The "Social")
* **User-Generated Observations:** Enabling users to easily report accessibility changes.
* **Verification Workflow:** A process for verifying the accuracy of user-contributed data.
* **Community Engagement:** Promoting a culture of accessibility awareness and contribution.

## Technical Constraints & Requirements

### Connectivity & Performance
* **Real-time Capabilities:** The system must handle real-time updates for navigation and alerts.
* **Offline Accessibility:** Basic map and accessibility data should be available even with intermittent connectivity.
* **Low Latency:** The system must provide rapid responses for route calculations and alert delivery.

### Data Accuracy & Integrity
* **Verifiability:** Mechanisms must be in place to ensure the reliability of user-contributed data.
* **Data Freshness:** The system must prioritize the most recent and verified observations.

### Security & Privacy
* **User Privacy:** Protecting user location data and accessibility needs.
* **Data Integrity:** Ensuring that observations and establishment data cannot be tamly manipulated.
