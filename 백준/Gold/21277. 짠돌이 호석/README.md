# [Gold III] 짠돌이 호석 - 21277 

[문제 링크](https://www.acmicpc.net/problem/21277) 

### 성능 요약

메모리: 12192 KB, 시간: 112 ms

### 분류

구현

### 제출 일자

2025년 7월 12일 11:36:00

### 문제 설명

<p>DIY(Do It Yourself)는 호석이가 제일 좋아하는 컨텐츠이다. 이번 DIY는 동네 친구 하늘이랑 각자 직소 퍼즐을 하나씩 맞춰보기로 했다. 두 개의 퍼즐은 각자 N<sub>1</sub> 행 M<sub>1</sub> 열과 N<sub>2</sub> 행 M<sub>2</sub> 열의 격자 형태로 이루어져 있다. 각 격자는 정사각형 모양이며, 퍼즐 조각이 있을 수도 있고, 없을 수도 있다. 즉, 아래 그림도 올바른 퍼즐의 완성 형태이다.</p>

<p><img alt="" src="https://upload.acmicpc.net/df7fb12e-b45f-43ac-87c3-2de7f8672251/-/preview/" style="height: 250px; width: 250px;"></p>

<p>성공적으로 DIY가 끝나서 퍼즐이 2개가 완성되었는데, 보관해야 하는 액자를 아직 구매하지 않았다. 그 이유는, 호석이는 엄청난 짠돌이기 때문에 퍼즐 하나마다 액자 하나를 사는 것은 상상도 못하기 때문이다. 액자의 가격은 액자의 넓이<strong>(행의 개수 × 열의 개수)</strong> 로 결정된다. 즉, 퍼즐 두 개를 퍼즐 조각끼리 같은 격자에서 만나지 않도록 배치해서 하나의 액자에 담는 방법 중에 가장 적은 비용일 때를 찾아보자! 단, 각 퍼즐은 90도, 180도, 270도로 자유롭게 회전시켜도 된다.</p>

### 입력 

 <p>첫 줄에 퍼즐의 크기를 나타내는 <em>N<sub>1</sub>, M<sub>1</sub></em>이 공백으로 구분되어 주어진다. 이어서 <em>N<sub>1</sub></em>개의 줄에 걸쳐서 완성된 첫 번째 퍼즐의 정보가 주어진다. 각 행마다 <em>M<sub>1</sub></em>개의 0 또는 1이 공백없이 주어진다. 다음 줄에 퍼즐의 크기를 나타내는 <em>N<sub>2</sub>, M<sub>2</sub></em>이 공백으로 구분되어 주어진다. 이어서 <em>N<sub>2</sub></em>개의 줄에 걸쳐서 완성된 두 번째 퍼즐의 정보가 주어진다. 각 행마다 <em>M<sub>2</sub></em>개의 0 또는 1이 공백없이 주어진다. 0이 써있는 격자는 퍼즐 조각이 없는 격자이며 1은 있는 격자이다. 두 퍼즐 모두 4개 모서리에 최소 하나의 1은 존재하는 것이 보장된다.</p>

### 출력 

 <p>두 개의 퍼즐을 담을 수 있는 액자들 중에 최소 넓이를 출력한다.</p>

