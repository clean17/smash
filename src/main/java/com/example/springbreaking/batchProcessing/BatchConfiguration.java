//package com.example.springbreaking.batchProcessing;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
//import org.springframework.batch.item.database.JdbcBatchItemWriter;
//import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
//import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//
///**
// * HSQLDB 메모리 DB 사용
// *
// * JobRepository : 배치 작업의 메타 데이터를 저장, 관리
// * 상태관리, 이력관리, 배치진행기록, 동시성 제어
// *
// * EnableBatchProcessing : 스프링 배치와 관련된 설정
// * - BatchConfigurer 인터페이스를 구현
// * - JobRepository 빈 생성 - 메타데이터 관리
// * - JobBuilderFactory, StepBuilderFactory 빈 생성
// * - PlatformTransactionManager  빈 생성 - 트랜잭션 관리
// * - 메타데이터 저장소 제공
// */
////@Configuration
////@EnableBatchProcessing
//public class BatchConfiguration {
//
//    // EnableBatchProcessing 로 생성된 빈은 Autowired로 가져와야 함
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    /**
//     * 플랫 파일( csv )에서 데이터를 읽어 온다.
//     * @return
//     */
//    @Bean
//    public FlatFileItemReader<Person> reader() {
//        return new FlatFileItemReaderBuilder<Person>()
//                // 파일 리더의 이름
//                .name("personItemReader")
//                // 리소스 설정
//                .resource(new ClassPathResource("sample-data.csv"))
//                // 파일 데이터가 구분자로 구분되어 있음을 알려줌( 디폴트 = , )
//                .delimited()
//                // 파일의 각 라인의 이름을 설정
//                .names(new String[]{"firstName", "lastName"})
//                // 파일의 각 라인을 도메인 객체로 변환
//                // BeanWrapperFieldSetMapper : 데이터 소스의 필드를 Java객체로 매핑
//                .fieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
//                    setTargetType(Person.class);
//                }})
//                .build();
//    }
//
//    /**
//     * 대문자로 처리하는 프로세서 등록
//     * @return
//     */
//    @Bean
//    public PersonItemProcessor processor() {
//        return new PersonItemProcessor();
//    }
//
//    /**
//     * DB 작업을 정의
//     *
//     * JdbcBatchItemWriter는 여러 아이템을 한번의 데이터베이스 연산으로 처리할 수 있다.
//     * SQL을 작성해야 한다.
//     * 각 아이템의 값을 SQL에 바인딩
//     * 데이터 소스를 설정해야 함
//     *
//     * @param dataSource
//     * @return
//     */
//    @Bean
//    public JdbcBatchItemWriter<Person> writer(DataSource dataSource) {
//        return new JdbcBatchItemWriterBuilder<Person>()
//                // 데이터 소스의 필드를 INSERT, :[속성] 명이 일치해야 함
//                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
//                .sql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)")
//                .dataSource(dataSource)
//                .build();
//    }
//
//
//    ///////////////////////////////////
//    /////// 배치의 작업 흐름을 구성 ///////
//    ///////////////////////////////////
//
//    /**
//     * 배치의 작업을 정의
//     *
//     * 콘솔 출력 -> Job: [FlowJob: [name=importUserJob]] launched with the following parameters: [{run.id=1}]
//     * @param listener
//     * @param step1
//     * @return
//     */
//    @Bean
//    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
//        return jobBuilderFactory.get("importUserJob")
//                // 각 작업의 고유한 ID 증가 생성
//                .incrementer(new RunIdIncrementer())
//                // 리스너 ( 콜백 )
//                .listener(listener)
//                // 작업의 흐름을 설정
//                .flow(step1)
//                .end()
//                .build();
//    }
//
//    /**
//     * 배치의 단계를 정의
//     * @param transactionManager
//     * @param writer
//     * @return
//     */
//    @Bean
//    public Step step1(PlatformTransactionManager transactionManager, JdbcBatchItemWriter<Person> writer) {
//        return stepBuilderFactory.get("step1")
//                // 청크 처리 방식을 설정 -  10개의 아이템을 한번에 처리
//                .<Person, Person> chunk(10)
//                // 리더기 -> 파일을 읽고 Java객체로 변환
//                .reader(reader())
//                // 작업 프로세서 - Java객체의 필드를 대문자로 변환
//                .processor(processor())
//                // JdbcBatchItemWriter - 데이터를 배치 방식으로 DB에 INSERT - Bean으로 등록 한거 가져옴
//                // 데이터 소스를 가져와 DB 작업(INSERT)
//                .writer(writer)
//                // 트랜잭션
//                .transactionManager(transactionManager)
//                .build();
//    }
//
//}
