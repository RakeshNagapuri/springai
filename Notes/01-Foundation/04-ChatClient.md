# ChatClient

## Definition

Main entry point for communicating with an LLM.

---

## Constructor Injection

```java
public ChatController(ChatClient.Builder builder) {
    this.chatClient = builder.build();
}
```

Spring Boot creates

ChatModel
↓
ChatClient.Builder
↓
Injected into Controller

---

## Request Flow

```java
chatClient
    .prompt(...)
    .call()
    .content();
```

### prompt()

Creates request.

No API call.

### call()

Actual HTTPS request.

### content()

Returns generated text.

---

## Design Patterns

- Builder Pattern
- Dependency Injection
- Fluent API
- Programming to Interface

---

