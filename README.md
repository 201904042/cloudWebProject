 cloudWebProject
 ===============

# 2024/1학기/클라우드 기반 웹 개발 
## 조원 소개
+ 201904042 박성훈
+ 202004022 김소현
+ 202104256 윤유빈
   
***

### 기능
1. (메인창) : 사이트의 메인
   + 책의 리스트 : 책 고유번호, 책제목, 책저자, 책 장르 혹은 줄거리, 리뷰수 ,책상세정보 버튼, 삭제 버튼으로 구성

   + 도서 검색버튼 : 키워드 입력 시 제목,저자를 비교하여 같은단어가 있을 경우 해당 도서를 리스트에 띄움

   + 도서 등록 버튼 : 도서 등록 창을 띄움

   + 상세정보 버튼 : 해당 도서의 상세 정보창을 띄움

   + 도서 삭제 버튼 : 리스트에서 해당 도서를 삭제하고 해당 도서 뒤의 id를 한칸씩 앞으로 옮겨 빈id가 없게함

2. (도서 등록 창) : (책 제목 , 저자, 장르 혹은 줄거리)(NOT NULL), 취소버튼, 등록버튼
   + 중복확인 버튼 : 제목 옆에 위치하여 해당 제목을 리스트 내의      도서와 비교하고 리스트 내의 같은 책이 있는지 확인함 ++ accept되어야 등록 가능

   + 취소버튼 : 다시 메인창으로

   + 등록버튼 : 요소들이 비어있지 않은지 확인 ++책 데이터베이스의 가장 마지막 id+1로 등록

3. (도서 상세정보창) : 도서의 상세정보 + 리뷰 리스트(책의 id와 리뷰의 리뷰책의번호가 같은것만 띄움)
   + 도서 수정 버튼 : 도서 수정창으로 이동
   + 도서 삭제 버튼 : 해당 도서 삭제하고 메인창으로 이동
   + 취소 버튼 : 메인창으로 이동
   + 리뷰 리스트 : 리뷰어이름, 리뷰내용 / 리뷰등록 버튼. 각리뷰별 수정, 삭제버튼 존재
      + 리뷰 등록 버튼 : 리뷰 등록창으로 이동, 리뷰데이터베이스의 가장 마지막 id+1로 등록
      + 리뷰 수정 버튼 : 해당 리뷰의 수정 등록창으로 이동
      + 리뷰 삭제 버튼 : 리뷰 데이터베이스에서 삭제 후 이전 페이지로 

4. (도서 수정창) : 도서 생성창과 동일한 구조, 해당 도서의 내용을 수정 / 단 도서의 제목은 고정
   + 도서 수정버튼 : 수정된 내용이 비어있지 않은지 확인후 해당 리스트에 업데이트
   + 취소 버튼 : 이전 페이지로 이동

5. (리뷰 등록창) : (리뷰자 아이디, 리뷰내용)(NOT NULL) / 등록버튼, 취소버튼
   + 등록버튼 : 검사후 해당 내용으로 리뷰 등록 ++ 리뷰 데이터베이스의 가장 마지막 id+1로 등록 
   + 취소버튼 : 이전 페이지로

6. (리뷰 수정창) : 리뷰의 내용만 수정가능 /수정버튼, 취소버튼
   + 수정버튼 : 무결성검사 후 리뷰 데이터베이스 업데이트
   + 취소 버튼 : 이전 페이지로
  
***

## 조원 역할
+ 박성훈 : 기능 3번
+ 김소현 : 기능 4번,5번,6번
+ 윤유빈 : 기능 1번 2번