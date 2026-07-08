# Prompt Engineering

## Prompt

Input sent to the model.

---

## System Prompt

Defines

- Behaviour
- Personality
- Rules

Example

```java
.system("You are a Java Trainer.")
```

---

## User Prompt

Actual user question.

```java
.user(question)
```

---

## Combined

```java
chatClient
    .prompt()
    .system(...)
    .user(...)
    .call()
    .content();
```

---

## Why separate them?

Instead of

One Big String

Spring AI creates

SYSTEM

↓

USER

This keeps the context structured.

---

