🟢 Slice 1: The "Active Path" Alert Engine

Goal: Implement the core logic that checks if a new Ocorre with a user's active path.
Scope:
- Data: Utilize the existing Ocorrencia entity and GeoLoca
- Logic: Implement a service method that takes an active Path (sequence of coordinates) and checks if any recent Ocorrencia falls within the 100m
  forward-projecting 20m buffer.
- API Update: Create/Update an endpoint (e.g., GET /users/{id}/active-path-alerts) that returns a list of relevant alerts.
- Verification: A test case where an Ocorrencia is createdlly returned in the alert list.
  Definition of Done: The system can proactively identify upcoming barriers on a user's route.
  Blocked By: None

---

🟡 Slice 2: The "Reputation & Trust" Engine

Goal: Implement the logic that calculates and updates Usua accuracy of their reports.
Scope:
- Data: Use the existing Usuario entity and Votos (Votes)
- Logic: Implement a service that adjusts a user's reputation score when their Ocorrencia is upvoted (extends life/increases trust) or downvoted
  (triggers removal/decreases trust).
- API Update: Ensure UsuarioDTO includes the calculated trust_level (e.g., "Verified" vs "Unverified").
- Verification: A test where a user's reputation score chad.
  Definition of Done: User reliability directly impacts the visibility and trust of their reports.
  Blocked By: None

---

🟠 Slice 3: The "Dynamic Establishment" Rating

Goal: Implement the logic to aggregate Estabelecimento accecent user reviews.
Scope:
- Data: Use Avaliacao (Review) and Estabelecimento entitie
- Logic: Implement a service that recalculates the accessibility_rating of an Estabelecimento whenever a new Avaliacao is submitted, considering
  the timestamp to prioritize "fresh" data.
- API Update: GET /estabelecimentos/{id} returns the updated, real-time accessibility rating.
- Verification: Submitting a new Avaliacao changes the ratimento in the API response.
  Definition of Done: Establishments reflect their current accessibility state based on recent community feedback.
  Blocked By: None

---

🔴 Slice 4: The "Proactive Alert" Notification (Mobile/Web)

Goal: Bridge the backend engine to the user interface (Flue push/in-app notifications.
Scope:
- Logic: Integrate the Alert Engine (Slice 1) with a notifbase Cloud Messaging or WebSockets).
- App Update: Update map_view.dart in the Flutter app to display "Alert" markers dynamically when the backend detects a path intersection.
- Verification: Triggering an Ocorrencia via the API resulon the Flutter map without a manual refresh.
  Definition of An end-to-end demo of the "Real-Time" promise.
  Blocked By: Slice 1