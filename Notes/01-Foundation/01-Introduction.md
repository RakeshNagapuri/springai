# Introduction

## What is Spring AI?

Spring AI is a Spring framework that provides a unified abstraction for interacting with Large Language Models (LLMs).

Supported Providers

- Google Gemini
- OpenAI
- Anthropic
- Ollama
- Azure OpenAI

---

## Why Spring AI?

Without Spring AI

Application
↓
HTTP Client
↓
JSON
↓
LLM

With Spring AI

Application
↓
ChatClient
↓
Spring AI
↓
LLM

Spring AI hides

- HTTP Communication
- Authentication
- Provider APIs
- JSON Serialization

---

## Key Takeaways

- One API for multiple providers.
- Spring Boot auto-configuration.
- Provider independent programming.

---

## My Understanding

(To be written by me)