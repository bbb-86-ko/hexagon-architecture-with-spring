# practice hexagon architecture with spring
hexagon architecture의 개념 정리 및 sample code 작성

## what is hexagon architecture?
hexagon architecture는 port & adapter architecture라고 불리는 소프트웨어 디자인 패턴으로 port & adapter를 이용하여 application의 components 관계 결합도를 낮게하여 유연성을 높이는 것을 목표로하고 있다. 

### port
componet의 기능을 명세하고 componet간 연결 하기 위한 interface이다.   
port는 inbound와 outbound port로 구분할 수 있다.    
(entry point, exiting point 라고 하기도 한다)   

- #### inbound port   
   application core의 기능을 명세하고 외부(primary adapters)에 노출하는 interface이다. 외부에서는 application core에 접근하기 위해서는 port를 통하여 호출한다.   
   application core(ex) XxxxService)는 inbound port를 상속받아서 구현된다.   

- #### outbound port  
   데이터베이스, 외부 라이브러리, 외부 API 등의 정보를 사용하게 되는 기능을 명세한 interface이다.    
   외부(secondary adapter의)는 outbound prot를 상속 받아서 구현된다.   
   application core에서 outbound prot를 이용하여 외부(secondary adapter의)기능을 호출하게 된다. 이렇게 outbound interface를 이용하여 호출하게 되면 application core 외부(secondary adapter의)의 구현방법과는 관계가 필요없어진다.   
   (ex) rdb데이터를 사용하다 검색엔진데이터 사용으로 변경을 해도 영향을 받지 않는다.)   

### adapter
interface를 다른 interface로 변경하는 역할을 한다. primary와 secondary adapter가 있다.

- #### primary adapter의(driving adapters)
   UI를 표현한다. ex) API Controller, Web Controller, View 
   application을 실행하기 때문에 driving adapter의 한다.   
   primary adapter의 inbound port를 사용하여 application core에 접근한다.

- #### secondary adapter의(driven adapters)
   데이터베이스, 외부 라이브러리, 외부 API 등에 대한 연결이다.   
   secondary adapter의 outbound port를 상속받아서 구현한다.
   application core에서 outbound port를 이용하여 secondary adapter의 기능르 사용하게 된다.   
   (데이터베이스, 외부 라이브러리, 외부 API를 호출하게 된다.)
   

## package architecture
```
adapter   
    - persistence   
        api-client implement outbound port interface
        repository implement outbound port interface
    - web
        controller

application
    - application
        - port
            - in
                interface (inbound port)
            - out
                interface (outbound port)
        - service
            service implement inbound interface
    - domain

common
    - annotation
```

## class description

- ### RegisterBookUseCase
   role : inbound port   
   class type : interface   
   implementation : RegisterBookService   
   ```
   public class RegisterBookService implements RegisterBookUseCase {
    ... skip ...
   }
   ```
   caller : Primary Adapters ex) RegisterBookController   
   ```
   public class RegisterBookController {
       private final RegisterBookUseCase registerBookUseCase;
           ... skip ...
       public void registerBook(@RequestBody Book book) {
           ... skip ...
           registerBookUseCase.registerBook(....);
       }
   }
   ```

- ### FindAuthorByIdPort, FindBookByTitlePort, PersistBookPort
   role : outbound port   
   class type : interface   
   implementation : AuthorPersistenceAdapter, BookPersistenceAdapter   
   ```
   public class AuthorPersistenceAdapter implements FindAuthorByIdPort {
   
       private final AuthorRepository authorRepository;
   
       public AuthorPersistenceAdapter(AuthorRepository authorRepository) {
           this.authorRepository = authorRepository;
       }
   
       @Override
       public Author findAuthorById(Long authorId) {
           return authorRepository.findById(authorId)
                   .orElseThrow(() -> new FindBookByTitlePort.AuthorNotFoundException(authorId));
       }
   }
   ```
   caller : implementation of inbound port ex) RegisterBookService   
   ```
   public class RegisterBookService implements RegisterBookUseCase {
       private final FindAuthorByIdPort findAuthorByIdPort;
       private final PersistBookPort saveBookPort;
       
       ... skip ...
       
       @Override
       public void registerBook(RegisterBookCommand command) {
           Author author = findAuthorByIdPort.findAuthorById(command.getAuthorId());
           ... skip ...
           saveBookPort.saveBook(book);
       }
   }
   ```

### referrence   
Wikipedia - Hexagonal architecture (software)   
<https://en.wikipedia.org/wiki/Hexagonal_architecture_(software)>   

Clean Architecture with Spring by Tom Hombergs @ Spring I/O 2019   
<https://speakerdeck.com/thombergs/o-2019?slide=39>   
<https://www.youtube.com/watch?v=cPH5AiqLQTo>   

Hexagonal Architecture, DDD, and Spring      
<https://www.baeldung.com/hexagonal-architecture-ddd-spring>   


Blog   
<https://www.thinktocode.com/2018/07/19/ports-and-adapters-architecture/>   
<https://blog.coderifleman.com/2017/12/18/the-clean-architecture/>   
<https://getoutsidedoor.com/2018/09/03/ports-adapters-architecture/>

