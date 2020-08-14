# practice hexagon architecture with spring
hexagon architecture의 개념 정리 및 sample code 작성

## What is hexagon architecture?
> hexagon architecture는 port & adapter architecture라고 불리는 소프트웨어 디자인 패턴으로 port & adapter를 이용하여 application의 components 관계 결합도를 낮게하여 유연성을 높이는 것을 목표로하고 있다. 

### Port
> component의 기능을 명세하고 component간 연결하는 interface 이다.   
application core에 접근하기 위해서는 port를 통하여 호출한다.
port는 in-bound, out-bound port로 구분할 수 있다. (entry point, exiting point)   

#### in-bound port
> application core에 접근하기 위한 port이다.   
application core는 in-bound port를 상속받아서 구현된다.

#### out-bound port  
> 외부(데이터베이스, 외부 라이브러리, 외부 API)와 통신하기 위한 interface이다.

### Adapter
> interface를 다른 interface로 변경하고 두 종류의 어뎁터가 있다.

#### Primary Adapters(Driving Adapters)
> UI를 표현한다. ex) API Controller, Web Controller, View   
application을 실행하기 때문에 Driving Adapter라고 한다.   
Primary Adapter는 in-bound port를 사용하여 application core에 접근한다.

#### Secondary Adapters(Driven Adapters)
> 데이터베이스, 외부 라이브러리, 외부 API 등에 대한 연결이다.   
application core에서 Secondary Adapter를 호출하여 데이터베이스, 외부 라이브러리, 외부 API를 사용한다.   
Secondary Adapter는 out-bound port를 상속받아서 구현한다.

## 구조
```
adapter   
    - persistence   
        api-client implement out-bound port interface
        repository implement out-bound port interface
    - web
        controller

application
    - application
        - port
            - in
                interface
            - out
                interface
        - service
            service implement in-bound interface
    - domain

common
    - annotation
```

### referrence
Wikipedia - Hexagonal architecture (software)
<https://en.wikipedia.org/wiki/Hexagonal_architecture_(software)>

Clean Architecture with Spring by Tom Hombergs @ Spring I/O 2019   
<https://speakerdeck.com/thombergs/o-2019?slide=39>   
<https://www.youtube.com/watch?v=cPH5AiqLQTo>   

Hexagonal Architecture, DDD, and Spring      
<https://www.baeldung.com/hexagonal-architecture-ddd-spring>   

https://www.thinktocode.com/2018/07/19/ports-and-adapters-architecture/
https://blog.coderifleman.com/2017/12/18/the-clean-architecture/
https://getoutsidedoor.com/2018/09/03/ports-adapters-architecture/

