# Architecture

Browser
↓
Spring MVC Controller
↓
ChatClient
↓
ChatModel
↓
Google Gemini
↓
Response

---

## Responsibilities

### Controller

- Receives HTTP requests.

### ChatClient

- Main API for AI communication.

### ChatModel

- Talks to Gemini.

### Gemini

- Generates responses.

---

## Key Takeaways

Controller never directly communicates with Gemini.

ChatClient hides the implementation.

---

