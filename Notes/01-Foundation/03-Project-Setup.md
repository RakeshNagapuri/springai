# Project Setup

## Technology Stack

- Java 17
- Spring Boot 4.1
- Gradle
- Google Gemini
- Spring AI

---

## Dependencies

```gradle
implementation 'org.springframework.boot:spring-boot-starter-web'

implementation 'org.springframework.ai:spring-ai-starter-model-google-genai'
```

## Configuration

```properties
spring.ai.google.genai.api-key=

spring.ai.google.genai.chat.options.model=gemini-2.5-flash

spring.ai.google.genai.chat.options.temperature=0.7
```

---

## API Key

Authenticates the application.

---

## Model

Determines which LLM processes the prompt.

---

## Temperature

Controls creativity.

---

