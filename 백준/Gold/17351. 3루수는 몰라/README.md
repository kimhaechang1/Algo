# [Gold IV] 3루수는 몰라 - 17351 

[문제 링크](https://www.acmicpc.net/problem/17351) 

### 성능 요약

메모리: 26880 KB, 시간: 184 ms

### 분류

다이나믹 프로그래밍, 그래프 이론, 그래프 탐색

### 제출 일자

2025년 7월 1일 12:45:40

### 문제 설명

<p>기어코 2루수의 이름을 알아낸 선수들. 2루수에게 매운맛을 보여 주려는 순간, 2루수가 충격적인 사실을 고백했다!</p>

<p style="text-align: center;"><em>"사실 음료수는 3루수가 보낸 거야!!"</em></p>

<p>선수들은 서로 3루수가 아니라고 주장하기 시작했다. 하지만 이젠 아무도 믿을 수 없다! 이번에는 감독님도 별로 도움이 안 되는 것 같다.</p>

<p style="text-align: center;"><em>"1루수가 누구야 2루수 이름이 뭐야 3루수는 몰라"</em></p>

<p>하지만 언제나 희망은 있다! 운동장에 3루수에 대한 단서가 흩뿌려져 있는 듯하다.</p>

<ol>
	<li>운동장은 N×N 크기의 격자로 표현된다.</li>
	<li>운동장의 각 칸에는 알파벳 대문자로 표현되는 단서들이 숨겨져 있다.</li>
	<li>우리는 (1, 1)에서 (N, N)까지 이동하면서 단서를 수집해야 한다. (각 좌표는 시작점과 도착점이다.)</li>
	<li>지금은 여름이라 몹시 더우므로, (r+1, c) 또는 (r, c+1)로만 움직여야 한다. (안 그러면 탈진해서 쓰러질 것이다!) 물론, 운동장을 벗어날 수는 없다.</li>
	<li>운동장을 지나면서 수집한 단서를 <strong>순서대로</strong> 이어 붙인 문자열을 S라고 하자. S에 "MOLA"라는 부분 문자열이 최대한 많이 등장하도록 움직여야 한다.</li>
	<li>어떤 칸을 지나면 반드시 해당 칸의 단서를 수집해야 한다. 즉, 단서를 취사 선택하지 않는다.</li>
</ol>

<p><strong>3루수는 몰라몰라몰라몰라아몰르파티~</strong></p>

### 입력 

 <p>첫째 줄에 운동장의 크기 N이 주어진다. (1 ≤ N ≤ 500)</p>

<p>둘째 줄부터 N개의 줄마다 N개의 문자가 주어진다. 이는 운동장 각 칸의 단서를 적어둔 지도이며, 알파벳 대문자로만 이루어져 있다.</p>

<p>첫 줄의 첫 문자가 (1, 1)이고, 마지막 줄의 마지막 문자가 (N, N)이다.</p>

### 출력 

 <p>주어진 규칙을 따라 이동하면서 부분 문자열로 "MOLA"를 최대한 많이 포함하는 S를 만들고, 이때 S에 포함된 부분 문자열 "MOLA"의 개수를 출력한다.</p>

