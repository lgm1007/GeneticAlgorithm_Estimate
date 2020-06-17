# 유전 알고리즘을 이용한 통계값의 함수 구하기

### 목표

* 유전 알고리즘(`Genetic Algorithm`)으로 x축, y축 2가지 변수값을 가지고 있는 통계값의 일부만으로 전체 함수의 추정값을 얻는다.

* ![](https://lh3.googleusercontent.com/proxy/Je6o16KugoRal7Qda2GfiDI-i-d4orMceUDl-uETZWGQ8OpjwGvkVNnwv1T-GklUie2WYkcy5uJmYcLQdDoFDVhNh1fzKx7LcGoY4CXLV58ya5pbjURoLM8ilZ7n3Hawrd5EmWOxOm8rh9B0DBWYOzyiuj9fd7Q99ILPNawzZ_05onZujqBastQXB6Dmi3Cd_-Gu6L75nsYEJMrbbUE1Rn2itka1JRjHY_zHKLrD0-H0SZiF)

  다음 차트 중 남자 아이의 1세~7세 까지의 평균몸무게(kg) 값을 이용하여 실험한다.

<br/>

##### 실험 과정

* 나이 : `x` = {1, 2, 3, 4, 5, 6, 7}, 평균몸무게 : `y` = {10, 12, 13, 15, 17, 20, 23}

* x값과 y축이 비례하는 형태이므로 예상 함수는 1차함수로 표시할 수 있다. 따라서 `f(x) = ax + b`의 식에서 `a`값과 `b`값을 유전 알고리즘으로 구하는 값으로 설정한다.
* `a`와 `b`를 각각 선택연산, 교차연산, 돌연변이 연산을 하여 최종 4개씩 후보값을 얻는다. 얻은 후보값으로 함수 결과값(`fx`)을 얻고 원래 `y`의 값과 결과값의 오차를 비교하여 일정 범위 내에 포함될 경우 그 때의 `a`와 `b`의 값을 가져온다.

<br/>

##### 실험 결과





