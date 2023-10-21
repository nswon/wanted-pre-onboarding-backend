### ResponseEntity
ResponseEntity를 사용해서 정해진 HTTP Response 규약을 지키면서 빠르게 구현하려고 노력했습니다.
```java
//Example
@GetMapping
    public ResponseEntity<JobPostingsResponse> getPostings() {
        JobPostingsResponse jobPostingsResponse = jobPostingService.getPostings();
        return ResponseEntity.ok(jobPostingsResponse);
    }
```

<br>

### Record
Record를 사용하여 가독성을 신경썼습니다.
```java
//Example
public record JobPostingUpdateRequest(
    String position,
    int compensation,
    String content,
    String technology
) {
}
```

<br>

### 사용자
```GET http://localhost:8081/api/user``` 테스트를 위해 사용자를 제공합니다.

DB에 유저가 존재하지 않는다면, 새로 생성하여 저장한 후 가져옵니다.
```java
default User get() {
        List<User> users = findAll();

        if(users.isEmpty()) {
            User user = User.createUser();
            save(user);
            return user;
        }

        return users.get(0);
    }
```

<br>

```GET http://localhost:8081/api/user/application-history``` 사용자가 지원한 채용공고 목록을 가져옵니다.

List를 객체로 감싸주면서 변화에 대한 유연성을 높였습니다.
```java
public ApplicationHistoriesResponse getApplicationHistories() {
        User user = userRepository.get();
        return user.getApplicationHistories().stream()
            .map(ApplicationHistoryResponse::new)
            .collect(collectingAndThen(toList(), ApplicationHistoriesResponse::new));
    }
```


### 회사
```POST http://localhost:8081/api/company``` 테스트를 위해 회사를 생성할 수 있습니다.
```json
{
    "name": "원티드랩",
    "nation": "한국",
    "area": "서울"
}
```

<br>

### 채용공고
```POST http://localhost:8081/api/job-posting``` 채용공고를 등록합니다.
```json
{
    "companyId": 1,
    "position": "개발",
    "compensation": 1000000,
    "content": "테스트",
    "technology": "Java"
}
```

회사와 채용공고 관계를 다대일 양방향으로 설계했습니다.
```java
public void createPosting(JobPostingCreateRequest request) {
        Company company = companyRepository.getById(request.companyId());
        JobPosting jobPosting = request.toEntity(company);
        company.createJobPosting(jobPosting);

        jobPostingRepository.save(jobPosting);
    }
```

```java
//JobPosting Entity
public class Company {
    ...
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private final List<JobPosting> jobPostings = new ArrayList<>();
    ...
}
```

```java
//Company Entity
public class JobPosting {
    ...
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
    ...
}
```

<br>

```GET http://localhost:8081/api/job-posting``` 채용공고 목록을 가져옵니다.

List를 객체로 감싸주면서 변화에 대한 유연성을 높였습니다.
```java
public JobPostingsResponse getPostings() {
        return jobPostingRepository.findAll().stream()
            .map(JobPostingResponse::new)
            .collect(collectingAndThen(toList(), JobPostingsResponse::new));
    }
```

<br>

```GET http://localhost:8081/api/job-posting/${id}/applicants``` 해당 채용공고에 지원한 유저들의 정보를 반환합니다.

지원내역에서 유저들의 id를 가져옵니다.
```java
public List<Long> getApplicants() {
        return applicationHistories.stream()
            .map(ApplicationHistory::getUserId)
            .toList();
    }
```

<br>

### 지원
```GET http://localhost:8081/api/application``` 사용자는 채용공고에 지원합니다.

이미 채용공고에 등록한 사용자가 또 등록할 시 에러를 반환합니다.
```java
//JobPosting Entity
public void receiveApplication(ApplicationHistory application) {
    if(isAlreadyApplied(application)) {
    throw new AlreadyAppliedException();
    }

    applicationHistories.add(application);
    }

private boolean isAlreadyApplied(ApplicationHistory target) {
    return applicationHistories.stream().anyMatch(application -> application.getId().equals(target.getId()));
    }
```