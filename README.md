# Linux
    21.05.10
    #1.드디어 리눅스의 시작
    #2.명령어
    #3.추가설치 프로그램
    #4. 콘솔과 터미널의 차이
    #5.서버 시간 동기화
    #6.vi 편집기 명령어
    #7.c 컴파일 하기 
    #8.권한
    #9.Unix directory
    #10.05.11
    #11. tar, gzip
    #12. vi editor
    #13. 네트워크


### #1. 드디어 리눅스의 시작 !

개발 환경,
Desktop -> Oracle VM, CentOS7

Red hat 패키지 명, rpl
Window는 msi


    직접 소스를 받아서 컴파일해서 설치하는 이유
 경로나 파일을 직접 관리하기 위해서. 내 프로그램을 직접 관리하고 사용하기 위해서임
 yum 등을 설치해도 되지만, 설정 파일에 미리 설정된 세팅에 맞게 설치되므로, 직접 관리하고 정하기 위해서
 
 java, db, git, maven, apache 이런건 내가 직접 관리해야되는거니까 소스파일 직접 다운 받고,
 다른 뭐, wget이런건 상관 없음 (유틸 도구 같은 것들)
 

        파티션 나누기
머신을 설정할때 안정성을 위해서 파티션을 나눠주는게 좋다.
/root 는 필수적이며 /boot, /swap 으로 파티션을 나누었다. (하나의 고장이 다른 모든 것에 영향을 끼치는 것을 방지하기 위해)
 

### #2.명령어 

\ or 절대경로 => alias 없이 사용 가능

useradd -g whell 계정이름 = 유저를 추가하는데 wheel 그룹에 넣겠다. wheel 그룹은 루트로 전환이 가능한 그룹이다.
passwd = 패스워드 변경, passwd 계정명 = 계정의 패스워드 설정
sudo = 루트로 변경,
su - = 루트로 변경하기(-를 붙여야 root에 필요한 설정 파일을 함께 읽어온다. root는 생략 가능), su 계정명 = 유저로 들어가기


shift + insert = Window에서 복사한 클립 붙여넣기
set = 환경변수 보기 (shell)

ps = 프로세습 보기
yum = 업데이트 제어, 소프트웨어 설치, 업데이트 사항 확인, 패키지 관리자
apt = 패키지 관리 

yum update = 업데이트 목록 확인 및 업데이트
yum install
yum clean all = 패키지 캐시 파일 삭제
yum list = 모든 패키지 표시
yum remove <패키지 명> = 패키지 삭제

fdisk -l = 파티션 정보(디스크 정보) 보기
grep -n ro /etc/profile = /etc/profile에 ro라는 단어가 있는 것을 모두 출력하라, 라인 번호를 포함해서.
mount = 디스크나 usb와 같은 특정 디바이스를 사용하기 위해 하드웨어와 디렉토리를 연결하는 작업
| = 파이프, 앞의 결과(출력)을 뒤에 전달.

systemctl is-enabled sshd = 서비스 등록/해제 (부팅과 함께 서비스가 실행되는 것의 설정과 해제), 이건 그 유무를 물어봄.
systemctl start/stop/restart = 서비스의 시작/종료/다시시작

useradd -D (/etc/default) = 사용자 생성 시 기본 정보 (홈, skel정보 - 스켈 디렉토리에서 유저 디렉터리로 파일 복사함, shell종류 etc..)
useradd username = 그룹을 별도로 지정하지 않으면 새로 그룹을 만들어 유저를 생성함
useradd -g users user2 = user2를 생성해서 users라는 그룹에 포함시킨다.
useradd -M user3 = 홈 디렉터리 없이 유저를 생성, 특수한 목적을 위해 필요한 경우.

userdel userID = user삭제 

groupadd groupID = group 생성
groupdel = group 삭제
groups groupID = 어느 그룹에 속해있는지 확인.

passwd(root에서) = root비밀번호 변경
passwd userID = userID 비밀번호 설정 및 변경

last = 로그인, 로그아웃한 흔적들. reboot 정보들. 

ln -s filename simbolfile = 링크 파일 생성, 윈도우의 바로가기 역할

make = makefile 만들어서 의존성 관계, 명령어 입력 후 실행 가능
gcc -c main.c
gcc -c hello.c
gcc -o hello main.o hello.o

makefile
--------------------
hello: main.o hello.o
    gcc -o hello main.o hello.o
main.o: main.c
    gcc -c main.c
hello.o: hello.c
    gcc -c hello.c

clean:
    rm -f hello *.o
 --------------------

touch = 파일 수정 안하고 다시 다 컴파일 하고 싶을 때( //touch *.c)

chown -R = chown -R webmaster:wheel /home/webmaster/dowork, 사용자 바꾸기 (-r 디렉토리 밑의 모든 파일도)



cp = copy
mv = rename, 옮기기

<파일 보기>
cat = 스크롤 형식으로 보여줌
more = 한 페이지씩 잡아서 보여줌 
less = more의 진화된 형, : 형태의 vi편집기와 유사, editting 기능만 제외.


<찾기>
find /찾는위치 -name '*log' = 파일 찾기 명령어
whereis ls = ls명령어의 위치
ex) ps -ef | grep sshd | grep -v grep : ps -ef 중에 sshd가 들어간 행 중에서 grep이란 글자가 포함된 행을 제외한 결과를  출력



<파이프와 리다이렉트>
프로세스 | 프로세스 = 프로세스의 출력을 다른 프로세스의 입력으로 결과를 출력
출력 > 파일 = 출력을 파일로 입력
>> = append역할, 변경 내용을 추가로 입력










### #3.추가 설치 프로그램 <on premiese>, 직접 다 설치해서 서비스 환경을 직접 구축하는 것 <-> 클라우딩 컴퓨팅
    
cronie 
cron job = 정해진 시간에 프로그램을 실행하게 하는 프로그램

rdate = 원격으로 시간을 물어봐서 시간을 맞추는 것. 시간을 정확하게 맞추고자.
--> 보라넷에 시간이 맞추어져 있음. 매우 정확함. 걔한테 물어봐서 시간을 리턴 받고 내 시스템 시간을 설정함
--> 주기적으로 실행해줘야함

gcc = gnu c compile, c로 작성되있는게 많아서 설치해줘야함. 
gcc-c++  = mariaDB 같은게 c++로 되있음

make = maven이 java프로그램들 쫙 war등으로 만들어주는 빌드 툴인데, 그런것과 같은 빌드 툴임

wget = url로 zip파일 등을 다운로드 해야할 때 사용할 프로그램

cmake = c++ compile 하는거

bind-utils = nslookup이 들어있는 놈

psmisc = 명령어 pstree써보세여. 부모 관계를 포함하여 나타내줌 --> 프로세스는 ~ 부모가 있다. 

ifcofig ...기본에 설치가 안되어있었... 설치합시다






### #4.콘솔과 터미널의 차이 ?

큰 차이는 없다 ! 터미널은 다중 실행이 가능. 터미널들은 원격으로 쉘과 연결되어 실행된다.
원격 터미널이라고 부른다.

putty/xshell(원격 터미널) 과 같은 것을 이용해 인터넷을 통해서 원격으로 쉘과 연결할 수 있다.
서버 측에서는 데몬 서버 프로그램이 떠 있어야함.
--> 암호화 되어있는 연결, 통신, 
ssh(secure shell) ----- telnet은 암호화되어있지 않을 뿐 같은 거임.
ssh client / ssh server

ps -ef 명령어 실행 후 /usr/sbin/sshd -D와 같은 것이 실행되어있는 것이 보인다면 이게 바로 서버 프로그램임.
xshell / putty 와 같은 원격 터미널을 통해 접속 가능. 호스트 ip, 통신 포트는 22번으로 연결함.


         집에서 연결은 할 수 없다. 왜?
집에서 사용하는 ip는 공용 ip이고 현재 접속중인 ip는 사설 ip로 10, 192, 127번대 ip이다. 
공용 ip는 KT 등과 같은 곳으로부터 유동 ip 혹은 고정 ip로 비어있는 ip를 받는 것이다.(공유기 등을 통해)
이 공유기는 네트워크를 만들고 이 네트워크를 사설 네트워크라고 부른다.

여기서, 같은 사설 ip 주소를 가지고 있는 경우에 인터넷이 나를 어떻게 찾을까.
https://api.ipify.org/를 통해 보면 같은 공용 ip를 가지고 있음을 알 수 있다. 
--> 공유기는 ARP 프로그램을 통해 거쳐서 나가면서 ip가 변경되어 공인 ip로 나갈 수 있게 바꿔준다.
공유기는 게이트웨이 역할도 하고 있다고 할 수 있다. 




### #5.서버 시간 동기화
서버를 운영하다 보면 시간 동기화를 하지 않을 경우 시간이 조금씩 어긋나게 된다. 이를 막아주기 위해 rdate 혹은 다른 방법을 이용해서
서버 시간을 국제 시간에 동기화 시켜 주어야 한다.
--> 스크립트를 작성하였다. 





### #6.vi 편집기 명령어

:w = 저장
:q = 나가기
:q! = 강제 나가기
/검색어 = 검색 (명령모드에서)




### #7.c 컴파일 하기 

gcc 컴파일러 사용. 
gcc -o 파일명 목적파일 --> 실행파일
-o로 실행파일을 지정해주지 않으면 a.out으로 컴파일되게 되어있음. 내가 만든 라이브러리와 붙이기 위해선 개별적으로 설정해야한다.
gcc -c 파일명 명령어로 목적파일을 만들도록 컴파일하고, 라이브러리 링크 작업을 위해 gcc -o 파일명 목적파일 명령어를 입력한다.

      --> 실행파일을 바로 실행했을 때 실행이 되지 않는 이유
윈도우는 현재 위치에서 찾는 것과 달리 리눅스와 유닉스는 지정된 PATH에서 파일을 찾게 된다. 그러므로 PATH 설정을 해주던가
경로를 입력하여 실행해주어야한다. (절대 경로 혹은 현재 위치의 상대 경로)
 





### #8.권한
r -> 4
w -> 2
x -> 1

실행파일
rx => 5
rwx => 7

일반파일
rw => 6
r => 4

chmod 명령어를 사용하여 권한 설정, ex) chmod 700



### #9.Unix directory

/ = 최상위
/root = root의 기본 경로
/etc = 설정파일
/var = 어플리케이션들이 남기는 로그들이 주로 저장된다.
/home = user들의 기본 경로.
/dev = device 파일들의 집합

기본적으로 프로그램 다운시 설치되는 경로
/usr/local/bin 
/usr/local/lib == 라이브러리





# #10.05.11

v 다중 사용자 환경. 서비스와 같음
v 네트워크 보안, 트래픽 제어
v ssh 접속으로 바로 루트로 접속 불가, 루트 접속 권한이 있는 사용자 계정으로 접속 후 전환해야한다.
v shell 종류 : bsh, bash, ksh, zsh etc..
v 디렉터리 구조(하다보면 머리에 들어온다 ~)
v 리다이렉트 (>)

v /dev/pts/0 --> 특수 파일, 파일이라고 생각하면 안됌. 드라이버랑 연결,드라이버를 통해서 연결되어있는 장치 파일이라고 생각해야함.
캐릭터형으로 작성.  디스크랑 연결되어있는 파일(sda0,1,2...)은 블록 단위로 파일 맞음. 

v 파일간의 의존성


### #11. 압축, tar(archining == 압축 없이 모아논것)

<tar>
디렉터리 구조를 그대로 유지하면서 모음 
tar cvf name /경로 및 모을 파일 == 아카이빙
tar xvf name == 해제
 
tar xvfz == 압축 해제 및 아카이빙 해제


<gzip>
 gzip file == gzip압축
 gzip -d file.gz == gzip압축해제 
    
 
### #12. JAVA 다운
일반 웹에서 링크 걸려있는 다운로드
다운로드 링크 복사
wget (shift+insert == 붙여넣기)

**로컬에서 움직이고 싶을땐 lcd

        1. JDK 다운 (oracle의 경우, wget이 안되도록 되어있음)
jdk-8u291-linux-x64.tar.gz 다운(java 8)(로컬에)


        2. ftp 이용
파일 설치 위치로 xshell을 이용하여 이동
-- sftp userID@IP
-- 파일명 복사 (ctrl + insert)
-- put 파일명(shitf + insert)

        3. 올라와있는거 확인후 해제, root홈으로 

        4. 파일 위치 정하기

/usr/local/douzone2021/java(java,git,mariadb)


        5. 파일을 지정된 디렉터리로 옮기기 (설치)
        6. 링크 파일 생성하기 (버전 관리)
ln -s /usr/local/douzone/java1.8 /usr/local/douzone2021/java

버전확인
 /usr/local/douzone2021/java/jdk1.8.0_291/bin/java -version
 
        7.설정
vi /etc/profile 에 아래 정보 입력
\# java
export JAVA_HOME=/usr/local/douzone2021/java/jdk1.8.0_291
export CLASSPATH=JAVA_HOME/lib/tools.jar
export PATH=$PATH:$JAVA_HOME/bin

        8. 현재 shell에 환경에 적용하기

source /etc/profile

        9. 파일 작성하고 컴파일 하기

.java 로 코드 작성하고 
javac 로 컴파일
java -cp . 파일경로/파일명(.클래스 빼고)
완료!




    05. 12
### #12. vi editor

sHitf + g = 맨 아래로.

"이름설정 + Y/yy = 지정된 행을 버퍼에 이름을 설정하여 저장
"설정된 이름 + p = 이름으로 저장된 내용을 붙여넣기
ex)"1 + Y, "1 + p

:w %.old : 백업 파일 만들기
:!명령어 : 한줄 명령어 shell에서 실행하고 돌아오기
:sh : shell에서 작업 후 exit하면 다시 돌아옴



    맥 os clipboard
맥의 경우 clipboard를 사용하기 위해선 vimrc파일 설정을 해줘야 한다.
홈에서 .vimrc 파일을 만들어서 vi가 사용자 설정을 읽어들일 수 있게 해주는 것이다.

vi --version | grep file을 통해 설정파일을 저장할 경로를 확인할 수 있다. 

기본적으로
set nu
set clipboard=unnamed " use OS clipboard 를 설정하여 
clipboard 사용과 행 번호 출력을 설정하였다. 
Tmux에서도 함께 사용하기 위해선
https://rampart81.github.io/post/vim-clipboard-share/
아래의 블로그를 읽어보는 것도 좋다. 
https://iamfreeman.tistory.com/entry/vi-vim-%ED%8E%B8%EC%A7%91%EA%B8%B0-%EB%AA%85%EB%A0%B9%EC%96%B4-%EC%A0%95%EB%A6%AC-%EB%8B%A8%EC%B6%95%ED%82%A4-%EB%AA%A8%EC%9D%8C-%EB%AA%A9%EB%A1%9D


### #13. 네트워크

    DNS 서버 변경하기
  /etc/resolv.conf -> DNS 주소 변경하기
  
  
    ping, ICMP 열기
   /etc/sysctl.conf 
   new.ipv4.icmp_echo_ignore_all=0 입력
   
    /etc/hosts
   ip주소와 별칭 매칭하기 
   주소 별명
   dns name 과 hostname 은 다른것
  
    netstat -r/t
   특정 포트가 열려있는지 확인하기 좋음
   ex) nesstat -a | grep ssh => listen 을 보면 어디로 들어오는지 확인 가능
    netstat -a | grep portnum
    netstat -anpt | grep sshd ====>>>>
    
    네트워크 카드 다운
   ifconfig enp0s3 down -> 서버에서 다시 열어줘야함
   장치명 확인 
   **** /etc/sysconfig/network-script ******
   
   서버에서 다시 up
  
  
    고정 ip 설정하기
    
   /etc/sysconfig/network-scripts
   ifcfg-enp0s3 파일 수정
   
        입력하기 
        BOOTPROTO => "static"
    IPADDR="192.168.80.116"
    GATEWAY="192.168.80.254"
    NETMASK="255.255.255.0"
    DNS1="168.126.63.1"
   
    /etc/resol::v.conf
    nameserver -> 168.126.63.1
 
    /etc/hosts 
    /etc/hostname
    
    ***** systemctl restart ****
    
    
   
   
  
