# 지섭이의 동물병원
## 팀
## 👥 팀 소개
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
	
