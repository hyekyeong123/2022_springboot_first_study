# 첫번째 스터디 : 스프링부트와 RestAPI
    패스트캠퍼스 자바 웹 개발 마스터 강의(강사. 예상국) 중 일부

## 강의와 다른 부분 or 강의와 버전이 달라 생기는 에러들 

#### lombok 관련
- 실행시 에러 발생 : variable name not initialized in the default constructor
  
    ##### Gradle 버전 확인
    
    Gradle을 통해서 롬복 의존성 라이브러리를 추가해주었지만 동작하지 않는다면 자신의 Gradle 버전이 5.x 이상인지 확인해주셔야합니다.
    
    그 이유는 Gradle 버전이 올라가면서 Lombok 의존성을 추가하는 방법이 바뀌었기 때문입니다.
    
    ####### Gradle 5.x 미만
    ```
    dependencies {
    implementation 'org.projectlombok:lombok'
    }
    ```
    
    ###### Gradle 5.x 이상
    ```
    dependencies {
    compileOnly 'org.projectlombok:lombok'
    annotationProcessor 'org.projectlombok:lombok'
    }
    ```

#### mysql 대신 mariaDB 사용시

- build.gradle 추가
    ```
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    runtimeOnly 'org.mariadb.jdbc:mariadb-java-client' // MariaDB
    ```


##### application.prpperties
- 아래는 로컬 DB 정보이다. 자신의 DB에 맞춰서 포트와 아이디, 비번은 바꿔줄 것

    ```
    spring.datasource.driverClassName=org.mariadb.jdbc.Driver
    spring.datasource.url=jdbc:mariadb://localhost:3306/firstspring?useSSL=false&useUnicode=true&serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true
    spring.datasource.username=root
    spring.datasource.password=admin
    ```

  
#### JUnit 관련 
- Assert.assertTrue를 인식못하는 문제
    동영상 강의에서는 Assert.assertTrue를 사용 -> assertTrue(user.isPresent());로 변경
    ```
    import static org.junit.jupiter.api.Assertions.assertFalse;
    import static org.junit.jupiter.api.Assertions.assertTrue;
    ```
  
#### DB 쿼리 추가
- firstspring -> 본인 지정 스키마 이름
- MySQL Workbench Forward Engineering
    ```
    SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
    SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
    SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
    
    -- -----------------------------------------------------
    CREATE SCHEMA IF NOT EXISTS `firstspring` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin ;
    USE `firstspring` ;
    
    -- -----------------------------------------------------
    -- Table `study`.`item`
    -- -----------------------------------------------------
    DROP TABLE IF EXISTS `study`.`item` ;
    
    CREATE TABLE IF NOT EXISTS `firstspring`.`item` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `price` INT NOT NULL,
    `content` VARCHAR(45) NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;
    
    
    -- -----------------------------------------------------
    -- Table `study`.`order_detail`
    -- -----------------------------------------------------
    DROP TABLE IF EXISTS `firstspring`.`order_detail` ;
    
    CREATE TABLE IF NOT EXISTS `firstspring`.`order_detail` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `order_at` DATETIME NULL,
    `user_id` BIGINT(20) NOT NULL,
    `item_id` BIGINT(20) NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;
    
    
    SET SQL_MODE=@OLD_SQL_MODE;
    SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
    SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
    ```
  
- 참고로 DB 문자열셋이 한글 지원해주는지 확인하기
    나같은 경우는 현재 문자열 셋이 armscii8_bin으로 되어 있는데 이를 utf16_bin으로 변경해줘야 했음
  -> 그렇지않으면 에러 발생 : Incorrect string value: '\xEC\xA0\x84\xEC\x9E\x90...' for column...
  
  
