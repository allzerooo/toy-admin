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

## Front-end 개발자를 위한 API

### 사용자 API Request - create

| Path<br />Parameter Level 1 | Path<br />Parameter Level 2 |   타입   | 필수 여부 |           설명           |
| :-------------------------: | :-------------------------: | :------: | :-------: | :----------------------: |
|      transaction_time       |                             | datetime |     Y     |      통신 발생 시간      |
|            data             |                             |  object  |     Y     |      요청 Body 부분      |
|                             |           account           |  string  |           |       사용자 계정        |
|                             |          password           |  string  |           |         비밀번호         |
|                             |           status            |  string  |           | REGISTERED / UNREGISTERD |
|                             |            email            |  string  |           |          이메일          |
|                             |        phone_number         |  string  |           |   - 가 포함된 전화번호   |
|                             |        registered_at        | datetime |           |   없는 경우 "" or null   |
|                             |       unregistered_at       | datetime |           |   없는 경우 "" or null   |

### 사용자 API Response - create

| JSON<br />Parameter Level 1 | JSON<br />Parameter Level 2 |   타입   | 필수 여부 |                    설명                     |
| :-------------------------: | :-------------------------: | :------: | :-------: | :-----------------------------------------: |
|      transaction_time       |                             | datetime |     Y     |               통신 발생 시간                |
|         result_code         |                             |  string  |     Y     | 정상 통신시 "OK"<br />비정상 통신시 "ERROR" |
|         description         |                             |  string  |     N     |                                             |
|            data             |                             |  object  |     Y     |               요청 Body 부분                |
|                             |             id              |   long   |           |                  DB index                   |
|                             |           account           |  string  |           |                 사용자 계정                 |
|                             |          password           |  string  |           |                  비밀번호                   |
|                             |           status            |  string  |           |          REGISTERED / UNREGISTERD           |
|                             |            email            |  string  |           |                   이메일                    |
|                             |        phone_number         |  string  |           |            - 가 포함된 전화번호             |
|                             |        registered_at        | datetime |           |            없는 경우 "" or null             |
|                             |       unregistered_at       | datetime |           |            없는 경우 "" or null             |

