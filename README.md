# Admin Site
> 패스트캠퍼스 강의 프로젝트

<br/>

### 목표
1. 사용자가 쇼핑물에서 아이템을 구매하거나, 관리자가 외부 업체를 관리하는 Admin Site
2. 1000명의 유저와 구매 내역이 5000개가 넘는 데이터를 다룸
3. 신규 주문, 신규 유저, 신규 파트너, 신규 상품을 볼 수 있는 대시보드를 제공
4. 고객 관리, 상품 관리, 파트너사 관리, 주문 관리, 카테고리 관리, Admin 사용자 관리 메뉴를 제공

<br/>

## Entity 및 Repository 설정
### Entity ( Table )
* AdminUser
* User
* Category
* Item
* OrderDetail
* OrderGroup
* Partner


### Repository
* AdminUserRepository
* UserRepository
* CategoryRepository
* ItemRepository
* OrderDetailRepository
* OrderGroupRepository
* PartnerRepository


### Entity Relationship
* User 1 : N OrderGroup
* OrderGroup 1 : N OrderDetail
* Item 1 : N OrderDetail
* Partner 1 : N Item
* Category 1 : N Partner


### 생성일, 생성한 대상, 수정일, 수정한 대상 자동화 하기
* JPA `@EnableJpaAuditing`
* 로그인된 User에 대해 `AuditorAware`를 구현한 컴포넌트를 사용


### Lombok를 이용한 생성자 패턴
* `@Builder`, `@Accessors`를 사용해 Entity를 생성, 수정


<br/>

## API

### Response Body 공통 속성

|    속성     |     타입      |      설명       |
| :---------: | :-----------: | :-------------: |
| transaction | LocalDateTime |  API 통신 시간  |
| resultCode  |    String     |  API 응답 코드  |
| description |    String     |  API 부가 설명  |
|    Data     |    Generic    | API 응답 데이터 |

