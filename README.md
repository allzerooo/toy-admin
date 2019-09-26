# Admin Site
### 목표
1. 사용자가 쇼핑물에서 아이템을 구매하거나, 관리자가 외부 업체를 관리하는 Admin Site
2. 1000명의 유저와 구매 내역이 5000개가 넘는 데이터를 다룸
3. 신규 주문, 신규 유저, 신규 파트너, 신규 상품을 볼 수 있는 대시보드를 제공
4. 고객 관리, 상품 관리, 파트너사 관리, 주문 관리, 카테고리 관리, Admin 사용자 관리 메뉴를 제공

<br/>

### Entity ( Table )
* AdminUser
* User
* Category
* Item
* OrderDetail
* OrderGroup
* Partner

<br/>

### Repository
* AdminUserRepository
* UserRepository
* CategoryRepository
* ItemRepository
* OrderDetailRepository
* OrderGroupRepository
* PartnerRepository

<br/>

### JPA Entity Relationship
* User 1 : N OrderGroup
* OrderGroup 1 : N OrderDetail
* Item 1 : N OrderDetail
* Partner 1 : N Item
* Category 1 : N Partner

<br/>

### `createdAt`, `createdBy`, `updatedAt`, `updatedBy` 자동화 하기
* JPA `@EnableJpaAuditing`
* 로그인된 User에 대해 `AuditorAware`를 구현한 컴포넌트를 사용

<br/>

### Lombok를 이용한 생성자 패턴
* `@Builder`, `@Accessors`를 사용해 Entity를 생성, 수정