# 🎯 Vertical SaaS Cash Machine (FeeFlow) - Project Overview

This document summarizes the core product, architecture, and implementation plan for FeeFlow, an Installment Control & Recovery System for competitive coaching institutes.

## 1. Product & Market Strategy

*   **Goal:** Build a "vertical SaaS cash machine" (side project, 8 hrs/week focus) aimed at faster first revenue, not VC-scale startup.
*   **Target Market:** Competitive coaching institutes in Tier-2 / Tier-3 cities, typically single-branch, with 300–1500 students (running NEET, JEE, UPSC programs).
*   **Positioning:**
    *   **You Are:** Installment Control & Recovery System. An operational partner improving business discipline.
    *   **Primary Angle:** Simplicity & Automation (better than Excel, no manual follow-up).
    *   **Core Focus:** Recovery Improvement / ROI (positioned as a recovery tool, not a cost center).
*   **Pricing Direction:** Flat pricing initially, targeted at **₹3,999 – ₹5,999/month**.
*   **Go-To-Market:** Focus on one Tier-2/3 city at a time with direct outreach, demo-led sales, and ROI conversation.

## 2. Product Scope (V1 - Desktop First)

**Core Modules:**

1.  **Institute & User Management:** Multi-tenant via `institute_id`.
2.  **Student & Batch Management:** Student profiles, batch assignment, fee info.
3.  **Installment Engine (Core Brain):** Plan generator, persisted installment records, payment history.
4.  **Reminder Automation Engine:** Configurable rules, Cron-based runner, Async WhatsApp sending, Reminder logs.
5.  **Financial Dashboard:** Total receivable, overdue, upcoming dues, monthly collection.
6.  **Broadcast System:** Batch-based messaging with tracking.
7.  **Audit Logs:** Tracking changes (Payments, Reminders, Broadcasts).

**Intentional Exclusions (V1 Discipline):** Attendance, LMS, Test Analytics, Student/Teacher apps, Multi-branch complexity, Payment Gateway Integration.

## 3. System & Database Architecture

*   **Architecture:** Modular Monolith with **Spring Boot 3** and **Java 21**.
*   **Database:** **PostgreSQL** (chosen for transactions, indexing, JSON support, SaaS standard).
*   **Messaging/Queue:** **Redis Queue** (for reminders) and **Worker process**. WhatsApp Cloud API via Meta for messaging.
*   **Backend Modules (Logical Bounded Contexts):**
    *   `feeflow-auth`
    *   `feeflow-students`
    *   `feeflow-fees` (Core Engine)
    *   `feeflow-payments` (Money Flow)
    *   `feeflow-reminders` (WhatsApp Automation Engine)
    *   `feeflow-broadcast`
    *   `feeflow-common`
    *   `feeflow-infrastructure` (External Integrations)

### Key Database Design Points

*   The schema is finalized to support complex scenarios: partial payments, discounts (at enrollment and midway), payment allocation, and receipts.
*   **Discount Rule:** We **never modify already paid installments**; we only adjust remaining ones (Scenario B).
*   **Reminder Reliability:** Two critical tables added for production robustness:
    *   `message_delivery_events`: Tracks delivery lifecycle (queued, sent, delivered) from the provider.
    *   `message_retry_queue`: Manages automatic retries for failed message sends.

## 4. Implementation Plan (Phase 1 Focus)

The recommended strategy is to prioritize the **Write Model** (system of record) and the three core services.

### Core Services (The Brain of FeeFlow)

1.  `InstallmentGenerationService`
2.  `PaymentAllocationService`
3.  `ReminderEngineService`

### Phase 1 MVP Focus

1.  **Phase 1 Core:** Students, Fee Structures, Installment Generation.
    *   **Core Modules:** `feeflow-students`, `feeflow-fees`, `feeflow-payments`.
2.  **Phase 2:** Payments and Allocation Engine.
3.  **Phase 3 (Later):** Reminder Engine and WhatsApp Integration.

**Initial MVP Endpoints:** POST/GET `/students`, POST `/fee-structures`, POST `/student-fees`, GET `/students/{id}/installments`, POST `/payments`.
