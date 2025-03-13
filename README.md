# 지섭이의 동물병원

## 팀 소개
| ![js](https://avatars.githubusercontent.com/u/140247789?v=4)| ![wb](https://avatars.githubusercontent.com/u/128762057?v=4) | ![sy](https://avatars.githubusercontent.com/u/87513664?v=4) |
|--------------------------------------|---------------------------------------|------------------------------------|
| [임지섭](https://github.com/icebear0111)| [이원빈](https://github.com/Lwonbin) | [이소연](https://github.com/reed426) |
|수의사<br>추가 기능 구현<br> |조회 기능 구현<br> |수정 기능 구현 <br> 발표 |


## 기능

### 1. 조회
	1-1. 주인
	 1-1-a. 전체 조회
  		- 필터링에 빈칸 입력 시, DB에 등록된 전체 Owner를 조회한다.
     1-1-b. 필터링 조회
      	- 필터링에 LastName 입력 시, DB에 등록된 LastName을 필터링해 조회한다.
      	- 해당 주인이 없을 경우 "has not been found"를 출력한다.
     1-1-c. 주인 프로필 조회
      	- 목록에서 주인 이름 클릭 시 Name, Address, City, Telephone의 값을 볼 수 있다.
      	- 하단에 등록된 펫을 조회할 수 있다(펫의 값은 Name, Birth Date, Type).
  	1-2. 수의사
  	  1-2-a. 전체 조회
    	- DB에 등록된 전체 수의사를 조회한다.

### 2. 추가
	2-1. 주인 추가
 	 	- First Name, Last Name, Address, City, Telephone 필드를 입력해 주인을 추가한다.
 	 	- 값이 비어있는 필드의 경우 X 비어 있을 수 없습니다.
 	 	- Telephone의 필드의 값은 숫자로 지정해야 하며 번호의 길이가 10이하여야 한다.
  	2-2. 펫 추가
   		- 펫 추가를 할 경우 Name, Birth Date, Type의 값을 설정한 뒤에 Add Pet을 눌러 저장한다.
     	- 값들이 빈칸일 경우 "X is required"를 출력한다.
    


		
### 3. 수정
	3-1. 주인 수정
  	  	- First Name, Last Name, Address, City, Telephone의 필드값을 수정할 수 있다.


## 결과

### 1. HOME
<img width="1440" alt="image" src="https://github.com/user-attachments/assets/4901b3b1-c437-42a9-91cd-143566d20d5d" />


### 2. FIND USERS
<img width="1440" alt="image" src="https://github.com/user-attachments/assets/1620fc77-f8f9-452b-a118-d4c27dfba494" />

#### 2-1. Find Owner (전체 조회)
- pagination 추가 구현
<img width="1439" alt="image" src="https://github.com/user-attachments/assets/0c1e3d95-b676-4bc3-99fe-434012acd6f4" />


#### 2-2. Find Owner (특정 인물 조회)
- 입력한 Last Name을 가진 Owner가 1명 일 떄 -> 바로 Owner Information으로 이동
<img width="1439" alt="스크린샷 2025-03-10 11 19 38" src="https://github.com/user-attachments/assets/bddb4035-8f1a-402b-9e96-9f510e0189db" />

- 입력한 Last Name을 가진 Owner가 2명 이상일 때 
<img width="1440" alt="image" src="https://github.com/user-attachments/assets/db105999-2949-4ece-a198-1f46035f39a2" />

#### 2-3. Owner Information 
<img width="1438" alt="image" src="https://github.com/user-attachments/assets/e2708e13-fd3d-4718-898e-e82ce404bac7" />

- Edit Owner
<img width="1440" alt="image" src="https://github.com/user-attachments/assets/eee71741-6786-43be-9554-94126a3bf8b8" />

- Add New Pet
<img width="1440" alt="스크린샷 2025-03-10 11 23 11" src="https://github.com/user-attachments/assets/b51e62c5-2a30-4bff-9738-c6776e979229" />

### 2-4. Pets and Visits
- Edit Pet
<img width="1438" alt="image" src="https://github.com/user-attachments/assets/e7b871df-1de1-498e-9b3d-a57e0c030bbe" />

- Add Visit
<img width="1440" alt="image" src="https://github.com/user-attachments/assets/2bff99bf-6431-4417-9d30-76ef43851819" />

- Date와 Description을 입력하여 추가하면 Previous Visits에 추가된다.
<img width="1440" alt="image" src="https://github.com/user-attachments/assets/123f42cd-2e06-4061-9465-0cd1334a42ea" />


### 3. VETERINARIANS
<img width="1429" alt="image" src="https://github.com/user-attachments/assets/851f3059-39e4-4b48-8075-a2fb170b563b" />


### 4. ERROR
<img width="1440" alt="스크린샷 2025-03-10 11 15 20" src="https://github.com/user-attachments/assets/f3754a76-c1cb-45e2-bbb7-bf2fbe7d815a" />


## Swagger
<img width="1413" alt="image" src="https://github.com/user-attachments/assets/2d56b199-cb7a-4d75-ab76-38a596ae79ee" />

	
