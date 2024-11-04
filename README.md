## Pre Market Project

> 🎫 Pre Market 프로젝트는 공연 및 스포츠 경기 관람 티켓 등의 예약 판매 서비스와 일반 상품 판매 기능을 제공하는 이커머스 플랫폼입니다.

---

## 🌱 사용된 기술

Java, Spring, JPA, QueryDSL, MySQL, Redis, Spring Security, Docker, Jenkins, AWS EC2, RDS, ElasticCache, Spring Rest
Docs

---

## 🌱 시스템 아키텍처

<img src="https://i.ibb.co/ZYV61xK/premarket-architecture.jpg" alt="premarket-architecture" border="0">

---

## 🌱 ERD

<img src="https://i.ibb.co/mtNQpPL/premarket-erd.png" alt="premarket-erd" border="0">

---

### 🌱 구현 및 문제 해결

- 구현
    - Layered Architecture & DDD 패턴 적용
    - Spring Rest Docs를 사용하여 API 명세를 위한 Test Code 의무화
    - JUnit5, Mockito를 사용한 유닛, 통합 테스트 작성
    - Jenkins, Docker를 활용한 CI/CD 구축


- 트러블슈팅
    - Redis + Kafka를 활용하여 예약 주문 TPS를 향상
    - Spring Security + JWT를 활용한 인증/인가 처리

---

### 🌱 트러블슈팅 접근 과정

- [Layered Architecture with DDD](https://syeon2.github.io/devlog/pre-market-architecture.html)
- [상품 주문 시 재고 차감 동시성 이슈 트러블슈팅](https://syeon2.github.io/devlog/premarket-concurrency.html)
    - [Java Synchronized Deep Dive](https://medium.com/@gsy4568/java-synchronized-deep-dive-9a764568d27c)
    - [Pessimistic Lock Deep Dive (feat.MySQL)](https://medium.com/@gsy4568/pessimistic-locking-deep-dive-feat-mysql-7fcf90f259f0)
    - [예약 상품 주문 요청 속도 개선 사례를 소개합니다.](https://syeon2.github.io/devlog/premarket-order-speed.html)
- [Spring Security 동작 프로세스](https://syeon2.github.io/devlog/pre-market-security.html)


