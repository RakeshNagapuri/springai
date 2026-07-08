# File: 01-Foundation/07-Common-Mistakes.md

# Common Mistakes

This document lists common mistakes made while learning Spring AI.

---

# 1. Calling the API without configuring the API Key

Problem

```properties
spring.ai.google.genai.api-key=
```

Result

Authentication Failure

Solution

Configure a valid API Key.

---

# 2. Thinking prompt() makes the API call

Incorrect

```java
chatClient.prompt("Explain Java");
```

Reality

No network request happens.

Only

```java
.call();
```

actually contacts Gemini.

---

# 3. Hardcoding prompts

Bad

```java
chatClient.prompt("Explain Java");
```

Better

```java
chatClient.prompt(question);
```

Use dynamic prompts whenever possible.

---

# 4. Mixing System Prompt and User Prompt

Bad

```
You are a Java Trainer.
Explain Java Streams.
```

Better

```java
.system("You are a Java Trainer.")
.user(question)
```

Keep responsibilities separate.

---

# 5. Creating ChatClient manually

Incorrect

```java
new ChatClient();
```

Correct

```java
ChatClient.Builder
```

managed by Spring.

---

# 6. Forgetting Constructor Injection

Prefer

```java
public ChatController(ChatClient.Builder builder)
```

instead of field injection.

---

# 7. Assuming ChatClient talks directly to Gemini

Actual flow

Controller

↓

ChatClient

↓

ChatModel

↓

Gemini

---

# Lessons Learned

- Let Spring create framework objects.
- Keep prompts dynamic.
- Separate System and User prompts.
- Remember that call() performs the API request.