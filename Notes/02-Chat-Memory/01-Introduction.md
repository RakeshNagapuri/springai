# Chat Memory - Introduction

## Learning Objectives

By the end of this chapter, I should understand:

- Why Chat Memory is required
- Why LLMs are stateless
- Difference between Memory, Knowledge, and Live Data
- How Spring AI manages conversations
- What ChatMemory is responsible for
- Why Spring AI stores `Message` objects instead of plain strings

---

# Why Chat Memory?

Large Language Models (LLMs) are **stateless**.

Every API request is independent.

Example:

```
Request 1

User:
Who invented Java?

↓

Gemini

↓

James Gosling
```

Later,

```
Request 2

User:
Where was he born?
```

Without sending the previous conversation again, Gemini does **not** know who "he" refers to.

This is because every request starts as a new conversation.

---

# How Chat Applications Remember

The LLM itself does **not** remember previous conversations.

Instead, the application stores previous messages and sends them again with every new request.

Conceptually:

```
User Question
      │
      ▼
Load Previous Messages
      │
      ▼
Build Complete Prompt
      │
      ▼
Send to Gemini
```

The memory belongs to the **application**, not the LLM.

---

# What is Context?

Context is all the information sent to the LLM for generating a response.

```
Context
│
├── Memory
├── Knowledge
└── Live Data
```

---

# Memory

## Definition

Information related to the user or the ongoing conversation.

Examples

- Chat History
- User Preferences
- Previous Questions
- Conversation Summary
- User Name

Storage

- Redis
- PostgreSQL
- JDBC
- InMemory

Managed By

Spring AI `ChatMemory`

---

# Knowledge

## Definition

Facts or business information required to answer the question.

Examples

- Company Documents
- PDFs
- Books
- Product Manuals
- Internal Wiki

Knowledge may come from:

- LLM's pre-trained knowledge
- RAG (Retrieval-Augmented Generation)

Storage

- Vector Database

Examples

- PGVector
- Chroma
- Pinecone
- Milvus

Managed By

Spring AI `VectorStore`

---

# Live Data

## Definition

Information that changes frequently and must be fetched in real time.

Examples

- Weather
- Bank Balance
- Order Status
- Flight Status
- Inventory

Retrieved From

- REST APIs
- Databases
- External Services
- Microservices

Managed By

Spring AI Tool Calling

---

# Difference Between Memory, Knowledge and Live Data

| Type | Purpose | Source | Spring AI Component |
|------|----------|--------|---------------------|
| Memory | Remember the user | Chat History | ChatMemory |
| Knowledge | Understand facts | Vector Database / LLM | VectorStore (RAG) |
| Live Data | Get current information | APIs / Database | Tool Calling |

---

# AI Application Flow

```
User Question
       │
       ▼
Retrieve Memory
       │
Retrieve Knowledge (RAG)
       │
Retrieve Live Data (Tools)
       │
Build Final Prompt
       │
ChatClient
       │
ChatModel
       │
Gemini
       │
AI Response
```

---

# Spring AI Architecture

```
User Question
        │
        ▼
Spring Boot Controller
        │
        ▼
Spring AI
        │
        ├── ChatMemory
        ├── VectorStore
        ├── Tool Calling
        │
        ▼
Prompt Construction
        │
        ▼
ChatClient
        │
        ▼
ChatModel
        │
        ▼
Google Gemini
        │
        ▼
AI Response
```

Spring AI acts as an **orchestrator**, collecting information from different sources before invoking the LLM.

---

# What is ChatMemory?

`ChatMemory` is responsible for managing conversation history.

Responsibilities:

- Load previous messages
- Add current user message
- Add assistant response
- Store updated conversation
- Provide conversation history for future requests

It is a **memory manager**, not just a memory retriever.

---

# Why ChatMemory is an Interface?

Spring AI follows the principle of **Programming to Interfaces**.

Conceptually:

```
ChatMemory
      ▲
      │
 ┌────┴──────────────┐
 │                   │
 ▼                   ▼

InMemoryChatMemory   JdbcChatMemory
                     RedisChatMemory
```

Different implementations provide different storage mechanisms.

---

# Why Store Message Objects Instead of Strings?

Incorrect

```java
List<String> messages;
```

Problem

```
Hello

Hi

Explain Spring Boot

Spring Boot is...
```

The application cannot identify:

- Who sent the message?
- Was it the user?
- Was it the assistant?
- Was it the system?

Correct

```java
List<Message>
```

Example

```
SystemMessage

↓

UserMessage

↓

AssistantMessage

↓

UserMessage
```

Each message contains metadata in addition to the text.

---

# Conversation ID

Multiple users may interact with the application simultaneously.

Each conversation must remain isolated.

Conceptually:

```
Conversation ID
        │
        ▼
ChatMemory
        │
        ▼
List<Message>
```

Every conversation maintains its own message history.

---

# Internal Working of ChatMemory

```
Conversation ID
        │
        ▼
Load Previous Messages
        │
        ▼
Add User Message
        │
        ▼
Build Prompt
        │
        ▼
Send to LLM
        │
        ▼
Receive AI Response
        │
        ▼
Store Assistant Message
```

---

# Key Takeaways

- LLMs are stateless.
- Chat Memory is maintained by the application.
- Context consists of Memory, Knowledge, and Live Data.
- Spring AI orchestrates all context before calling the LLM.
- `ChatMemory` manages conversations.
- Messages are stored as objects, not plain strings.
- Conversation IDs isolate user conversations.
- Spring AI follows interface-based design for memory implementations.

---

# Interview Questions

### 1. Why do LLMs need Chat Memory?

### 2. What is the difference between Memory and Knowledge?

### 3. What is the difference between Knowledge and Live Data?

### 4. Why does Spring AI use `ChatMemory`?

### 5. Why are `Message` objects preferred over `String` objects?

### 6. Why is `ChatMemory` an interface?

### 7. What is the purpose of a Conversation ID?

